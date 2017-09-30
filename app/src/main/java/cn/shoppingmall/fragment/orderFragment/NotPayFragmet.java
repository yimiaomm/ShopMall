package cn.shoppingmall.fragment.orderFragment;

import android.view.View;

/**
 * Created by ${易淼} on 2017/9/30.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class NotPayFragmet extends OrderBaseFragment{
    @Override
    protected View getContentView() {
        return view;
    }

    @Override
    protected void initView() {
        ORDER_SUTTAS = "1";
    }
}
