package cn.shoppingmall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.shoppingmall.activity.OrderListActivity;
import cn.shoppingmall.fragment.ClassifyFragment;
import cn.shoppingmall.fragment.HomeFragment;
import cn.shoppingmall.fragment.MineFragment;
import cn.shoppingmall.fragment.ShopCarFragment;

/**
 * Created by ${易淼} on 2017/8/28.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class PagerAdapter extends FragmentPagerAdapter{
    private List<Fragment>list = new ArrayList<Fragment>() ;




    private void choseFrag(){
        list.add(new HomeFragment());
        list.add(new ClassifyFragment());
        list.add(new ShopCarFragment());
        list.add(new MineFragment());
    }

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        choseFrag();
    }

    @Override
    public Fragment getItem(int position) {

        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
