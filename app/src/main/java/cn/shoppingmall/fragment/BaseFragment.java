package cn.shoppingmall.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ${易淼} on 2017/8/24.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    protected Context cxt;
    protected ProgressDialog mDialog;
    protected static View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        cxt = getActivity();
        Fresco.initialize(cxt);
        showDialog();
        init();
        return view;
    }

    protected abstract int getLayoutId();


    public abstract void init();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
    protected void showDialog() {
        mDialog = new ProgressDialog(cxt);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("请稍等");
        mDialog.setIndeterminate(false);
        // 设置ProgressDialog 是否可以按退回按键取消
        mDialog.setCancelable(false);
    }
}
