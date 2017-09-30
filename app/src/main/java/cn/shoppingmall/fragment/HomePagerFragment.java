package cn.shoppingmall.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.ProductDetailsActivity;
import cn.shoppingmall.adapter.GridViewAdapter;
import cn.shoppingmall.adapter.HorizontalListAdapter;
import cn.shoppingmall.bean.ButtomListBean;
import cn.shoppingmall.bean.RecommentBean;

/**
 * Created by ${易淼} on 2017/8/31.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class HomePagerFragment extends BaseFragment {
    @BindView(R.id.gv_view_list)
     GridView gridView;

    private GridViewAdapter adapter;
    private List<ButtomListBean.DataEntity> dataEntities;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_home_page_fragment;
    }

    @Override
    public void init() {
        String result = getArguments().getString("index","");
        ButtomListBean bean = MyApplication.gson.fromJson(result, ButtomListBean.class);

        dataEntities = bean.getData();
        HorizontalListAdapter horizontalListAdapter = new HorizontalListAdapter(getActivity().getApplicationContext(), dataEntities);
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.width = dip2px(getActivity(), 95) * dataEntities.size();
        gridView.setLayoutParams(params);
        gridView.setNumColumns(dataEntities.size());
        gridView.setAdapter(horizontalListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                intent.putExtra("productId",dataEntities.get(position).getID()+"");
                startActivity(intent);
            }
        });
    }
}
