package cn.shoppingmall.adapter;

import android.content.Context;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.shoppingmall.R;
import cn.shoppingmall.bean.RecommentBean;
import cn.shoppingmall.viewHolder.BaseViewHolder;

/**
 * Created by ${易淼} on 2017/8/29.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class HAdapter extends SimpleAdapter<RecommentBean> {
    private List<RecommentBean> list;
    private Context context;

    public HAdapter(Context context,List<RecommentBean> datas) {
        super(context, R.layout.item_horizontal_list, datas);
    }


    @Override
    protected void convert(BaseViewHolder viewHoder, RecommentBean item) {
        viewHoder.getTextView(R.id.tv_title).setText(item.getImageName());
        viewHoder.getTextView(R.id.tv_price).setText(item.getPrice());
        Picasso.with(context).load(item.getImageUrl()).into(viewHoder.getImageView(R.id.iv_image));
    }
}
