package cn.shoppingmall.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ${易淼} on 2017/9/6.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

abstract public class ListBaseAdapter<T> extends android.widget.BaseAdapter {
    protected  Context context;
    protected List<T> datas;
    LayoutInflater mInflater;

    public ListBaseAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<T>datas){
        this.datas = datas;
    }
    public List<T> getDatas(){
        return datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public abstract   int getItemViewType(int position);
    public abstract int getItemLayoutId(int getItemViewType);
    public abstract void handleItem(int itemViewType , int position , T item , ViewHolder holder , boolean isRecycle);
    public void refreshData(List<T> list){

        if(list !=null && list.size()>0){
            clear();
            int size = list.size();
            for (int i=0;i<size;i++){
                datas.add(i,list.get(i));
                notifyDataSetChanged();
            }

        }
    }
    public void clear(){
//        int itemCount = datas.size();
//        datas.clear();
//        this.notifyItemRangeRemoved(0,itemCount);

        for (Iterator it = datas.iterator(); it.hasNext();){

            T t = (T) it.next();
            int position = datas.indexOf(t);
            it.remove();
            notifyDataSetChanged();
        }
    }
    public void loadMoreData(List<T> list){

        if(list !=null && list.size()>0){

            int size = list.size();
            int begin = datas.size();
            for (int i=0;i<size;i++){
                datas.add(list.get(i));
                notifyDataSetChanged();
            }

        }

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemLayoutType = getItemViewType(position);
        ViewHolder viewHolder = null;
        boolean isRecycle =false;
        if (convertView==null){
            convertView =  mInflater.inflate(getItemLayoutId(itemLayoutType) , null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
            isRecycle = true;
        }
        handleItem(itemLayoutType , position ,datas.get(position) , viewHolder , isRecycle);
        return convertView;
    }

    public class ViewHolder{
        View mRootView;
        SparseArray<View> mViews = new SparseArray<View>();
        public ViewHolder(View view){
            this.mRootView = view;
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

        protected <T extends View> T retrieveView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mRootView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
    }
}
