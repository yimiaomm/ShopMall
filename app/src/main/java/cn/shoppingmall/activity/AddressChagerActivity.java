package cn.shoppingmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;
import cn.qqtheme.framework.util.LogUtils;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.adapter.AddressAdapter;
import cn.shoppingmall.bean.AddressBean;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddressChagerActivity extends BaseActivity {

    @BindView(R.id.tv_left_title)
    TextView tv_leftTitle;
    @BindView(R.id.tv_center_title)
    TextView tv_centerTitle;
    @BindView(R.id.tv_right_title)
    TextView tv_rightTitle;
    @BindView(R.id.et_name)
    EditText et_eame;
    @BindView(R.id.et_tel)
    EditText et_tel;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.rl_setting_address)
    RelativeLayout rl_settingAddress;
    @BindView(R.id.tv_delete_address)
    TextView tv_deleteAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.ll_city)
    RelativeLayout llCity;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    private Intent intent;
    private String ADDRESSBEAN = "ADDRESSBEAN";
    private String BUNDLE = "BUNDLE";
    private Bundle bundle;
    private GreenDaoUtlis daoUtlis;
    private String isAdd = "0";
    private static DataEntity user;
    private String name;
    private String tel;
    private String address;
    private String iPprovince;
    private String iCity;
    private String iCounty;
    private String isCheck="0";
    private static AddressBean.DataEntity dataEntity;

    @Override
    protected void init() {
        intent = getIntent();
        tv_leftTitle.setText("返回");
        tv_rightTitle.setText("保存");
        if (null != intent) {
            tv_centerTitle.setText("修改地址");
            bundle = intent.getExtras();
            if (null != bundle) {
                dataEntity = (AddressBean.DataEntity) bundle.getSerializable(BUNDLE);
                if (dataEntity != null) {
                    et_eame.setText(dataEntity.getName());
                    et_tel.setText(dataEntity.getTel());
                    etAddress.setText(dataEntity.getAddr());

                    iPprovince = dataEntity.getProvince();
                    iCity = dataEntity.getCity();
                    iCounty = dataEntity.getCounty();
                    tv_city.setText(iPprovince+" "+iCity +" "+iCounty );
                    isAdd = dataEntity.getID();
                }
            } else {
                tv_centerTitle.setText("添加地址");
            }
        }
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isCheck = "1";
                    if (dataEntity!=null){
                        settingAddress(user.getToken(),user.getUserId(),dataEntity.getID());
                    }
                }else {
                    isCheck = "0";
                }
            }
        });
    }

    @Override
    protected int viewId() {
        return R.layout.activity_address_chager;
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = new GreenDaoUtlis(this).query();
    }

    @OnClick({R.id.tv_left_title, R.id.tv_right_title, R.id.ll_city, R.id.tv_city, R.id.rl_setting_address, R.id.tv_delete_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left_title:
                finish();
                break;
            case R.id.tv_right_title:
                name = et_eame.getText().toString().trim();
                tel = et_tel.getText().toString().trim();
                address = etAddress.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    ToastUtils.showToast("请填写收货人");
                    return;
                }
                if (TextUtils.isEmpty(tel)) {
                    ToastUtils.showToast("请填写收货人电话");
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    ToastUtils.showToast("请填写收货人详细地");
                    return;
                }

                if (TextUtils.isEmpty(iPprovince)) {
                    ToastUtils.showToast("请填写收货人地区");
                    return;
                }
                if (TextUtils.isEmpty(iCity)) {
                    ToastUtils.showToast("请填写收货人市");
                    return;
                }
                if (TextUtils.isEmpty(iCounty)) {
                    ToastUtils.showToast("请填写收货人县/区");
                    return;
                }
                if ("0".equals(isAdd)) {
                    addAddress(isAdd);
                } else {
                    addAddress(isAdd);
                }


                finish();
                break;
            case R.id.ll_city:
                onAddress2Picker(tv_city);
                break;
            case R.id.rl_setting_address:

                break;
            case R.id.tv_delete_address:
                if (dataEntity==null){
                    return;
                }
                deleteAddress(user.getToken(),user.getUserId(),dataEntity.getID());
                finish();
                break;
        }
    }

    private void addAddress(String Id) {
//    ID (integer, optional): *收货地址序号(0为添加,正数为更新),
//            UserId (string, optional): *归属用户名,
//            Name (string, optional): *姓名,
//            Tel (string, optional): *电话,
//            Postcode (string, optional): *邮编,
//            Addr (string, optional): *地址,
//            Province (string, optional): *省,
//            City (string, optional): *市,
//            County (string, optional): *县区,
//            IsDefault (integer, optional): *是否默认(1是0否),
//            Token (string, optional): *登录凭证
        Map<String, String> map = new HashMap<>();
        map.put("ID", Id);
        map.put("UserId", user.getUserId());
        map.put("Name", name);
        map.put("Tel", tel);
        map.put("Addr", address);
        map.put("Province", iPprovince);
        map.put("City", iCity);
        map.put("County", iCounty);
        map.put("IsDefault",isCheck);
        map.put("Token", user.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        addAddressResult(RetrofitHttp.getRetrofit(builder.build()).addAddress(map));
    }

    private void addAddressResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {

            private String result;

            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    result = response.body().string().toString();
                    Log.e("s",result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public static void settingAddress(String token,String userId,String addressId) {
        if (dataEntity==null){
            return;
        }
        Map<String, String> map = new HashMap<>();
//        ID (integer, optional): *收货地址序号(要设为默认或删除的序号),
//                UserId (string, optional): *归属用户名,
//                Token (string, optional): *登录凭证,
        map.put("ID",dataEntity.getID());
        map.put("UserId",user.getUserId());
        map.put("Token",user.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        settingAddressResult(RetrofitHttp.getRetrofit(builder.build()).setDefault(map));
    }



    private static void settingAddressResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {

            private String result;

            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    result = response.body().string().toString();
                    AddressBean addressBean = MyApplication.gson.fromJson(result, AddressBean.class);
                    if ("true".equals(addressBean.getSuccess())) {
                        ToastUtils.showToast(addressBean.getMsg());
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

    private  void deleteAddress(String token,String userId,String addressId) {

        Map<String, String> map = new HashMap<>();
        map.put("ID",addressId);
        map.put("UserId",userId);
        map.put("Token",token);
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        deleteAddressResult(RetrofitHttp.getRetrofit(builder.build()).delAddress(map));
    }

    private void deleteAddressResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {
            private String result;
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    result = response.body().string().toString();
                    AddressBean addressBean = MyApplication.gson.fromJson(result, AddressBean.class);
                    if ("true".equals(addressBean.getSuccess())) {
                        ToastUtils.showToast(addressBean.getMsg());
                        finish();
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

    public void onAddress2Picker(View view) {
        try {
            ArrayList<Province> data = new ArrayList<>();
            String json = ConvertUtils.toString(getAssets().open("city.json"));
            data.addAll(JSON.parseArray(json, Province.class));
            AddressPicker picker = new AddressPicker(this, data);
            picker.setShadowVisible(true);
//            picker.setTextSizeAutoFit(false);
            picker.setHideProvince(false);
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(Province province, City city, County county) {
                    tv_city.setText(province.getAreaName() + " " + city.getName() + " " + county.getName());
                    iPprovince = province.getAreaName();
                    iCity = city.getName();
                    iCounty = county.getName();
                }
            });
            picker.show();
        } catch (Exception e) {
            ToastUtils.showToast(LogUtils.toStackTraceString(e));
        }
    }
}
