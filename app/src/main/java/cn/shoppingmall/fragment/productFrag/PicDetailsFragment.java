package cn.shoppingmall.fragment.productFrag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.shoppingmall.R;
import cn.shoppingmall.bean.ProductDetailsBean;
import cn.shoppingmall.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicDetailsFragment extends BaseFragment {

    @BindView(R.id.web_view)
    WebView webView;
    private static final String ARG_PARAM12 = "param12";
    public static PicDetailsFragment newInstance(ProductDetailsBean productDetailsBean) {
        PicDetailsFragment fragment = new PicDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM12,productDetailsBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pic_details;
    }

    @Override
    public void init() {
        Bundle bundle = getArguments();
        ProductDetailsBean bean = (ProductDetailsBean) bundle.getSerializable(ARG_PARAM12);
        ProductDetailsBean.DataEntity dataEntity = bean.getData();
        webView.loadUrl(dataEntity.getToURL());
    }

}
