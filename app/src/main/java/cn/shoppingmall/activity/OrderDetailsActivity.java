package cn.shoppingmall.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shoppingmall.R;

public class OrderDetailsActivity extends BaseActivity {


    @BindView(R.id.tv_left_title)
    TextView tvLeftTitle;
    @BindView(R.id.tv_center_title)
    TextView tvCenterTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void init() {
        tvLeftTitle.setText("返回");
        tvCenterTitle.setText("订单详情");
    }

    @Override
    protected int viewId() {
        return R.layout.activity_order_details;
    }


    @OnClick(R.id.tv_left_title)
    public void onViewClicked() {
        finish();
    }
}
