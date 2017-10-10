package cn.shoppingmall.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.bean.CityBean;
import cn.shoppingmall.bean.CountyBean;
import cn.shoppingmall.bean.Provincebean;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.BaseUtils;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import cn.shoppingmall.view.SpinerPopWindow;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.prompt;
import static android.media.CamcorderProfile.get;

/**
 * author：Anumbrella
 * Date：16/5/28 下午8:56
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.btn_ok)
    TextView btnOk;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.et_password_again)
    EditText etPasswordAgain;
    @BindView(R.id.et_people_num)
    EditText etPeopleNum;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.register_info1)
    TextView registerInfo1;
    @BindView(R.id.nest_scrool_view)
    NestedScrollView nest_scrool_view;
    private String phone;

    private String password;

    private String password_again;

    private String userName;
    private ProgressDialog mDialog;

    @BindView(R.id.btn_back)
    Button btn_back;

    @BindView(R.id.service_text)
    TextView service_text;
    private String perpleNum;
    private SpinerPopWindow mSpinerPopWindow;
    private SpinerPopWindow.OnItemClickListeners listeners;
    private List<Provincebean.DataEntity> provincedata;
    private List<CityBean.DataEntity> cityList;
    private List<CountyBean.DataEntity> countyList;
    private String provinceId;
    private String cityId;
    private String counId;


    @Override
    protected void init() {
        showDialog();
    }

    @Override
    protected int viewId() {
        return R.layout.activity_register;
    }

    private void getData() {
        phone = tvPhone.getText().toString();
        password = tvPassword.getText().toString();
        password_again = etPasswordAgain.getText().toString();
        perpleNum = etPeopleNum.getText().toString();
        if (phone.equals("")) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!BaseUtils.checkPhoneNumber(phone)) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.equals("")) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            ToastUtils.showToast("密码不能小于6位");
            return;
        }
        if ((password_again.equals("") || !password.equals(password_again))) {
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
        }

        if (perpleNum.equals("")) {
            ToastUtils.showToast("推荐码不能为空");
            return;
        }
        if (TextUtils.isEmpty(provinceId) || TextUtils.isEmpty(cityId) || TextUtils.isEmpty(counId)) {
            ToastUtils.showToast("请填写完整地区");
            return;
        }
        doRegister(password, phone, perpleNum);
    }

    private void doRegister(String pswd, String tel, String refuerid) {
//        ProId (integer, optional): *省 ,
//                CityId (integer, optional): *市 ,
//                CountyId (integer, optional): *县区 ,
        Map<String, String> map = new HashMap();
        map.put("UserPwd", pswd);
        map.put("UserTel", tel);
        map.put("RefUserId", refuerid);
        map.put("UserSource", "4");
        map.put("ProId", provinceId);
        map.put("CityId", cityId);
        map.put("CountyId", counId);
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        prompt(RetrofitHttp.getRetrofit(builder.build()).registerUser(map));
    }

    private void prompt(Call<ResponseBody> register) {
        register.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
//                    {"success":true,"msg":"恭喜，会员注册成功，已激活","data":null,"errcode":""}
                    JSONObject object = new JSONObject(result);
                    String success = object.has("success") ? object.getString("success") : "";
                    String msg = object.has("msg") ? object.getString("msg") : "";
                    if ("true".equals(success)) {
                        ToastUtils.showToast(msg);
                        finish();
                    } else {
                        ToastUtils.showToast(msg);
                    }
                } catch (Exception io) {

                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RegisterActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mSpinerPopWindow = new SpinerPopWindow(RegisterActivity.this);
        listeners = new SpinerPopWindow.OnItemClickListeners() {
            @Override
            public void setOnItemClicks(AdapterView<?> parent, View view, int position, long id) {
                if (provincedata.size() != 0 && provincedata != null) {
                    provinceId = provincedata.get(position).getProId();
                    String provinceName = provincedata.get(position).getProName();

                    if (provinceId.equals("0")) {
                        return;
                    }
                    etAddress.setText(provinceName);
                    getCity(provinceId);
                }
                if (cityList != null && cityList.size() != 0) {
                    cityId = cityList.get(position).getCityId();
                    String cutyName = cityList.get(position).getCityName();
                    if (cityId.equals("0")) {
                        return;
                    }
                    etAddress.append(" " + cutyName);
                    cityList.clear();
                    mSpinerPopWindow.mAdapter.notifyDataSetChanged();
                    getCounty(cityId);
                }

                if (countyList != null && countyList.size() != 0) {
                    counId = countyList.get(position).getCountyId();
                    String counName = countyList.get(position).getCountyName();
                    if (counId.equals("0")) {
                        return;
                    }
                    etAddress.append(" " + counName);
                    countyList.clear();
                    mSpinerPopWindow.mAdapter.notifyDataSetChanged();
                    mSpinerPopWindow.dismiss();
                }
            }
        };

        mSpinerPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mSpinerPopWindow.list != null) {
                    mSpinerPopWindow.list.clear();
                    mSpinerPopWindow.mAdapter.notifyDataSetChanged();
                }
            }
        });
        getprovince();
    }

    private void specialUpdate() {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                if (mSpinerPopWindow.mAdapter != null)
                    mSpinerPopWindow.mAdapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }

    @OnClick({R.id.service_text, R.id.btn_back, R.id.btn_login, R.id.et_address})
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.service_text:
//                Intent intent = new Intent();
//                intent.setClass(this, ServiceTextActivity.class);
//                startActivity(intent);
                break;
            case R.id.btn_back:
                finish();
                break;
//            case R.id.btn_ok:
//                prompt = true;
//                if (checkUpResult == false) {
//                    checkUpResult = true;
//                }
//                getData();
//                break;
            case R.id.btn_login:
                getData();
                break;
            case R.id.et_address:
                etAddress.getText().clear();
                mSpinerPopWindow.setWidth(nest_scrool_view.getWidth());
                mSpinerPopWindow.setHeight(nest_scrool_view.getHeight() / 2);
                mSpinerPopWindow.showAtLocation(nest_scrool_view, Gravity.BOTTOM, 0, 0);
//                mSpinerPopWindow.showAsDropDown(etAddress);
//                getCity("2");
//                getCounty("2");
                break;
            default:
                break;
        }
    }

    private void getprovince() {
        Map<String, String> map = new HashMap();
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getProvinceResult(RetrofitHttp.getRetrofit(builder.build()).getProvince(map));
    }

    private void getProvinceResult(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    Provincebean beam = MyApplication.gson.fromJson(result, Provincebean.class);
                    if (beam.getSuccess().equals("true")) {
                        provincedata = beam.getData();
                        mSpinerPopWindow.setData(provincedata);
                        mSpinerPopWindow.setOnItemClickLineners(listeners);
                    } else {
                        ToastUtils.showToast(result);
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

    private void getCity(String proId) {
        Map<String, String> map = new HashMap();
        map.put("ProId", proId);
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getCityResult(RetrofitHttp.getRetrofit(builder.build()).getCity(map));
    }

    private void getCityResult(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    CityBean bean = MyApplication.gson.fromJson(result, CityBean.class);
                    if (bean.getSuccess().equals("true")) {
                        cityList = bean.getData();
                        provincedata.clear();
                        mSpinerPopWindow.setData(cityList);
                        specialUpdate();
                        mSpinerPopWindow.setOnItemClickLineners(listeners);
                    } else {
                        ToastUtils.showToast(result);
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

    private void getCounty(String CityId) {
        Map<String, String> map = new HashMap();
        map.put("CityId", CityId);
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getCountyResult(RetrofitHttp.getRetrofit(builder.build()).getCounty(map));
    }

    private void getCountyResult(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    CountyBean bean = MyApplication.gson.fromJson(result, CountyBean.class);
                    if (bean.getSuccess().equals("true")) {
                        countyList = bean.getData();
                        mSpinerPopWindow.setData(countyList);
                        mSpinerPopWindow.setOnItemClickLineners(listeners);
                    } else {
                        ToastUtils.showToast(result);
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
