package cn.shoppingmall.fragment.productFrag;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.LoginActivity;
import cn.shoppingmall.activity.MyAddress;
import cn.shoppingmall.bean.AddressBean;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.bean.ProductDetailsBean;
import cn.shoppingmall.fragment.BaseFragment;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends BaseFragment {

    @BindView(R.id.slider)
    SliderLayout slider;
    @BindView(R.id.tv_product_content)
    TextView tvProductContent;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_product_style)
    TextView tvProductStyle;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_is_stock)
    TextView tvIsStock;
    @BindView(R.id.tv_freight)
    TextView tvFreight;

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.shopping_product_sum_sub)
    ImageView shoppingProductSumSub;

    public static TextView productSum;
    @BindView(R.id.shopping_product_sum_add)
    ImageView shoppingProductSumAdd;
    private DataEntity entity;

    public static ProductFragment newInstance(ProductDetailsBean productDetailsBean) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, productDetailsBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product;
    }

    @Override
    public void init() {
        productSum = (TextView) view.findViewById(R.id.shopping_product_sum);
        Bundle bundle = getArguments();
        ProductDetailsBean bean = (ProductDetailsBean) bundle.getSerializable(ARG_PARAM1);
        ProductDetailsBean.DataEntity data = bean.getData();
        String srtPrice3 = data.getPrice3();
        String strAmout_stock = data.getAmount_Stock();
        Double douPrice3;
        Integer doustrAmout_stock;
        try {
            douPrice3 = Double.parseDouble(srtPrice3);
            doustrAmout_stock = Integer.parseInt(strAmout_stock);
        } catch (Exception e) {
            douPrice3 = 0.0;
            doustrAmout_stock = 0;
        }
        if (douPrice3 <= 0) {
            tvFreight.setText("免运费");
        } else {
            tvFreight.setText("￥"+srtPrice3);
        }
        if (doustrAmout_stock <= 0) {
            tvIsStock.setText("断货");
        } else {
            tvIsStock.setText("有货");
        }
        tvProductStyle.setText(data.getSpec());
        List<ProductDetailsBean.DataEntity.ProdImgsEntity> list = data.getProdImgs();
        for (int i = 0; i < list.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView.image(list.get(i).getPicUrl());
            slider.addSlider(textSliderView);
        }
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setPresetTransformer(SliderLayout.Transformer.Default);
        slider.setDuration(3000);
        tvProductContent.setText(data.getProdName());
        tvPrice.setText("￥"+data.getPrice2());
    }



    @Override
    public void onStart() {
        super.onStart();
        getAddress();
    }

    private void getAddress() {
        entity = new GreenDaoUtlis(getActivity()).queryDefult();
        if (entity == null) {
            tvAddress.setText("去添加收货地址");
            return;
        }
        Map<String, String> map = new HashMap<>();
//        UserId (string, optional): *用户名,
//                Token (string, optional): *登录凭证,
        map.put("UserId", entity.getUserId());
        map.put("Token", entity.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getAddressResult(RetrofitHttp.getRetrofit(builder.build()).getAddressList(map));
    }

    private void getAddressResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    AddressBean addressBean = MyApplication.gson.fromJson(result, AddressBean.class);
                    if ("true".equals(addressBean.getSuccess())) {
                        List<AddressBean.DataEntity> list = addressBean.getData();
                        for (int i = 0; i < list.size(); i++) {
                            if ("1".equals(list.get(i).getIsDefault())) {
                                tvAddress.setText(list.get(i).getCity() + " " + list.get(i).getCity() + " " + list.get(i).getCounty() + "\n" + list.get(i).getAddr());
                            } else {
                                tvAddress.setText(list.get(0).getCity() + " " + list.get(0).getCity() + " " + list.get(0).getCounty() + "\n" + list.get(0).getAddr());
                            }
                        }

                    } else {
                        ToastUtils.showToast(addressBean.getMsg());
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

    @OnClick({R.id.shopping_product_sum_sub, R.id.shopping_product_sum_add,R.id.tv_product_style, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopping_product_sum_sub:
                if (Integer.parseInt(productSum.getText().toString()) > 1) {
                    productSum.setText(String.valueOf(Integer.parseInt(productSum.getText().toString()) - 1));
                }
                break;
            case R.id.shopping_product_sum_add:
                productSum.setText(String.valueOf(Integer.parseInt(productSum.getText().toString()) + 1));
                break;
            case R.id.tv_product_style:
                break;
            case R.id.tv_address:
                if (entity==null){
                    ToastUtils.showToast("尚未登陆");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                startActivity(new Intent(getActivity(), MyAddress.class));
                break;
        }
    }
}
