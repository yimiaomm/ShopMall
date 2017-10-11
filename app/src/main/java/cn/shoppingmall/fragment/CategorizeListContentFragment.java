package cn.shoppingmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.AllProductActivity;
import cn.shoppingmall.adapter.GridViewAdapter;
import cn.shoppingmall.bean.ClasslflyBean;

/**
 * author：Anumbrella
 * Date：16/5/26 下午7:13
 */
public class CategorizeListContentFragment extends BaseFragment {

    @BindView(R.id.productName)
    TextView tv_productName;
    @BindView(R.id.GridViewList)
    GridView gridView;
    Unbinder unbinder;

    /**
     * widget网格view
     */


    private GridViewAdapter adapter;


    private String productName;

    private int icon;

    public static CategorizeListContentFragment newInstance(ClasslflyBean.DataBean databean, int index) {

        Bundle args = new Bundle();
        args.putSerializable("classBean", databean);
        args.putInt("index", index);
        CategorizeListContentFragment fragment = new CategorizeListContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void init() {
        int index = getArguments().getInt("index");
        ClasslflyBean.DataBean datebean = (ClasslflyBean.DataBean) getArguments().getSerializable("classBean");
        productName = datebean.getCategoryName();
        tv_productName.setText(productName);
        final List<ClasslflyBean.DataBean.ChildCategoryBean> list = datebean.getChildCategory();
        adapter = new GridViewAdapter(getActivity(), list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ClasslflyBean.DataBean.ChildCategoryBean data = list.get(position);
                Intent intent = new Intent();
                intent.putExtra("ProdType",data.getCategoryId());
                intent.setClass(getContext(), AllProductActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.productlist_layout;
    }

}
