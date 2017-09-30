package cn.shoppingmall.viewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.shoppingmall.adapter.BaseAdapter;


/**
 * Created by ${易淼} on 2017/8/25.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class BaseViewHolder <M>extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SparseArray<View> views;
    private BaseAdapter.OnItemClickListener mListener;
    private BaseAdapter.OnViewClickListener mOnViewListener;
    public BaseViewHolder(View itemView, BaseAdapter.OnItemClickListener mListener, BaseAdapter.OnViewClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mListener = mListener;
        this.views = new SparseArray<View>();
    }
    public TextView getTextView(int viewId){
        return retrieveView(viewId);
    }
    public Button getButton(int viewId){
        return retrieveView(viewId);
    }
    public ImageView getImageView(int viewId){
        return retrieveView(viewId);
    }
    public View getView (int viewId){
        return retrieveView(viewId);
    }


    @Override
    public void onClick(View v) {
        if (mListener!=null){
            mListener.onItemClick(v,getLayoutPosition());
        }
    }
    protected <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
    protected Context getContext(){
        return itemView.getContext();
    }
    public void setData(M data) {
    }
}
