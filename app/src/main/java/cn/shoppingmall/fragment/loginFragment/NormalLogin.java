package cn.shoppingmall.fragment.loginFragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.IOException;
import java.security.Permission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.MyAddress;
import cn.shoppingmall.bean.UserInfoBean;
import cn.shoppingmall.fragment.BaseFragment;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.BaseUtils;
import cn.shoppingmall.utils.CodeUtils;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ${易淼} on 2017/9/26.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class NormalLogin extends BaseFragment {
    @BindView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.et_verNum)
    EditText etVerNum;
    Unbinder unbinder;
    private CodeUtils codeUtils;
    private String gpsGood;
    String phone, password, verNum,verCode;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_normallogin;
    }

    @Override
    public void init() {
        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        image.setImageBitmap(bitmap);
    }

    private void checkPermission() {
        AndPermission.with(this)
                .permission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE)
                .requestCode(200)
                .rationale(rationaleListener)
                .callback(this).start();
    }

    private RationaleListener rationaleListener = new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            AndPermission.rationaleDialog(getActivity(), rationale).show();
        }
    };

    private void getData() {
        phone = etPhoneNum.getText().toString();
        password =etNum.getText().toString();
        verNum = etVerNum.getText().toString().toLowerCase();
        String code = codeUtils.getCode().toLowerCase();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showToast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(verNum)) {
            ToastUtils.showToast("请输入验证码");
            return;
        }
        if (!BaseUtils.checkPhoneNumber(phone)) {
            ToastUtils.showToast("手机号格式不正确");
         return;
        }
        if (!code.equalsIgnoreCase(verNum)) {
            ToastUtils.showToast("验证码错误");
            Bitmap bitmap = codeUtils.createBitmap();
            image.setImageBitmap(bitmap);
            return;
        }
        userLogin();
    }

    @PermissionYes(200)
    private void getPermissionYes(List<String> grantedPermissions) {
        gpsGood = NetUitls.checkPermissions();
        getData();
    }

    @PermissionNo(200)
    private void getPermissionNo(List<String> deniedPermissions) {
        AndPermission.defaultSettingDialog(this, 300)
                .setTitle("权限申请失败")
                .setMessage("我们需要的定位权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则无法正常登陆！")
                .setPositiveButton("好，去设置")
                .show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 300) {
            gpsGood = NetUitls.checkPermissions();
        }

    }

    @OnClick({R.id.et_phoneNum, R.id.et_num, R.id.btn_login, R.id.tv_forget_pwd,R.id.image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_phoneNum:
                break;
            case R.id.et_num:
                break;
            case R.id.btn_login:
                checkPermission();
                break;
            case R.id.tv_forget_pwd:
                break;
            case R.id.image:
                Bitmap bitmap = codeUtils.createBitmap();
                image.setImageBitmap(bitmap);
                break;
        }
    }
    private void userLogin(){
        Map<String,String>map = new HashMap<>();
        map.put("UserId",phone);
        map.put("UserPwd",password);
        map.put("LoginType","1");
        map.put("UDID",NetUitls.getDeviceId(getActivity()));
        map.put("ClientVer",NetUitls.getSystemVersion());
        map.put("MobileVer",NetUitls.getDeviceBrand());
        map.put("MobileType","4");
        map.put("GPSCoords",gpsGood);
        map = NetUitls.getHashMapData(map);
//        UserId(string, optional): *用户名 / 手机号,
//                UserPwd(string, optional): *登录密码或验证码,
//                LoginType(integer, optional): *登陆类型 1. 密码登陆 2. 验证码登陆,
//                UDID(string, optional): *手机唯一识别码
//        ClientVer(string, optional): *客户端版本号,
//                MobileVer(string, optional): *移动终端设备版本号,
//                MobileType(integer, optional): *设备类型：1）webPC，2）webMobile，3）微信，4）Android，5）IOS （
//        对应字段OperSource）,
//        GPSCoords(string, optional):GPS信息,
//                timestamp(string, optional): *时间戳,
//                nonce(string, optional):随机数,
//                signature(string, optional):加密签名
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        userConnect(RetrofitHttp.getRetrofit(builder.build()).login(map));
    }
    private void userConnect(Call<ResponseBody> responseBodyCall){
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    UserInfoBean user = MyApplication.gson.fromJson(result,UserInfoBean.class);
                    if ("true".equals(user.getSuccess())){
                        GreenDaoUtlis greenDaoUtlis = new GreenDaoUtlis(getActivity());
                        greenDaoUtlis.insertUser(user.getData());
                        ToastUtils.showToast(user.getMsg());
                        getActivity().finish();
                    } else {
                      ToastUtils.showToast(user.getMsg());
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
