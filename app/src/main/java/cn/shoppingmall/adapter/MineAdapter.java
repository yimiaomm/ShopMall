package cn.shoppingmall.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;

/**
 * developer: liyongfeng
 * created on: 2017/9/1 22:56
 * description:
 */
public class MineAdapter extends RecyclerView.Adapter<MineAdapter.ViewHolder> {
    private final int[] imageRes;
    private final String[] itemNames;
    private onItemClickListener clickListener;

    public MineAdapter(int[] imageRes, String[] itemNames) {
        this.imageRes = imageRes;
        this.itemNames = itemNames;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(MyApplication.getAppCtx()).
                inflate(R.layout.item_fragment_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(view,(int)view.getTag());
                }
            }
        });
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(View view, int tag);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ivIcon.setImageResource(imageRes[position]);
        holder.tvName.setText(itemNames[position]);
        holder.view.setTag(position);
    }

    @Override
    public int getItemCount() {
        return imageRes == null ? 0 : imageRes.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view=view;
        }
    }
}
