package cn.shoppingmall.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.shoppingmall.R;
import cn.shoppingmall.bean.ButtomListBean;
import cn.shoppingmall.viewHolder.BaseViewHolder;

/**
 * Created by ${易淼} on 2017/8/29.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class ButtomListAdapter extends ListBaseAdapter<ButtomListBean.DataEntity> implements View.OnClickListener {
    private Context context;
    private List<ButtomListBean.DataEntity> list;

    public ButtomListAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemLayoutId(int getItemViewType) {
        return R.layout.item_buttom_list;
    }

    @Override
    public void handleItem(int itemViewType, int position, ButtomListBean.DataEntity item, ViewHolder holder, boolean isRecycle) {
        ImageView iv_show_imager = holder.getImageView(R.id.iv_showpic);
        ImageView iv_add_car = holder.getImageView(R.id.iv_add_car);
        TextView tv_content = holder.getTextView(R.id.tv_content);
        TextView tv_new_price = holder.getTextView(R.id.tv_new_price);
        TextView tv_old_price = holder.getTextView(R.id.tv_old_price);
        tv_old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        iv_add_car.setOnClickListener(this);
//        Picasso.with(context).load(item.getPicUrl()).into(iv_show_imager);
//        Picasso.with(context).load(item.getPicUrl()).error(R.mipmap.iv_nomal).fit().into(iv_show_imager);
        Picasso.with(context)
                .load(item.getPicUrl())
                .resize(50, 50)
                .centerCrop()
                .error(R.mipmap.iv_nomal)
                .into(iv_show_imager);
        tv_content.setText(item.getProdName());
        tv_new_price.setText("¥"+item.getPrice1()+"");
        tv_old_price.setText("¥"+item.getPrice2()+"");
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "添加了一条数据", Toast.LENGTH_LONG).show();
    }

}
