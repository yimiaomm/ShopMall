package cn.shoppingmall.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.shoppingmall.bean.ProductDetailsBean;
import cn.shoppingmall.fragment.BaseFragment;
import cn.shoppingmall.fragment.orderFragment.OrderBaseFragment;
import cn.shoppingmall.fragment.productFrag.PicDetailsFragment;
import cn.shoppingmall.fragment.productFrag.ProductFragment;

import static cn.shoppingmall.activity.OrderListActivity.FRAGMENT_MAP_ORDER;


/**
 * Created by ${易淼} on 2017/9/7.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class ProductDetailAdapter extends FragmentPagerAdapter{
    private BaseFragment fragment;
    private List<BaseFragment>list;
    public ProductDetailAdapter(FragmentManager supportFragmentManager,List<BaseFragment> list) {
        super(supportFragmentManager);
      this.list = list;

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
