package cn.shoppingmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.shoppingmall.R;
import cn.shoppingmall.bean.ButtomListBean;
import cn.shoppingmall.bean.RecommentBean;

/**
 * Created by ${易淼} on 2017/8/29.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class HorizontalListAdapter extends BaseAdapter{
    private Context context;
    private List<ButtomListBean.DataEntity>list;

    public HorizontalListAdapter(Context context, List<ButtomListBean.DataEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    convertView = LayoutInflater.from(context).inflate(R.layout.item_horizontal_list,null);
        TextView textView  = (TextView) convertView.findViewById(R.id.tv_title);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView textView1 = (TextView) convertView.findViewById(R.id.tv_price);
//        Picasso.with(context).load(list.get(position).getPicUrl()).into(imageView);
        Picasso.with(context).load(list.get(position).getPicUrl()).fit()
                .error(R.mipmap.ic_launcher)
                .into(imageView);

        textView.setText(list.get(position).getProdName());
        textView1.setText("¥"+list.get(position).getPrice2()+"");
        return convertView;
    }


}
