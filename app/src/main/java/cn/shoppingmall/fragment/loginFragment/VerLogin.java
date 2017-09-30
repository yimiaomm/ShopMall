package cn.shoppingmall.fragment.loginFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.shoppingmall.R;
import cn.shoppingmall.fragment.BaseFragment;

/**
 * Created by ${易淼} on 2017/9/26.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class VerLogin extends BaseFragment {

    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.tv_getAuth)
    TextView tvGetAuth;
    @BindView(R.id.et_verNum)
    EditText etVerNum;
    @BindView(R.id.btn_sendVer)
    TextView btnSendVer;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verlogin;
    }

    @Override
    public void init() {

    }

    @OnClick({R.id.et_num,R.id.et_verNum, R.id.btn_sendVer, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_num:
                break;
            case R.id.et_verNum:
                break;
            case R.id.btn_sendVer:
                break;
            case R.id.btn_login:
                break;
        }
    }
}
