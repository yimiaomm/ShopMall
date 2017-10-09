package cn.shoppingmall.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

    private boolean prompt = true;

    private boolean checkUpResult = true;


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
        if (phone.equals("") && prompt) {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            checkUpResult = false;
            prompt = false;
        }

        if (!BaseUtils.checkPhoneNumber(phone) && prompt) {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            checkUpResult = false;
            prompt = false;
        }

        if (password.equals("") && prompt) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            checkUpResult = false;
            prompt = false;
        }
        if (password.length()<6&&prompt){
            ToastUtils.showToast("密码不能小于6位");
            checkUpResult = false;
            prompt = false;
        }
        if ((password_again.equals("") || !password.equals(password_again)) && prompt) {
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
            checkUpResult = false;
            prompt = false;
        }

        if (perpleNum.equals("") && prompt) {
            ToastUtils.showToast("推荐码不能为空");
            checkUpResult = false;
            prompt = false;
        }

    }
    private  void specialUpdate(final String str) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.obj = str;
                msg.what=1;
                handler.sendMessage(msg);
            }
        }).start();


    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                String strs = (String) msg.obj;
//                mSpinerPopWindow.setText(strs);
            }
        }
    };
    private void doRegister(String pswd,String tel,String refuerid) {
//                UserId(string, optional):用户名
//               UserPwd(string, optional):登录密码(必填, 不少于6位)
//                UserTel(string, optional):手机(必填),
//                LevelId(integer, optional):用户级别(必填),
//                UserName(string, optional):姓名,
//                Email(string, optional):电子邮件,
//                IdenCard(string, optional):身份证号,
//                UserAddress(string, optional):家庭地址,
//                BankName(string, optional):开户行名称,
//                BankAccount(string, optional):银行卡账号,
//                ProId(integer, optional):省(允许null, 默认0),
//                CityId(integer, optional):市(允许null, 默认0),
//                CountyId(integer, optional):县区(允许null, 默认0),
//                RefUserId(string, optional):推荐人(必填),
//                NodeUserId(string, optional):接点人(允许null),
//                ActUserId(string, optional):激活人(允许null, 默认"admin"),
//                PayEMX(string, optional):支付账户(允许null, 默认1, 4),
//                UserSource(integer, optional):用户来源(必填 1. webPC(默认) 2. webMobile 3. 微信 4. Android 5.IOS),
//               timestamp(string, optional):时间戳,
//                nonce(string, optional):随机数,
//                signature(string, optional):加密签名
            Map<String, String> map = new HashMap();
            map.put("UserPwd",pswd);
            map.put("UserTel",tel);
            map.put("RefUserId",refuerid);
            map.put("UserSource", "4");
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
                    String success = object.has("success")?object.getString("success"):"";
                    String msg = object.has("msg")?object.getString("msg"):"";
                    if ("true".equals(success)){
                        ToastUtils.showToast(msg);
                        finish();
                    }else {
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
                if (provincedata.size()!=0&&provincedata!=null){
                    provinceId = provincedata.get(position).getProId();
                    String provinceName = provincedata.get(position).getProName();

                    if (provinceId.equals("0")){
                        return;
                    }
                    etAddress.setText(provinceName);
                    mSpinerPopWindow.mAdapter.notifyDataSetChanged();
                    provincedata.clear();
                    getCity(provinceId);
                }
                if (cityList!=null&&cityList.size()!=0){
                    String cityId = cityList.get(position).getCityId();
                    String cutyName = cityList.get(position).getCityName();
                    if (cityId.equals("0")){
                        return;
                    }
                    etAddress.append(" "+cutyName);
                    cityList.clear();
                    mSpinerPopWindow.mAdapter.notifyDataSetChanged();
                    getCounty(cityId);
                }

                if (countyList!=null&&countyList.size()!=0){
                    String counId = countyList.get(position).getCountyId();
                    String counName = countyList.get(position).getCountyName();
                    if (counId.equals("0")){
                        return;
                    }
                    etAddress.append(" "+counName);
                   mSpinerPopWindow.dismiss();
                }
            }
        };
    }

    @OnClick({R.id.service_text, R.id.btn_back,R.id.btn_login,R.id.et_address})
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
                if (prompt==true&&checkUpResult==true){
                    doRegister(password,phone,perpleNum);
                }
                break;
            case R.id.et_address:
                getprovince();
                mSpinerPopWindow.setWidth(nest_scrool_view.getWidth());
                mSpinerPopWindow.setHeight(nest_scrool_view.getHeight()/2);
                mSpinerPopWindow.showAtLocation(nest_scrool_view, Gravity.BOTTOM,0,0);
//                mSpinerPopWindow.showAsDropDown(etAddress);
//                getCity("2");
//                getCounty("2");
                break;
            default:
                break;
        }
    }

    private void getprovince(){
        Map<String, String> map = new HashMap();
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getProvinceResult(RetrofitHttp.getRetrofit(builder.build()).getProvince(map));
    }
    private void getProvinceResult(Call<ResponseBody>call){
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    Provincebean beam = MyApplication.gson.fromJson(result,Provincebean.class);
                    if (beam.getSuccess().equals("true")){
                        provincedata = beam.getData();
                        mSpinerPopWindow.init(provincedata);
                        mSpinerPopWindow.setOnItemClickLineners(listeners);
                    }else {
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
    private void getCity(String proId){
        Map<String, String> map = new HashMap();
        map.put("ProId",proId);
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getCityResult(RetrofitHttp.getRetrofit(builder.build()).getCity(map));
    }
    private void getCityResult(Call<ResponseBody>call){
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    CityBean bean = MyApplication.gson.fromJson(result,CityBean.class);
                    if (bean.getSuccess().equals("true")){
                        cityList = bean.getData();
                        mSpinerPopWindow.init(cityList);
                        mSpinerPopWindow.setOnItemClickLineners(listeners);
                    }else {
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
    private void getCounty(String CityId){
        Map<String, String> map = new HashMap();
        map.put("CityId",CityId);
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getCountyResult(RetrofitHttp.getRetrofit(builder.build()).getCounty(map));
    }
    private void getCountyResult(Call<ResponseBody>call){
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    CountyBean bean = MyApplication.gson.fromJson(result,CountyBean.class);
                    if (bean.getSuccess().equals("true")){
                        countyList = bean.getData();
                        mSpinerPopWindow.init(countyList);
                        mSpinerPopWindow.setOnItemClickLineners(listeners);
                    }else {
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
