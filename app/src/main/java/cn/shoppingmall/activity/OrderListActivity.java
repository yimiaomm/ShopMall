package cn.shoppingmall.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nineoldandroids.view.ViewPropertyAnimator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shoppingmall.R;
import cn.shoppingmall.fragment.BaseFragment;
import cn.shoppingmall.fragment.orderFragment.OrderAllFragment;
import cn.shoppingmall.fragment.orderFragment.OrderBaseFragment;
import cn.shoppingmall.fragment.orderFragment.OrderPaymentFragment;
import cn.shoppingmall.fragment.orderFragment.OrderUsingFragment;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.view.LazyViewPager;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ${易淼} on 2017/8/30.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class OrderListActivity extends BaseActivity {


    @BindView(R.id.tv_using)
    TextView tvUsing;
    @BindView(R.id.tv_payment)
    TextView tvPayment;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.viewPager)
    LazyViewPager viewPager;
    private List fragments;
    private int line_width;
    public static final Map<Integer, OrderBaseFragment> FRAGMENT_MAP_ORDER = new HashMap<>();

    @Override
    public void init() {

        OrderAdapter orderAdapter = new OrderAdapter(getSupportFragmentManager());
        viewPager.setAdapter(orderAdapter);
        fragments = new ArrayList();
        fragments.add(new OrderAllFragment());
        fragments.add(new OrderUsingFragment());
        fragments.add(new OrderPaymentFragment());
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

private void getOrderCount(){
//            UserId (string, optional): *用户名,
//            Token (string, optional): *登录凭证,
//            timestamp (string, optional): *时间戳,
//            nonce (string, optional): 随机数,
//            signature (string, optional): 加密签名
    Map<String,String>map = new HashMap<>();
    map.put("UserId","");
    map.put("Token","");
    map = NetUitls.getHashMapData(map);
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    getCount(RetrofitHttp.getRetrofit(builder.build()).getOrderCount(map));
}
private void getCount(Call<ResponseBody>responseBody){
    responseBody.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Response<ResponseBody> response) {
            try {
                String result = response.body().string().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Throwable t) {

        }
    });
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
                    fragment = new OrderUsingFragment();
                } else if (position == 2) {
                    fragment = new OrderPaymentFragment();
                }
            }
            if (fragment != null) {
                FRAGMENT_MAP_ORDER.put(position, fragment);
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }


    @OnClick({R.id.tv_using, R.id.tv_payment, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_using:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_payment:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_finish:
                viewPager.setCurrentItem(2);
                break;
        }
    }

}
