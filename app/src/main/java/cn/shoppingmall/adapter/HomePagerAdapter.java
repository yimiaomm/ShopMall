package cn.shoppingmall.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import cn.shoppingmall.bean.ButtomListBean;
import cn.shoppingmall.bean.RecommentBean;
import cn.shoppingmall.fragment.HomePagerFragment;

/**
 * Created by ${易淼} on 2017/8/31.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class HomePagerAdapter  extends FragmentStatePagerAdapter{
    List<ButtomListBean.DataEntity> shoppinglist;
    private String result;
    public HomePagerAdapter(FragmentManager fm,List<ButtomListBean.DataEntity> shoppinglist,String result) {
        super(fm);
        this.shoppinglist = shoppinglist;
        this.result =result;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment  = new HomePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("index",result);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return shoppinglist.size();
    }
}
