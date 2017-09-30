package cn.shoppingmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shoppingmall.R;
import cn.shoppingmall.fragment.loginFragment.NormalLogin;
import cn.shoppingmall.fragment.loginFragment.VerLogin;
import cn.shoppingmall.utils.BaseUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static cn.shoppingmall.R.id.line;

/**
 * author：Anumbrella
 * Date：16/5/24 下午7:02
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_regist)
    TextView tvRegist;
//    @BindView(R.id.line)
//    View line;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List fragments;
    private int line_width;
    @Override
    protected void init() {
        BaseUtils.setFakeBoldText(tvLogin);
//        ViewPropertyAnimator.animate(tvVerLogin).scaleY(1.2f).setDuration(0);
//        tvVerLogin.setTextColor(getResources().getColor(R.color.red));
        fragments = new ArrayList();
//        fragments.add(new VerLogin());
        fragments.add(new NormalLogin());
//        line_width = getWindowManager().getDefaultDisplay().getWidth()
//                / fragments.size();
//        line.getLayoutParams().width = line_width;
//        line.setBackgroundColor(getResources().getColor(R.color.red));
//        line.requestLayout();
        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(simpleOnPageChangeListener);
    }

    @Override
    protected int viewId() {
        return R.layout.activity_login;
    }
    public class LoginAdapter extends FragmentPagerAdapter {
        public LoginAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    ViewPager.SimpleOnPageChangeListener simpleOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
//            changeState(position);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            float tagerX = position * line_width + positionOffsetPixels / fragments.size();
//            ViewPropertyAnimator.animate(line).translationX(tagerX)
//                    .setDuration(0);
        }
    };

//    private void changeState(int arg0) {
//        if (arg0 == 0) {
//            tvVerLogin.setTextColor(getResources().getColor(R.color.red));
//            tvNormalLogin.setTextColor(getResources().getColor(R.color.red));
//            ViewPropertyAnimator.animate(tvVerLogin).scaleX(1.2f).setDuration(200);
//            ViewPropertyAnimator.animate(tvVerLogin).scaleY(1.2f).setDuration(200);
//            ViewPropertyAnimator.animate(tvNormalLogin).scaleX(1.0f)
//                    .setDuration(200);
//            ViewPropertyAnimator.animate(tvNormalLogin).scaleY(1.0f)
//                    .setDuration(200);
//        } else {
//            tvNormalLogin.setTextColor(getResources().getColor(R.color.red));
//            tvVerLogin.setTextColor(getResources().getColor(R.color.red));
//            ViewPropertyAnimator.animate(tvVerLogin).scaleX(1.0f).setDuration(200);
//            ViewPropertyAnimator.animate(tvVerLogin).scaleY(1.0f).setDuration(200);
//            ViewPropertyAnimator.animate(tvNormalLogin).scaleX(1.2f)
//                    .setDuration(200);
//            ViewPropertyAnimator.animate(tvNormalLogin).scaleY(1.2f)
//                    .setDuration(200);
//        }
//    }



  /**
   *  @OnClick({R.id.btn_register, R.id.btn_login, R.id.forget_password, R.id.btn_back})
    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                prompt = true;
                if (checkUpResult == false) {
                    checkUpResult = true;
                }
                getData();
                break;
            case R.id.btn_register:
                Intent intent_register = new Intent();
                intent_register.setClass(this, RegisterActivity.class);
                startActivity(intent_register);
                break;
            case R.id.forget_password:
                Intent intent_forget_password = new Intent();
                intent_forget_password.setClass(this, FindPasswordActivity.class);
                startActivity(intent_forget_password);
                break;
            case R.id.btn_back:
                if (startUp != null) {
                    if (startUp.equals("main")) {
                        finish();
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void getData() {
        phone = login_phone.getEditText().getText().toString().trim();
        password = login_password.getEditText().getText().toString().trim();

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

        doLogin();
    }

    private void doLogin() {
        if (checkUpResult) {
            mDialog.show();
            HashMap<String, String> map = new HashMap<String, String>();
//                    UserId (string, optional):用户名/手机号,
//                    UserPwd (string, optional):登录密码或验证码,
//                    LoginType (integer, optional):登陆类型 1.密码登陆 2.验证码登陆,
//                    UDID (string, optional):手机唯一识别码,
//                    ClientVer (string, optional):客户端版本号,
//                    MobileVer (string, optional):移动终端设备版本号,
//                    MobileType (integer, optional):设备类型：1）webPC，2）webMobile，3）微信，4）Android，5）IOS （对应字段OperSource）,
//                    GPSCoords (string, optional):GPS信息,

            map.put("UserId","");
            map.put("UserPwd","");
            map.put("LoginType","");
            map.put("UDID",NetUitls.getDeviceId(LoginActivity.this));
            map.put("ClientVer",NetUitls.getSystemVersion());
            map.put("MobileVer",NetUitls.getDeviceBrand());
            map.put("MobileType","4");
            map.put("GPSCoords","");
            map = NetUitls.getHashMapData(map);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            loginResult(RetrofitHttp.getRetrofit(builder.build()).login(map));*/
