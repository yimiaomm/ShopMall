package cn.shoppingmall.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import cn.shoppingmall.MyApplication;
import es.dmoral.toasty.Toasty;

import static es.dmoral.toasty.Toasty.normal;

/**
 * Created by ${易淼} on 2017/9/1.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class ToastUtils {
        public static void showToast(String text) {
                 Toasty.normal(MyApplication.getAppCtx(),text,Gravity.CENTER).show();
        }

    }

