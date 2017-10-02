package cn.shoppingmall.fragment;

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

    public static CategorizeListContentFragment newInstance(ClasslflyBean.DataBean databean,int index) {

        Bundle args = new Bundle();
        args.putSerializable("classBean",databean);
        args.putInt("index",index);
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
        List<ClasslflyBean.DataBean.ChildCategoryBean> list = datebean.getChildCategory();
        adapter = new GridViewAdapter(getActivity(),list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                ProductTypeModel data = productListType.get(position);
//                Intent intent = new Intent();
//                intent.putExtra(CategorizeDetailProductActivity.INTENT_PRODUCT_ITEM_INFO, data);
//                intent.setClass(getContext(), CategorizeDetailProductActivity.class);
//                startActivity(intent);
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.productlist_layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
