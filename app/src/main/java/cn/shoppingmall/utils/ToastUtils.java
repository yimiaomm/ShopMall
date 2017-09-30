package cn.shoppingmall.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import cn.shoppingmall.MyApplication;

/**
 * Created by ${易淼} on 2017/9/1.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class ToastUtils {
    private static Toast toast;


        public static void showToast(String text) {
            if (toast == null) {
                toast = Toast.makeText(MyApplication.getAppCtx(), text, Toast.LENGTH_SHORT);
            }
            toast.setText(text);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

    }

