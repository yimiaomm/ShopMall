package cn.shoppingmall.viewHolder;

import android.app.Dialog;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.squareup.picasso.Picasso;


import java.util.HashMap;

import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.adapter.ShoppingDataAdapter;
import cn.shoppingmall.bean.ShopCarBean;
import cn.shoppingmall.fragment.ShopCarFragment;
import cn.shoppingmall.view.PromptDialog;

/**
 * author：Anumbrella
 * Date：16/6/4 下午10:01
 */
public class ShoppingDataViewHolder extends BaseViewHolder<ShopCarBean.DataEntity.ShopCartListEntity> implements View.OnClickListener {

    private ShopCarBean.DataEntity.ShopCartListEntity data;

    private static HashMap<Integer, Integer> hashMapValue = new HashMap<>();

    private ImageView icon;

    private CardView cardview;

    private CheckBox checkbox;

    private TextView title;

    private TextView price;

    private TextView sum;

    private LinearLayout shopping_delete_layout;


    private LinearLayout shopping_data_action;


    private LinearLayout shopping_data_sum;

    private ImageView actionSub;

    private ImageView actionAdd;

    private TextView productSum;

    private TextView actionDelete;

    private boolean carIsCheck = true;
    public ShoppingDataViewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_shopping_data);
        icon = $(R.id.shopping_data_icon);
        cardview = $(R.id.shopping_product_cardview);
        checkbox = $(R.id.checkbox);
        title = $(R.id.shopping_data_title);
        price = $(R.id.shopping_data_price);
        sum = $(R.id.shopping_data_add_sum);
        shopping_delete_layout = $(R.id.shopping_delete_layout);
        shopping_data_action = $(R.id.shopping_data_action);
        shopping_data_sum = $(R.id.shopping_data_sum);
        actionAdd = $(R.id.shopping_product_sum_add);
        actionSub = $(R.id.shopping_product_sum_sub);
        actionDelete = $(R.id.shopping_action_delete);
        productSum = $(R.id.shopping_product_sum);

    }

    @Override
    public void setData(final ShopCarBean.DataEntity.ShopCartListEntity listData) {
        super.setData(data);
        this.data = listData;
//        Picasso.with(MyApplication.getAppCtx())
//                .load(data.getPicUrl())
//                .error(R.mipmap.ic_launcher)
//                .fit()
//                .into(icon);
        Picasso.with(MyApplication.getAppCtx())
                .load(data.getPicUrl())
                .error(R.mipmap.iv_nomal)
                .fit()
                .into(icon);
        cardview.setOnClickListener(this);
        checkbox.setOnClickListener(this);
        actionAdd.setOnClickListener(this);
        actionSub.setOnClickListener(this);
        actionDelete.setOnClickListener(this);
        title.setText(data.getProdName());
        price.setText("￥" + data.getSnapPrice());
        sum.setText(data.getBuyNum());
        productSum.setText(data.getBuyNum());
        int  pid = data.getPid();
        final boolean isCheck = ShoppingDataAdapter.getIsCheck(pid);
        if (ShoppingDataAdapter.getDisplay()) {
            shopping_delete_layout.setVisibility(View.VISIBLE);
            shopping_data_action.setVisibility(View.VISIBLE);
            shopping_data_sum.setVisibility(View.GONE);
        } else {
            shopping_delete_layout.setVisibility(View.GONE);
            shopping_data_action.setVisibility(View.GONE);
            shopping_data_sum.setVisibility(View.VISIBLE);
        }
        checkbox.setChecked(isCheck);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                carIsCheck = isChecked;
                ShoppingDataAdapter.setCheckBoolean(data.getPid(),isChecked);
//                if (ShopCarFragment.isCheckAll.equals("0")) {
                    ShopCarFragment.updataShopCar(data.getID(),productSum.getText().toString(),carIsCheck+"");
//                }
//                if (ShopCarFragment.ischeckAllState() && isChecked) {
//                    ShopCarFragment.checkAll.setChecked(true);
//                } else {
//                    if (ShopCarFragment.checkAll.isChecked()) {
//                        ShopCarFragment.isCheckSingle = true;
//                        ShopCarFragment.checkAll.setChecked(false);
//                    } else if (ShopCarFragment.ischeckAllState()) {
//                        ShopCarFragment.isCheckSingle = false;
//                        ShopCarFragment.checkAll.setChecked(true);
//                    }
//                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopping_product_sum_add:
                productSum.setText(String.valueOf(Integer.parseInt(productSum.getText().toString()) + 1));
                addHashMapValue(data.getPid(), Integer.parseInt(productSum.getText().toString()));
                ShopCarFragment.updataShopCar(data.getID(),productSum.getText().toString(),carIsCheck+"");
                break;
            case R.id.shopping_product_sum_sub:
                if (Integer.parseInt(productSum.getText().toString()) > 1) {
                    productSum.setText(String.valueOf(Integer.parseInt(productSum.getText().toString()) - 1));
                    ShopCarFragment.updataShopCar(data.getID(),productSum.getText().toString(),carIsCheck+"");
                }
                addHashMapValue(data.getPid(), Integer.parseInt(productSum.getText().toString()));
                break;
            case R.id.shopping_action_delete:

                new PromptDialog.Builder(getContext())
                        .setTitle("提示")
                        .setTitleColor(R.color.white)
                        .setViewStyle(PromptDialog.VIEW_STYLE_TITLEBAR_SKYBLUE)
                        .setMessage("确定删除该商品吗?")
                        .setMessageSize(20f)
                        .setButton1("确定", new PromptDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                ShopCarFragment.deleteOrder(data.getID());
                                dialog.dismiss();
                            }
                        })
                        .setButton2("取消", new PromptDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

                break;


        }
    }

    public void addHashMapValue(int pid, int sum) {
        if (hashMapValue.get(pid) == null || hashMapValue.get(pid) != sum) {
            hashMapValue.put(pid, sum);
        }
    }

    public static HashMap<Integer, Integer> getHashMap() {
        return hashMapValue;
    }

}
