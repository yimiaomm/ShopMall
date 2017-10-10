package cn.shoppingmall.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.util.List;

import cn.shoppingmall.R;
import okhttp3.ResponseBody;
import retrofit2.Callback;



public class SpinerPopWindow<T> extends PopupWindow {
    private LayoutInflater inflater;
    private ListView mListView;
    public MyAdapter mAdapter;
    private Context ctx;
    private OnItemClickListeners listener;
    public List<T>list;
//    public  TextView tv_show_address;
    public SpinerPopWindow(Context context) {
        super(context);
        this.ctx = context;
        inflater = LayoutInflater.from(context);
        init();
    }

    public void init() {
        View view = inflater.inflate(R.layout.spiner_window_layout, null);
        setContentView(view);
        setWidth(LayoutParams.WRAP_CONTENT);
        setHeight(LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00);
        setBackgroundDrawable(dw);
        setAnimationStyle(R.style.popupwindow_animation);
        mListView = (ListView) view.findViewById(R.id.listview);
//        tv_show_address = (TextView) view.findViewById(R.id.tv_show_address);


    }

    public void setData(List<T> list){
        this.list = list;
        if (mAdapter!=null){
            mListView.setAdapter(null);
            mAdapter.notifyDataSetChanged();
        }
        mAdapter = new MyAdapter(list);
        mListView.setAdapter(mAdapter);
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.setSelectedItem(position);
                if (listener != null) {
                    listener.setOnItemClicks(parent, view, position, id);
                }
            }
        });
    }

//    public   void setText(String str){
//        tv_show_address.setVisibility(View.VISIBLE);
//        tv_show_address.setText(str);
//    }

    public void setOnItemClickLineners(OnItemClickListeners listener) {
        this.listener = listener;
    }


    public interface OnItemClickListeners {
        void setOnItemClicks(AdapterView<?> parent, View view, int position, long id);
    }

    public class MyAdapter extends BaseAdapter {
        private int selectedItems = -1;
        private List<T> list;

        public MyAdapter(List<T> list) {
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

        public void setSelectedItem(int selectedItem) {
            this.selectedItems = selectedItem;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.spiner_item_layout, null);
                holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(getItem(position).toString());
//            if(position == selectedItems) {
//                convertView.setBackgroundColor(ctx.getResources().getColor(R.color.main_green));
//                holder.tvName.setTextColor(ctx.getResources().getColor(R.color.white));
//            }else {
//                convertView.setBackgroundColor(ctx.getResources().getColor(R.color.white));
//                holder.tvName.setTextColor(ctx.getResources().getColor(R.color.black_4));
//            }

            return convertView;
        }
    }

    private class ViewHolder {
        private TextView tvName;
    }
}