//            RetrofitHttp.getRetrofit(builder.build()).login("login", phone, password)
//                    .enqueue(new Callback<ResponseBody>() {
//                        @Override
//                        public void onResponse(Response<ResponseBody> response) {
//                            try {
//
//                                String result = response.body().string().toString();
//                                for (int i = 0; i < ServiceApi.LoginApi.length; i++) {
//                                    if (ServiceApi.LoginApi[i][0].equals(result)) {
//                                        mDialog.hide();
//                                        Toast.makeText(LoginActivity.this, ServiceApi.LoginApi[i][1], Toast.LENGTH_SHORT).show();
//                                        break;
//                                    }
//
//                                }
//
//                                if (!result.equals(ServiceApi.LoginApi[0][0]) && !result.equals(ServiceApi.LoginApi[0][1])) {
//                                    JSONObject jsonObj = new JSONObject(result);
//                                    String userName = jsonObj.getString("userName");
//                                    String signName = jsonObj.getString("signName");
//                                    int uid = jsonObj.getInt("uid");
//                                    String iconImg = jsonObj.getString("iconImg");
//                                    //保存用户数据
//                                    LocalUserDataModel data = new LocalUserDataModel();
//                                    data.setSignName(signName);
//                                    data.setUid(uid);
//                                    data.setUserImg(iconImg);
//                                    data.setUserName(userName);
//                                    data.setLogin(true);
//                                    BaseUtils.saveLocalUser(LoginActivity.this, data);
//                                    //添加用户
//                                    DBManager.getManager(LoginActivity.this).addUser(userName, true, uid);
//                                    new Handler().postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            mDialog.dismiss();
//                                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                                            if (startUp != null) {
//                                                if (startUp.equals("main")) {
//                                                    finish();
//                                                } else if (startUp.equals("welcome")) {
//                                                    Intent intent = new Intent();
//                                                    intent.setClass(LoginActivity.this, MainActivity.class);
//                                                    startActivity(intent);
//                                                    finish();
//                                                }
//                                            } else {
//                                                Intent intent = new Intent();
//                                                intent.setClass(LoginActivity.this, MainActivity.class);
//                                                startActivity(intent);
//                                                finish();
//                                            }
//                                            finish();
//                                        }
//
//                                    }, 1000);
//                                }
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Throwable t) {
//                            mDialog.hide();
//                            Toast.makeText(LoginActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//        }
//    }

    private void loginResult(Call<ResponseBody> response) {
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                mDialog.hide();


            }

            @Override
            public void onFailure(Throwable t) {
                mDialog.hide();
                Toast.makeText(LoginActivity.this, "网络不给力", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.tv_cancle, R.id.tv_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                break;
            case R.id.tv_regist:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
//            case R.id.tv_verLogin:
//                viewPager.setCurrentItem(0);
//                break;
//            case R.id.tv_normalLogin:
//                viewPager.setCurrentItem(1);
//                break;
        }
    }
}
