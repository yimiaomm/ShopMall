package cn.shoppingmall.activity;

import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.utils.NetBroadcastReceiver;


/**
 * author：anumbrella
 * Date:16/9/5 上午10:00
 */
abstract public class BaseActivity extends AppCompatActivity {
    protected ProgressDialog mDialog;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(viewId());
        unbinder = ButterKnife.bind(this);
        MyApplication.getActivityManager().pushActivity(this);
        Fresco.initialize(this);
        //PushAgent.getInstance(this).onAppStart();
        checkInternet();
        showDialog();
        init();
    }
    protected void checkInternet() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(NetBroadcastReceiver.getInstance(), filter);
    }
    abstract protected void init();

    abstract protected int viewId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(NetBroadcastReceiver.getInstance());
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected void showDialog() {
        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("请稍等");
        mDialog.setIndeterminate(false);
        // 设置ProgressDialog 是否可以按退回按键取消
        mDialog.setCancelable(false);
    }
}
