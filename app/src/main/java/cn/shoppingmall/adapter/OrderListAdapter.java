package cn.shoppingmall.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.bean.OrderListBean;
import cn.shoppingmall.fragment.orderFragment.OrderBaseFragment;
import cn.shoppingmall.utils.BaseUtils;
import cn.shoppingmall.utils.ToastUtils;
import cn.shoppingmall.view.PromptDialog;
import cn.shoppingmall.viewHolder.BaseViewHolder;

/**
 * Created by pc on 2017/9/7.
 */

public class OrderListAdapter extends SimpleAdapter<OrderListBean.DataBean.DataListBean> {
    private List<OrderListBean.DataBean.DataListBean> list;
    private Context mContext;
    private String orderStatus;
    private String id;
    private int viewHoderId;

    public OrderListAdapter(List<OrderListBean.DataBean.DataListBean> list, FragmentActivity activity) {
        super(activity, R.layout.order_list_item_layout, list);
        this.list = list;
        this.mContext = activity;
    }

//    @Override
//    protected void convert(BaseViewHolder viewHoder, OrderListBean.DataBean.DataListBean item) {
//

//        for (int i= 0;i<list.size();i++){
//
//        }
//

//
//    }

    /**
     * @Override protected void convert(BaseViewHolder viewHoder, OrderListBean item) {
     * <p>
     * <p>
     * }
     */
//    @Override
//    public void onClick(View view) {
//        id = list.get(viewHoderId).getID();
//        orderStatus = list.get(viewHoderId).getOrderStatus();
//        switch (view.getId()) {
//            case R.id.btn_left:

//                break;
//            case R.id.btn_right:
//
//                break;
//        }
//    }
    @Override
    protected void convert(final BaseViewHolder viewHoder, OrderListBean.DataBean.DataListBean item) {
        viewHoderId = viewHoder.getAdapterPosition();
        id = item.getOrderId();
        TextView tv_orderId = viewHoder.getTextView(R.id.tv_order_id);
        TextView tv_orderStutas = viewHoder.getTextView(R.id.tv_order_stutas);
        ListView listView = (ListView) viewHoder.getView(R.id.list_view);
        OrderChildListAdapter adapter = new OrderChildListAdapter();
        adapter.setDatas(item.getOrderContent());
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        Button leftButton = viewHoder.getButton(R.id.btn_left);
        Button rightButton = viewHoder.getButton(R.id.btn_right);
        orderStatus = item.getOrderStatus();
        tv_orderId.setText(item.getOrderId());
        tv_orderStutas.setText(item.getStrOrderStatus());
        if (orderStatus.equals("0")) {
            leftButton.setVisibility(View.GONE);
            rightButton.setText("删除订单");
        } else if (orderStatus.equals("1")) {
            leftButton.setText("取消订单");
            rightButton.setText("确认支付");
        } else if (orderStatus.equals("2")) {
            leftButton.setText("查看物流");
            rightButton.setText("确认收货");
        } else if (orderStatus.equals("3")) {
            leftButton.setText("查看物流");
            rightButton.setText("确认支付");
        } else if (orderStatus.equals("4")) {
            leftButton.setText("删除订单");
            rightButton.setText("再次购买");
        }
        final int position = viewHoder.getLayoutPosition();
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getOrderStatus().equals("1")) {
                    new PromptDialog.Builder(mContext)
                            .setTitle("提示")
                            .setTitleColor(R.color.white)
                            .setViewStyle(PromptDialog.VIEW_STYLE_TITLEBAR_SKYBLUE)
                            .setMessage("确定取消订单吗?")
                            .setMessageSize(20f)
                            .setButton1("确定", new PromptDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which) {
                                    OrderBaseFragment.updataOrder(list.get(position).getID(), "0", list.get(position).getOrderStatus());
                                    dialog.dismiss();
                                }
                            })
                            .setButton2("取消", new PromptDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getOrderStatus().equals("0")) {
                    new PromptDialog.Builder(mContext)
                            .setTitle("提示")
                            .setTitleColor(R.color.white)
                            .setViewStyle(PromptDialog.VIEW_STYLE_TITLEBAR_SKYBLUE)
                            .setMessage("确定删除订单吗?")
                            .setMessageSize(20f)
                            .setButton1("确定", new PromptDialog.OnClickListener() {
                                @Override
                                public void onClick(Dialog dialog, int which) {
                                    OrderBaseFragment.deleteOrder(list.get(position).getID());
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
                }
            }
        });


    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    class OrderChildListAdapter extends ListBaseAdapter<OrderListBean.DataBean.DataListBean.OrderContentBean> {
        private List<OrderListBean.DataBean.DataListBean.OrderContentBean> list;

        public OrderChildListAdapter() {
            super(mContext);
        }


        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemLayoutId(int getItemViewType) {
            return R.layout.item_child_order;
        }

        @Override
        public void handleItem(int itemViewType, int position, OrderListBean.DataBean.DataListBean.OrderContentBean item, ViewHolder holder, boolean isRecycle) {
            TextView tv_orderContent = holder.getTextView(R.id.tv_order_content);
            TextView tv_newPrice = holder.getTextView(R.id.tv_new_price);
            TextView tv_ordPrice = holder.getTextView(R.id.tv_old_price);
            TextView tv_orderCount = holder.getTextView(R.id.tv_order_count);
            ImageView iv_orderImage = holder.getImageView(R.id.iv_order_image);
            tv_ordPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            BaseUtils.setFakeBoldText(tv_orderContent);
            tv_orderContent.setText(item.getProdName());
            tv_newPrice.setText("¥" + item.getPrice1());
            tv_ordPrice.setText("¥" + item.getPrice2());
            tv_orderCount.setText("x" + item.getNum());
//            Picasso.with(mContext).load(item.getPicUrl()).into(iv_orderImage);
            Picasso.with(mContext)
                    .load(item.getPicUrl())
                    .error(R.mipmap.iv_nomal)
                    .fit()
                    .into(iv_orderImage);
        }
    }
}
