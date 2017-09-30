package cn.shoppingmall.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


import java.util.HashMap;

import cn.shoppingmall.bean.ShopCarBean;
import cn.shoppingmall.viewHolder.ShoppingDataViewHolder;

/**
 * author：Anumbrella
 * Date：16/6/4 下午9:57
 */
public class ShoppingDataAdapter extends RecyclerArrayAdapter<ShopCarBean.DataEntity.ShopCartListEntity> {

    private static HashMap<Integer, Boolean> isCheckList = new HashMap<>();

    public ShoppingDataAdapter(Context context) {
        super(context);
    }

    private static boolean display = false;


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingDataViewHolder(parent);
    }


    @Override
    public int getPosition(ShopCarBean.DataEntity.ShopCartListEntity item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }






    public static void setCheckBoolean(int pid, boolean bool) {
        isCheckList.put(pid, bool);
    }
//
    public static HashMap<Integer, Boolean> getIsCheckList() {
        return isCheckList;
    }

    public static boolean getIsCheck(int pid) {
        return isCheckList.get(pid);
    }

    public TipSpanSizeLookUp obtainTipSpanSizeLookUp() {
        return new TipSpanSizeLookUp();
    }

    public static boolean getDisplay() {
        return display;
    }

    public static void setDisplay(boolean isDisplay) {
        display = isDisplay;
    }



    public class TipSpanSizeLookUp extends RecyclerArrayAdapter.GridSpanSizeLookup {

        public TipSpanSizeLookUp() {
            //列数默认为2
            super(2);
        }

        @Override
        public int getSpanSize(int position) {
            return 2;
        }
    }
}
