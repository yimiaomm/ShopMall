package cn.shoppingmall.viewHolder;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.squareup.picasso.Picasso;

import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.ProductDetailsActivity;
import cn.shoppingmall.bean.ListProductContentModel;
import cn.shoppingmall.utils.BaseUtils;

import static cn.shoppingmall.R.id.tv_old_price;


/**
 * author：Anumbrella
 * Date：16/6/4 下午7:17
 */
public class CategorizeDetailProductViewHolder extends BaseViewHolder<ListProductContentModel.DataEntity.DataListEntity> implements View.OnClickListener {


    private ImageView simpleDraweeView;
    private CardView cardView;
    private TextView name;
    private TextView price,tv_oldprice;
    private ImageView phoneType;
    private ListProductContentModel.DataEntity.DataListEntity data;

    public CategorizeDetailProductViewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_categorize_detail_product);
        simpleDraweeView = $(R.id.categorize_detail_product_img);
        name = $(R.id.categorize_detail_product_name);
        price = $(R.id.categorize_detail_product_price);
        cardView = $(R.id.categorize_detail_product_cardview);
        phoneType = $(R.id.categorize_phone_type);
        tv_oldprice = $(R.id.tv_oldprice);
        tv_oldprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
    }
    @Override
    public void setData(ListProductContentModel.DataEntity.DataListEntity data) {
        super.setData(data);
        this.data = data;
        Picasso.with(MyApplication.getAppCtx())
                .load(data.getPicUrl())
                .error(R.mipmap.iv_nomal)
                .fit()
                .into(simpleDraweeView);
        name.setText(data.getProdName());
        price.setText("¥ " + data.getPrice1());
        tv_oldprice.setText("¥ "+data.getPrice2());
        phoneType.setOnClickListener(this);
        cardView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.categorize_detail_product_cardview:
                intent.setClass(MyApplication.getAppCtx(), ProductDetailsActivity.class);
                intent.putExtra("productId",data.getID());
             MyApplication.getAppCtx().startActivity(intent);
                break;
        };
    }
}
