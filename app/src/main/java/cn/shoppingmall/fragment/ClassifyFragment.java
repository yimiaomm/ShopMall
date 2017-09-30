package cn.shoppingmall.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.adapter.CategorizeProductAdapter;
import cn.shoppingmall.bean.ClasslflyBean;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClassifyFragment extends BaseFragment {
    @BindView(R.id.tools)
    LinearLayout toolsLayout;
    @BindView(R.id.tools_scrollView)
    ScrollView tools_scrollView;
    @BindView(R.id.goods)
    ViewPager viewPager;
    Unbinder unbinder;
    private View[] shopViews;
    private List<ClasslflyBean.DataBean> listMenus;
    private CategorizeProductAdapter categorizeProductAdapter;
    private TextView[] listMenuTextViews;
    private Bundle savedState;
    private int currentItem = 0;
    private View view;


    @Override
    public void init() {
        setHasOptionsMenu(true);
        getCategory();

        initViewPager();
    }

    private void initViewPager() {
        // 由于使用了支持包所以最终必须确保所有的导入包都是来自支持包

        // 为ViewPager设置页面变化的监控
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if (viewPager.getCurrentItem() != position) {
                viewPager.setCurrentItem(position);
            }
            // 通过ViewPager监听点击字体颜色和背景的改变
            if (currentItem != position) {
                changeTextColor(position);
                changeTextLocation(position);
            }
            currentItem = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }
    };


    private void getCategory(){
        Map<String,String>map = new HashMap<>();
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getCategoryResult(RetrofitHttp.getRetrofit(builder.build()).getCategory(map));
    }

    private void getCategoryResult(Call<ResponseBody>responseBody){
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    ClasslflyBean bean = MyApplication.gson.fromJson(result,ClasslflyBean.class);
                    List<ClasslflyBean.DataBean>list = bean.getData();
                    if ("true".equals(bean.isSuccess())){
                        categorizeProductAdapter = new CategorizeProductAdapter(getChildFragmentManager(),bean.getData());
                        viewPager.setAdapter(categorizeProductAdapter);
                        listMenus =bean.getData();


                            shopViews = new View[listMenus.size()];


                        listMenuTextViews = new TextView[listMenus.size()];
                        for (int i = 0; i < listMenus.size(); i++) {
                            View view = LayoutInflater.from(getContext()).inflate(R.layout.itemview_categorize_listmenus, null);
                            // 给每个View设定唯一标识
                            view.setId(i);
                            // 给每个view添加点击监控事件
                            view.setOnClickListener(ListItemMenusClickListener);
                            // 获取到左侧栏的的TextView的组件
                            TextView textView = (TextView) view.findViewById(R.id.textView);
                            textView.setText(listMenus.get(i).getCategoryName());
                            toolsLayout.addView(view);
                            // 传入的是地址不是复制的值
                            listMenuTextViews[i] = textView;
                            shopViews[i] = view;
                        }
                        changeTextColor(0);
                    }else {
                     ToastUtils.showToast(bean.getMsg());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    private void changeTextColor(int position) {
        for (int i = 0; i < listMenus.size(); i++) {
            if (position != i) {
                listMenuTextViews[i].setBackgroundColor(0x00000000);
                listMenuTextViews[i].setTextColor(0xFF000000);
            }
        }
        listMenuTextViews[position].setBackgroundColor(0xFFFFFFFF);
        listMenuTextViews[position].setTextColor(0xFFFF5D5E);
    }

    private void changeTextLocation(int clickPosition) {
        int y = (shopViews[clickPosition].getTop());
        // 如果滑动条可以滑动的情况下就把点击的视图移动到顶部
        tools_scrollView.smoothScrollTo(0, y);

    }

    View.OnClickListener ListItemMenusClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(v.getId());
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                ToastUtils.showToast(item.getTitle().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.classify_fragment_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        restoreState();

    }

    private void restoreState() {
        if (savedState != null) {
            currentItem = savedState.getInt("index");
        }
    }

    private Bundle saveState() {
        Bundle state = new Bundle();
        state.putInt("index", currentItem);
        return state;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }
}
