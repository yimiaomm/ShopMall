package cn.shoppingmall.fragment;

import android.content.Intent;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.shoppingmall.MainActivity;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.LoginActivity;
import cn.shoppingmall.activity.MyAddress;
import cn.shoppingmall.activity.OrderListActivity;
import cn.shoppingmall.adapter.MineAdapter;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.utils.ToastUtils;

import static android.os.Build.VERSION_CODES.M;


public class MineFragment extends BaseFragment implements SwipeDismissBehavior.OnDismissListener {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ll_user_info)
    LinearLayout ll_user_info;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    int[] imageRes = {
            R.mipmap.mine_order,R.mipmap.shop_car
            , R.mipmap.balance, R.mipmap.place,
            R.mipmap.preblem, R.mipmap.clent_help,
            R.mipmap.check_version, R.mipmap.exit_login};

    @Override
    public void init() {
        final GreenDaoUtlis greenDao = new GreenDaoUtlis(cxt);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MineAdapter adapter = new MineAdapter(imageRes, getResources().
                getStringArray(R.array.mine_item_names));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MineAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int tag) {
                    Intent   intent = new Intent();
             switch (tag){
                 case 0://我的订单
                     intent.setClass(cxt,OrderListActivity.class);
                     startActivity(intent);
                     break;
                case 1://购物车
                        MainActivity.pager.setCurrentItem(2);
                     break;
                 case 2://我的钱包

                     break;
                 case 3://地址管理
                    DataEntity user =  greenDao.query();
                     if (user==null){
                         return;
                     }
                    intent.setClass(cxt,MyAddress.class);
                     startActivity(intent);
                     break;
                 case 4://常见问题

                     break;
                 case 5://客户服务

                     break;
                 case 6://版本检测

                     break;
                 case 7://退出登陆

                     break;
             }
            }
        });
        ll_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cxt, LoginActivity.class));

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.mine_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_mine:
                ToastUtils.showToast(item.getTitle().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDismiss(View view) {

    }

    @Override
    public void onDragStateChanged(int state) {

    }

//    @OnClick({R.id.tv_order_list, R.id.tv_shop_car, R.id.tv_balance, R.id.tv_place, R.id.tv_preblem, R.id.tv_clent_help, R.id.tv_version})
//    public void onViewClicked(View view) {
//        Intent intent = new Intent();
//        switch (view.getId()) {
////            case R.id.tv_order_list:
////                intent.setClass(getActivity(), OrderListActivity.class);
////                startActivity(intent);
////                break;
////            case R.id.tv_shop_car:
////                MainActivity.pager.setCurrentItem(2);
////                break;
//
////            case R.id.tv_place:
////                intent.setClass(getActivity(), MyAddress.class);
////                startActivity(intent);
////                break;
//
//
//        }
//    }
}
