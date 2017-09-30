package cn.shoppingmall.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shoppingmall.R;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.BaseUtils;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.tv_people)
    EditText tvPeople;
    @BindView(R.id.et_people_num)
    EditText etPeopleNum;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.register_info1)
    TextView registerInfo1;
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
//                    for (int i = 0; i < ServiceApi.RegisterApi.length; i++) {
//                        if (ServiceApi.RegisterApi[i][0].equals(result)) {
//                            Toast.makeText(RegisterActivity.this, ServiceApi.RegisterApi[i][1], Toast.LENGTH_SHORT).show();
//                            //注册成功跳转到登录
//                            if (ServiceApi.RegisterApi[i][0].equals("0200")) {
//                                Intent intent = new Intent();
//                                intent.setClass(RegisterActivity.this, LoginActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                            break;
//                        }
//                    }
                } catch (Exception io) {

                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RegisterActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @OnClick({R.id.service_text, R.id.btn_back,R.id.btn_login})
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
            default:
                break;
        }
    }



}
