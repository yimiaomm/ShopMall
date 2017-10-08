package cn.shoppingmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.adapter.CarDetailAdapter;
import cn.shoppingmall.bean.AddressBean;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.bean.ShopCarBean;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.data;
import static cn.shoppingmall.R.id.mRecyclerView;

public class ShopCarDeatil extends BaseActivity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_address_and_contast)
    TextView tvAddressAndContast;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.tv_noAddress)
    TextView tvNoAddress;
    @BindView(R.id.ll_city)
    LinearLayout llCity;
    @BindView(R.id.ll_contast)
    LinearLayout llContast;
    private DataEntity entity;
    private String addID;
    private TextView tv_shou;
    private NestedScrollView nest_scrool_view;
    @Override
    protected void init() {
        tv_shou = (TextView) findViewById(R.id.tv_shou);
        nest_scrool_view = (NestedScrollView) findViewById(R.id.nest_scrool_view);
        nest_scrool_view.smoothScrollTo(0,0);
        ShopCarBean.DataEntity data = (ShopCarBean.DataEntity) getIntent().getBundleExtra("bundle").getSerializable("CartBean");
        tvPrice.setText(data.getTotalPrice());
        List<ShopCarBean.DataEntity.ShopCartListEntity> list = data.getShopCartList();
        List<ShopCarBean.DataEntity.ShopCartListEntity> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsCheck().equals("true")) {
                newList.add(list.get(i));
            }
        }
        CarDetailAdapter adapter = new CarDetailAdapter(this,newList);

        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAddress();
    }

    @Override
    protected int viewId() {
        return R.layout.activity_shop_car_deatil;
    }


    @OnClick({R.id.tv_back, R.id.tv_address_and_contast, R.id.tv_sure, R.id.tv_noAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_noAddress:
                startActivity(new Intent(this, MyAddress.class));
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_address_and_contast:
                startActivity(new Intent(this, MyAddress.class));
                break;
            case R.id.tv_sure:
                creatOrder();
                break;
        }
    }

    private void creatOrder() {
        if (TextUtils.isEmpty(addID)) {
            ToastUtils.showToast("请添加收货地址");
            return;
        }

        Map<String, String> map = new HashMap<>();
//        UserId (string, optional):*用户名,
//                AddrID (integer, optional):*收货地址ID,
//                Token (string, optional):*登录凭证,
//                timestamp (string, optional):*时间戳,
//                nonce (string, optional):随机数,
//                signature (string, optional):加密签名
        map.put("UserId", entity.getUserId());
        map.put("AddrID", addID);
        map.put("Token", entity.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        createOrderResult(RetrofitHttp.getRetrofit(builder.build()).creatOrder(map));
    }

    private void createOrderResult(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    JSONObject object = new JSONObject(result);
                    String success = object.has("success")?object.getString("success"):"";
                    String msg = object.has("msg")?object.getString("msg"):"";
                    String data = object.has("data")?object.getString("data"):"";
                    if ("true".equals(success)){
                        Intent intent = new Intent(ShopCarDeatil.this,OrderListActivity.class);
                        intent.putExtra("orderID",data);
                        startActivity(intent);
                        finish();
                    }else {
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


    private void getAddress() {
        entity = new GreenDaoUtlis(this).queryDefult();
        if (entity == null) {
            tvAddress.setText("尚未登陆");
            startActivity(new Intent(this, LoginActivity.class));
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
                        if (addressBean.getData().size()<=0) {
                            addID = null;
                            tvNoAddress.setVisibility(View.VISIBLE);
                            llCity.setOrientation(View.GONE);
                            llContast.setVisibility(View.GONE);
                            tv_shou.setVisibility(View.GONE);
                        }else {
                            tvNoAddress.setVisibility(View.GONE);
                            llCity.setOrientation(View.VISIBLE);
                            llContast.setVisibility(View.VISIBLE);
                            tv_shou.setVisibility(View.VISIBLE);
                        }


                        List<AddressBean.DataEntity> list = addressBean.getData();
                        for (int i = 0; i < list.size(); i++) {
                            if ("1".equals(list.get(i).getIsDefault())) {
                                addID = list.get(i).getID();
                                tvAddress.setText(list.get(i).getCity() + " " + list.get(i).getCity() + " " + list.get(i).getCounty());
                                tvAddressAndContast.setText(list.get(i).getAddr() + "\n" + list.get(i).getName() + "   " + list.get(i).getTel());
                            } else {
                                tvAddress.setText(list.get(0).getCity() + " " + list.get(0).getCity() + " " + list.get(0).getCounty());
                                addID = list.get(0).getID();
                                tvAddressAndContast.setText(list.get(0).getAddr() + "\n" + list.get(0).getName() + "   " + list.get(0).getTel());
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
}
