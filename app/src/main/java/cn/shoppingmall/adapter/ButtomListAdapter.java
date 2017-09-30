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
        Picasso.with(context).load(item.getPicUrl()).fit()
                .error(R.mipmap.ic_launcher)
                .into(iv_show_imager);

        tv_content.setText(item.getProdName());
        tv_new_price.setText("¥"+item.getPrice1()+"");
        tv_old_price.setText("¥"+item.getPrice2()+"");
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, "添加了一条数据", Toast.LENGTH_LONG).show();
    }
//
//    @Override
//    protected void convert(BaseViewHolder viewHoder, ButtomListBean item) {
//        ImageView iv_show_imager = viewHoder.getImageView(R.id.iv_showpic);
//        ImageView iv_add_car = viewHoder.getImageView(R.id.iv_add_car);
//        TextView tv_content = viewHoder.getTextView(R.id.tv_content);
//        TextView tv_new_price = viewHoder.getTextView(R.id.tv_new_price);
//        TextView tv_old_price = viewHoder.getTextView(R.id.tv_old_price);
//        iv_add_car.setOnClickListener(this);
//        Picasso.with(context).load(item.getImagerUrl()).into(iv_show_imager);
//        tv_content.setText(item.getContent());
//        tv_new_price.setText(item.getNewPrice());
//        tv_old_price.setText(item.getOldPrice());
//    }


//    public ButtomListAdapter(Context context, List<ButtomListBean> list) {
//        this.context = context;
//        this.list = list;
//    }





//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder=null;
//        if (viewHolder==null){
//            viewHolder = new ViewHolder();
//                    convertView = LayoutInflater.from(context).inflate(R.layout.item_buttom_list,null);
//            viewHolder.iv_show_imager = (ImageView) convertView.findViewById(R.id.iv_showpic);
//            viewHolder.iv_add_car = (ImageView) convertView.findViewById(R.id.iv_add_car);
//            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
//            viewHolder.tv_new_price = (TextView) convertView.findViewById(R.id.tv_new_price);
//            viewHolder.tv_old_price = (TextView) convertView.findViewById(R.id.tv_old_price);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        viewHolder.tv_old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
//        ButtomListBean blb = list.get(position);
//        viewHolder.iv_add_car.setOnClickListener(this);
//        if (blb!=null){
//            Picasso.with(context).load(blb.getImagerUrl()).into(viewHolder.iv_show_imager);
//            viewHolder.tv_content.setText(blb.getContent());
//            viewHolder.tv_new_price.setText(blb.getNewPrice());
//            viewHolder.tv_old_price.setText(blb.getOldPrice());
//
//
//        }
//        return convertView;
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.iv_add_car:
//                Toast.makeText(context,"添加了一条数据",Toast.LENGTH_LONG).show();
//                break;
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemLayoutId(int getItemViewType) {
//        return R.layout.item_buttom_list;
//    }
//
//    @Override
//    public void handleItem(int itemViewType, int position, ButtomListBean item, ViewHolder holder, boolean isRecycle) {

//    }

//    class ViewHolder{
//        TextView tv_content,tv_old_price,tv_new_price;
//        ImageView iv_show_imager,iv_add_car;
//    }


}
