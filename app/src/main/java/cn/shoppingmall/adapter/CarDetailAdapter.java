package cn.shoppingmall.adapter;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.shoppingmall.R;
import cn.shoppingmall.bean.ShopCarBean;
import cn.shoppingmall.viewHolder.BaseViewHolder;

/**
 * Created by ${易淼} on 2017/9/30.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class CarDetailAdapter extends SimpleAdapter<ShopCarBean.DataEntity.ShopCartListEntity>{
    private Context context;
    public CarDetailAdapter(Context context, List<ShopCarBean.DataEntity.ShopCartListEntity> datas) {
        super(context, R.layout.item_car_detail, datas);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, ShopCarBean.DataEntity.ShopCartListEntity item) {
        ImageView iv_prod_pic = viewHoder.getImageView(R.id.iv_prod_pic);
        TextView tv_prod_name = viewHoder.getTextView(R.id.tv_prod_name);

        TextView tv_prod_price = viewHoder.getTextView(R.id.tv_prod_price);
        TextView tv_prod_num = viewHoder.getTextView(R.id.tv_prod_num);
        TextView tv_prod_freigh = viewHoder.getTextView(R.id.tv_prod_freigh);
        EditText editText = (EditText) viewHoder.getView(R.id.editText);
        Picasso.with(context).load(item.getPicUrl()).fit().into(iv_prod_pic);
        tv_prod_name.setText(item.getProdName());
        tv_prod_price.setText("¥"+item.getSnapPrice());
        tv_prod_num.setText("x"+item.getBuyNum());
        tv_prod_freigh.setText("¥"+item.getSnapPrice3());
    }
}
