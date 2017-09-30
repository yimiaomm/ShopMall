package cn.shoppingmall.fragment.orderFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.adapter.BaseAdapter;
import cn.shoppingmall.adapter.OrderListAdapter;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.bean.OrderListBean;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ${易淼} on 2017/8/31.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public abstract class OrderBaseFragment extends Fragment {
    protected View view;
    private EasyRecyclerView recycler_view;
    protected static String ORDER_SUTTAS = "";
    private DataEntity userinfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.order_item_page, null);
        recycler_view = (EasyRecyclerView) view.findViewById(R.id.recycler_view);
        userinfo = new GreenDaoUtlis(getActivity()).query();
        initView();
        initData();
        return view;
    }

    private void getOrderList() {
//        UserId(string, optional): *用户名,
//                OrderStatus(integer, optional): *订单状态(0.已关闭 1. 未付款 2. 已付款 3. 已发货 4. 交易完成),
//        pageIndex(integer, optional): *第几页(起始请传入1),
//                pageSize(integer, optional): *每页条数,
//                Token(string, optional): *登录凭证,
//                timestamp(string, optional): *时间戳,
//                nonce(string, optional):随机数,
//                signature(string, optional):加密签名
        Map<String,String>map = new HashMap<>();
        map.put("UserId",userinfo.getUserId());
        map.put("OrderStatus",ORDER_SUTTAS);
        map.put("pageIndex","1");
        map.put("pageSize","10");
        map.put("Token",userinfo.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getOrderListResult(RetrofitHttp.getRetrofit(builder.build()).getOrderList(map));
    }

    private void getOrderListResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    OrderListBean bean = MyApplication.gson.fromJson(result,OrderListBean.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    private void initData() {
        getOrderList();
        List<OrderListBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderListBean bean = new OrderListBean();
            bean.setOrderId("1000456");
            bean.setOrderStatus("未付款");
            bean.setOrderImageUrl("http://img02.liwushuo.com/image/150615/urgs9vy8a.png-pw144");
            bean.setOrderContent("这是一个测试的商品，" +
                    "需要测试行数为两行，" +
                    "孝悌之至通于神明光于四海无所不通，" +
                    "身体发肤受之父母不敢毁伤孝之始也，" +
                    "立身行道扬名于后世以显父母孝之终也");
            bean.setOrderNewPrice("199");
            bean.setOrderOldPrice("356");
            bean.setOrderCount("2");
            list.add(bean);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(layoutManager);
        OrderListAdapter adapter = new OrderListAdapter(list, getActivity());
        recycler_view.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showToast(position+"");
            }
        });
    }



    protected abstract View getContentView();

    protected abstract void initView();
}
