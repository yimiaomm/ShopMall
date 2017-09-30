package cn.shoppingmall.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewPropertyAnimator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shoppingmall.MainActivity;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.adapter.ProductDetailAdapter;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.bean.ProductDetailsBean;
import cn.shoppingmall.fragment.BaseFragment;
import cn.shoppingmall.fragment.productFrag.PicDetailsFragment;
import cn.shoppingmall.fragment.productFrag.ProductFragment;
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

import static android.R.attr.data;
import static cn.shoppingmall.fragment.productFrag.ProductFragment.productSum;

public class ProductDetailsActivity extends BaseActivity {


    @BindView(R.id.tv_product)
    TextView tvProduct;
    @BindView(R.id.tv_pic_details)
    TextView tvPicDetails;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.rl_header)
    RelativeLayout rlHeader;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.view_pager)
    LazyViewPager viewPager;
    @BindView(R.id.home_layout)
    LinearLayout homeLayout;
    @BindView(R.id.shop_car_layout)
    LinearLayout shopCarLayout;
    @BindView(R.id.add_product_shopping)
    TextView addProductShopping;
    private int line_width;
    private String productId;
    private ProductDetailsBean prodBean;


    protected void init() {
        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");
        getProduct();
        line_width = getViewWidth(rlHeader) / 2;
        line.getLayoutParams().width = line_width;
        line.setBackgroundColor(getResources().getColor(R.color.black));
        line.requestLayout();
        viewPager.setOnPageChangeListener(simpleOnPageChangeListener);
    }

    private void getProduct() {
//        ID (integer, optional): *商品序号,
//                timestamp (string, optional): *时间戳,
//                nonce (string, optional): 随机数,
//                signature (string, optional): 加密签名
        Map<String, String> map = new HashMap<>();
        map.put("ID", productId);
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getProductResult(RetrofitHttp.getRetrofit(builder.build()).getProductsContent(map));
    }

    private void getProductResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    prodBean = MyApplication.gson.fromJson(result, ProductDetailsBean.class);
                    if ("true".equals(prodBean.getSuccess())) {
                        List<BaseFragment> list = new ArrayList<BaseFragment>();
                        list.add(ProductFragment.newInstance(prodBean));
                        list.add(PicDetailsFragment.newInstance(prodBean));
                        ProductDetailAdapter adapter = new ProductDetailAdapter(getSupportFragmentManager(), list);
                        viewPager.setAdapter(adapter);
                        ToastUtils.showToast(prodBean.getMsg());
                    } else {
                        ToastUtils.showToast(prodBean.getMsg());
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

    LazyViewPager.SimpleOnPageChangeListener simpleOnPageChangeListener = new LazyViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            float tagerX = position * line_width + positionOffset * line_width;

            ViewPropertyAnimator.animate(line).translationX(tagerX)
                    .setDuration(0);
        }
    };

    private int getViewWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        return width;
    }

    @Override
    protected int viewId() {
        return R.layout.activity_product_details;
    }

    @OnClick({R.id.tv_pic_details, R.id.tv_back, R.id.home_layout, R.id.shop_car_layout, R.id.add_product_shopping, R.id.tv_product})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_product:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_pic_details:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.home_layout:
                finish();
                MainActivity.pager.setCurrentItem(0);
                break;
            case R.id.shop_car_layout:
                finish();
                MainActivity.pager.setCurrentItem(2);
                break;
            case R.id.add_product_shopping:
                addShopCar();
                break;
        }
    }

    private void addShopCar() {
        DataEntity user = new GreenDaoUtlis(this).query();
        if (user==null){
            return;
        }

//        UserId (string, optional): *用户名 ,
//                ProdId (string, optional): *商品编码 ,
//                BuyNum (integer, optional): *商品数量 ,
//                Token (string, optional): *登录凭证 ,
//                timestamp (string, optional): *时间戳 ,
//                nonce (string, optional): 随机数 ,
//                signature (string, optional): 加密签名
        String str = ProductFragment.productSum.getText().toString();
        if (TextUtils.isEmpty(str)){
            str = "1";
        }
        if (prodBean != null) {
            DataEntity data = new GreenDaoUtlis(this).query();
            if (!TextUtils.isEmpty(productId)) {
                Map<String, String> map = new HashMap<>();
                map.put("UserId", data.getUserId());
                map.put("ID",productId);
                map.put("BuyNum",str);
                map.put("Token", data.getToken());
                map = NetUitls.getHashMapData(map);
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                AddShopCarResult(RetrofitHttp.getRetrofit(builder.build()).addShopCart(map));
            }
        }
    }

    private void AddShopCarResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
//                    {"success":true,"msg":"添加成功","data":null,"errcode":""}
                    JSONObject object = new JSONObject(result);
                    String success = object.has("success") ? object.getString("success") : "";
                    String msg = object.has("msg") ? object.getString("msg") : "";
                    if ("true".equals(success)) {
                        ToastUtils.showToast(msg);
                    } else {
                        ToastUtils.showToast(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
