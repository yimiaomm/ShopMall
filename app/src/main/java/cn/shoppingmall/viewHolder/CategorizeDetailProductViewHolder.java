package cn.shoppingmall.viewHolder;

import android.content.Intent;
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
import cn.shoppingmall.bean.ListProductContentModel;
import cn.shoppingmall.utils.BaseUtils;


/**
 * author：Anumbrella
 * Date：16/6/4 下午7:17
 */
public class CategorizeDetailProductViewHolder extends BaseViewHolder<ListProductContentModel> implements View.OnClickListener {


    private ImageView simpleDraweeView;
    private CardView cardView;
    private TextView name;
    private TextView price;
    private ImageView phoneType;
    private ListProductContentModel data;

    public CategorizeDetailProductViewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_categorize_detail_product);
        simpleDraweeView = $(R.id.categorize_detail_product_img);
        name = $(R.id.categorize_detail_product_name);
        price = $(R.id.categorize_detail_product_price);
        cardView = $(R.id.categorize_detail_product_cardview);
        phoneType = $(R.id.categorize_phone_type);
    }


    @Override
    public void setData(ListProductContentModel data) {
        super.setData(data);
        this.data = data;
//        Picasso.with(MyApplication.getAppCtx()).load(data.getImageUrl()).into(simpleDraweeView);
        Picasso.with(MyApplication.getAppCtx())
                .load(data.getImageUrl())
                .resize(50, 50)
                .centerCrop()
                .error(R.mipmap.iv_nomal)
                .into(simpleDraweeView);
        name.setText(data.getTitle());
        price.setText("￥ " + data.getPrice());
        phoneType.setOnClickListener(this);
        cardView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.categorize_detail_product_cardview:
//                intent.putExtra(DetailContentFragment.ARG_ITEM_INFO_LISTPRODUCT, data);
                break;
        }
//        intent.setClass(getContext(), DetailContentActivity.class);
        getContext().startActivity(intent);

    }
}
