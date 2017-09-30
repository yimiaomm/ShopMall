package cn.shoppingmall.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.shoppingmall.R;
import cn.shoppingmall.bean.OrderListBean;
import cn.shoppingmall.viewHolder.BaseViewHolder;

/**
 * Created by pc on 2017/9/7.
 */

public class OrderListAdapter  extends SimpleAdapter<OrderListBean> implements View.OnClickListener{
    private List<OrderListBean>list;
    private Context context;
    public OrderListAdapter(List<OrderListBean> list, FragmentActivity activity) {
        super(activity,R.layout.order_list_item_layout,list);
        this.list = list;
        this.context = activity;
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, OrderListBean item) {
        TextView tv_orderId = viewHoder.getTextView(R.id.tv_order_id);
        TextView tv_orderStutas = viewHoder.getTextView(R.id.tv_order_stutas);
        TextView tv_orderContent = viewHoder.getTextView(R.id.tv_order_content);
        TextView tv_newPrice  = viewHoder.getTextView(R.id.tv_new_price);
        TextView tv_ordPrice  = viewHoder.getTextView(R.id.tv_old_price);
        TextView tv_orderCount = viewHoder.getTextView(R.id.tv_order_count);
        ImageView iv_orderImage = viewHoder.getImageView(R.id.iv_order_image);
        Button leftButton = viewHoder.getButton(R.id.btn_left);
        Button rightButton = viewHoder.getButton(R.id.btn_right);
        tv_ordPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        tv_orderId.setText(item.getOrderId());
        tv_orderStutas.setText(item.getOrderStatus());
        tv_orderContent.setText(item.getOrderContent());
        tv_newPrice.setText(item.getOrderNewPrice());
        tv_ordPrice.setText(item.getOrderOldPrice());
        tv_orderCount.setText("x"+item.getOrderCount());
        Picasso.with(context).load(item.getOrderImageUrl()).into(iv_orderImage);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_left:
                break;
            case R.id.btn_right:
                break;
        }
    }
}
