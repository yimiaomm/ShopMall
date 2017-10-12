package cn.shoppingmall.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.nineoldandroids.view.ViewPropertyAnimator;
import com.yinglan.alphatabs.AlphaTabView;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.bean.OrderCountBean;
import cn.shoppingmall.fragment.orderFragment.NotPayFragmet;
import cn.shoppingmall.fragment.orderFragment.OrderAllFragment;
import cn.shoppingmall.fragment.orderFragment.OrderBaseFragment;
import cn.shoppingmall.fragment.orderFragment.OrderCompled;
import cn.shoppingmall.fragment.orderFragment.OrderPaymentFragment;
import cn.shoppingmall.fragment.orderFragment.OrderUsingFragment;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import cn.shoppingmall.view.LazyViewPager;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cn.shoppingmall.MainActivity.pager;

/**
 * Created by ${易淼} on 2017/8/30.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class OrderListActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    LazyViewPager viewPager;
    @BindView(R.id.alph_view_all)
    AlphaTabView alphViewAll;
    @BindView(R.id.alph_view_noPay)
    AlphaTabView alphViewNoPay;
    @BindView(R.id.alph_view_shift)
    AlphaTabView alphViewShift;
    @BindView(R.id.alph_view_compled)
    AlphaTabView alphViewCompled;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;
    @BindView(R.id.line)
    View line;
    private List fragments;
    private int line_width;
    public static final Map<Integer, OrderBaseFragment> FRAGMENT_MAP_ORDER = new HashMap<>();
    private DataEntity userinfo;

    @Override
    public void init() {
        OrderAdapter orderAdapter = new OrderAdapter(getSupportFragmentManager());
        viewPager.setAdapter(orderAdapter);
        fragments = new ArrayList();
        fragments.add(new OrderAllFragment());
        fragments.add(new NotPayFragmet());
        fragments.add(new OrderPaymentFragment());
        fragments.add(new OrderCompled());
        line_width = getWindowManager().getDefaultDisplay().getWidth()
                / fragments.size();
        line.getLayoutParams().width = line_width;
        line.setBackgroundColor(getResources().getColor(R.color.red));
        line.requestLayout();
//        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(simpleOnPageChangeListener);
    }

    @Override
    protected int viewId() {
        return R.layout.activity_order_list;
    }


    LazyViewPager.SimpleOnPageChangeListener simpleOnPageChangeListener = new LazyViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            float tagerX = position * line_width + positionOffsetPixels / fragments.size();
            ViewPropertyAnimator.animate(line).translationX(tagerX)
                    .setDuration(0);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        userinfo = new GreenDaoUtlis(this).queryDefult();
        if (userinfo ==null){
            return;
        }
        getOrderCount();
    }

    private void getOrderCount() {
//            UserId (string, optional): *用 户名,
//            Token (string, optional): *登录凭证,
//            timestamp (string, optional): *时间戳,
//            nonce (string, optional): 随机数,
//            signature (string, optional): 加密签名
        Map<String, String> map = new HashMap<>();
        map.put("UserId",userinfo.getUserId());
        map.put("Token",userinfo.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getCount(RetrofitHttp.getRetrofit(builder.build()).getOrderCount(map));
    }

    private void getCount(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    OrderCountBean bean = MyApplication.gson.fromJson(result,OrderCountBean.class);
                    OrderCountBean.DataEntity data =   bean.getData();
                   if (data.getDataClosed()>0||data.getDataFinished()>0){
                       alphViewCompled.showNumber(data.getDataClosed()+data.getDataFinished());

                   }else {
                       alphViewCompled.removeShow();
                   }
                    if (data.getDataCount()>0){
                        alphViewAll.showNumber(data.getDataCount());
                    }else {
                        alphViewAll.removeShow();
                    }
                    if (data.getDataDelivered()>0||data.getDataPaid()>0){
                        alphViewShift.showNumber(data.getDataDelivered()+data.getDataPaid());
                    }else {
                        alphViewShift.removeShow();
                    }
                    if (data.getDataNoPay()>0){
                        alphViewNoPay.showNumber(data.getDataNoPay());
                    }else {
                        alphViewNoPay.removeShow();
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



    @OnClick({R.id.alph_view_all, R.id.alph_view_noPay, R.id.alph_view_shift, R.id.alph_view_compled, R.id.alphaIndicator, R.id.line})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alph_view_all:
                viewPager.setCurrentItem(0);
                break;
            case R.id.alph_view_noPay:
                viewPager.setCurrentItem(1);
                break;
            case R.id.alph_view_shift:
                viewPager.setCurrentItem(2);
                break;
            case R.id.alph_view_compled:
                viewPager.setCurrentItem(3);
                break;
            case R.id.alphaIndicator:
                break;
            case R.id.line:
                break;
        }
    }

//    public class LoginAdapter extends FragmentPagerAdapter {
//        public LoginAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return (Fragment) fragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragments.size();
//        }
//    }

    public class OrderAdapter extends FragmentPagerAdapter {
        public OrderAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            OrderBaseFragment fragment = FRAGMENT_MAP_ORDER.get(position);
            if (fragment == null) {
                if (position == 0) {
                    fragment = new OrderAllFragment();
                } else if (position == 1) {
                    fragment = new NotPayFragmet();
                } else if (position == 2) {
                    fragment = new OrderPaymentFragment();
                } else if (position==3){
                    fragment = new OrderUsingFragment();
                }
            }
            if (fragment != null) {
                FRAGMENT_MAP_ORDER.put(position, fragment);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }


}
