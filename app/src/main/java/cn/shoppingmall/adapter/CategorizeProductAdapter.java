package cn.shoppingmall.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import cn.shoppingmall.bean.ClasslflyBean;
import cn.shoppingmall.fragment.CategorizeListContentFragment;


/**
 * author：Anumbrella
 * Date：16/5/26 下午7:08
 */
public class CategorizeProductAdapter extends FragmentStatePagerAdapter {


    private List<ClasslflyBean.DataBean>list;

    public CategorizeProductAdapter(FragmentManager fm, List<ClasslflyBean.DataBean> array) {
        super(fm);
        list = array;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment =  CategorizeListContentFragment.newInstance(list.get(position),position);
        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
