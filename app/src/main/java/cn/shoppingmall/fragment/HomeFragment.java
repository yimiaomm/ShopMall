package cn.shoppingmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.shoppingmall.MainActivity;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.AllProductActivity;
import cn.shoppingmall.activity.LoginActivity;
import cn.shoppingmall.activity.OrderListActivity;
import cn.shoppingmall.activity.ProductDetailsActivity;
import cn.shoppingmall.adapter.ButtomListAdapter;
import cn.shoppingmall.adapter.HomePagerAdapter;
import cn.shoppingmall.adapter.HorizontalListAdapter;
import cn.shoppingmall.bean.Banner;
import cn.shoppingmall.bean.ButtomListBean;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import cn.shoppingmall.view.NoScroolGridView;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends BaseFragment {


    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.buttom_list)
    NoScroolGridView buttomList;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.tv_more_prod)
    TextView tv_more_prod;
    @BindView(R.id.ll_all)
    LinearLayout ll_all;
    @BindView(R.id.ll_shopcar)
    LinearLayout ll_shopcar;
    @BindView(R.id.ll_order)
    LinearLayout ll_order;
    @BindView(R.id.ll_member)
    LinearLayout ll_member;
    @BindView(R.id.list)
    ViewPager pager;
    @BindView(R.id.search_view)
    SearchView searchView;
    private ButtomListAdapter buttomListAdapter;
    private HorizontalListAdapter horizontalListAdapter;
    private HomePagerAdapter homePagerAdapter;
    private int scrollY = 0;// 标记上次滑动位置
    private View contentView;
    int j = 8;
    private boolean isRefresh = false;
    private String ProductType = "1";
    private List<ButtomListBean.DataEntity> dataEntities, homePagerListBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        if (contentView == null) {
            contentView = scrollView.getChildAt(0);
        }
        buttomListAdapter = new ButtomListAdapter(getActivity().getApplicationContext());
        initRecyclerView();
        initHoruzontalList();
        buttomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.putExtra("productId", dataEntities.get(position).getID() + "");
                startActivity(intent);
            }
        });


        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {

                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (TextUtils.isEmpty(query)){
                    return false;
                }
                Intent intent = new Intent(getActivity(),AllProductActivity.class);
                intent.putExtra("search",query);
                intent.putExtra("ProdType", "-1");
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        slider.stopAutoCycle();

    }

    private void initHoruzontalList() {
        ProductType = "1";
        getHomeprod(ProductType);


    }

    //获取首页广告轮播图
    private void initRecyclerView() {
        Map<String, String> map = new HashMap<>();
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getBanner(RetrofitHttp.getRetrofit(builder.build()).getBanners(map));
    }

    private void getBanner(Call<ResponseBody> responseBodyCall) {
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {

                mDialog.dismiss();
                String result = null;
                try {
                    result = response.body().string().toString();
                    Banner banner = MyApplication.gson.fromJson(result, Banner.class);
                    if ("true".equals(banner.getSuccess())) {
                        List<Banner.DataEntity> data = banner.getData();
                        for (int i = 0; i < data.size(); i++) {
                            Banner.DataEntity dataEntity = data.get(i);
                            TextSliderView textSliderView = new TextSliderView(getActivity());
                            textSliderView.image(dataEntity.getLinkPic());
                            textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                            slider.addSlider(textSliderView);

                            slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            slider.setCustomAnimation(new DescriptionAnimation());
                            slider.setPresetTransformer(SliderLayout.Transformer.Default);
                            slider.setDuration(3000);
                        }
                    } else {
                        ToastUtils.showToast(banner.getMsg());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("result", result);

            }

            @Override
            public void onFailure(Throwable t) {
                mDialog.dismiss();
            }
        });

    }

    /***
     * 获取首页商品的网络请求
     */

    private void getHomeprod(String module) {
        Map<String, String> map = new HashMap<>();
        map.put("Module", module);
        map.put("Num", "8");
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getProduct(RetrofitHttp.getRetrofit(builder.build()).getHomeProd(map));
    }

    private void getProduct(Call<ResponseBody> responseBodyCall) {
        if (ProductType == "2") {
            responseBodyCall.clone();
        }
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    ButtomListBean bean = MyApplication.gson.fromJson(result, ButtomListBean.class);
                    if ("true".equals(bean.getSuccess())) {
                        dataEntities = bean.getData();
                        if (ProductType == "1") {
                            dataEntities = bean.getData();
                            buttomListAdapter.setDatas(dataEntities);
                            buttomList.setAdapter(buttomListAdapter);
                            ProductType = "2";
                            getHomeprod(ProductType);
                        }
                        if (ProductType == "2") {
                            homePagerListBean = bean.getData();
                            homePagerAdapter = new HomePagerAdapter(getChildFragmentManager(), homePagerListBean, result);
                            pager.setAdapter(homePagerAdapter);
                        }
                        scrollView.smoothScrollTo(0, 0);
                    } else {
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


    @OnClick({R.id.ll_all, R.id.ll_shopcar, R.id.ll_order, R.id.ll_member, R.id.tv_more_prod})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ll_all:
            case R.id.tv_more_prod:
                intent.setClass(getActivity(), AllProductActivity.class);
                intent.putExtra("ProdType", "-1");
                startActivity(intent);
                break;
            case R.id.ll_shopcar:
                MainActivity.pager.setCurrentItem(1);
                break;
            case R.id.ll_order:
                DataEntity userinfo = new GreenDaoUtlis(cxt).query();
                if (null==userinfo){
                    ToastUtils.showToast("尚未登陆");
                    intent.setClass(cxt, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent.setClass(cxt, OrderListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_member:
                MainActivity.pager.setCurrentItem(3);
                break;

        }
    }
}




