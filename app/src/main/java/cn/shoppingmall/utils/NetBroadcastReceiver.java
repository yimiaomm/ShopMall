package cn.shoppingmall.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.view.WindowManager;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.ActivityManager;


public class NetBroadcastReceiver extends BroadcastReceiver {

    public static NetBroadcastReceiver netBroadcastReceiver = new NetBroadcastReceiver();
    private static Dialog dialog;


    @Override
    public void onReceive(Context context, Intent intent) {
        final Activity currentActivity = ActivityManager.getInstance().currentActivity();
//        boolean b = currentActivity instanceof SplishActivity || currentActivity instanceof SecondActivity;
//        if (!b) {
//            if (context == null) {
//                return;
//            }
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(R.string.message_title);
            builder.setMessage(R.string.setting_netWork_warning);
            if (dialog == null) {
                dialog = builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        if (currentActivity != null) {
                            currentActivity.startActivity(intent);
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();

                //在Service中弹出对话框，须设置
                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            }

//        }
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netMobile = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            switch (netMobile) {
                case NetUtil.NETWORK_MOBILE:
                case NetUtil.NETWORK_WIFI:
                    if (dialog != null && dialog.isShowing()&&context!=null) {
                        dialog.dismiss();
                    }

                    break;
                case NetUtil.NETWORK_NONE:
                    if (dialog != null&&context!=null) {
                        dialog.show();
                    }

                    break;
            }
        }
    }


    public static NetBroadcastReceiver getInstance() {
        if (netBroadcastReceiver == null) {
            return new NetBroadcastReceiver();
        }
        return netBroadcastReceiver;
    }
}
