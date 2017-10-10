package cn.shoppingmall.fragment.orderFragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

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

import static android.R.attr.id;

/**
 * Created by ${易淼} on 2017/8/31.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public abstract class OrderBaseFragment extends Fragment {
    protected View view;
    private EasyRecyclerView recycler_view;
    protected static String ORDER_SUTTAS = "";
    private static DataEntity userinfo;
    private static OrderListAdapter adapter;
    private static List<OrderListBean.DataBean.DataListBean> list;

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
        Map<String, String> map = new HashMap<>();
        map.put("UserId", userinfo.getUserId());
        map.put("OrderStatus", ORDER_SUTTAS);
        map.put("pageIndex", "1");
        map.put("pageSize", "10");
        map.put("Token", userinfo.getToken());
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
                    OrderListBean bean = MyApplication.gson.fromJson(result, OrderListBean.class);
                    if (bean.isSuccess().equals("true")) {
                        if (bean.getData() != null) {
                            list = bean.getData().getDataList();
                            if (list.size() > 0) {
                                adapter = new OrderListAdapter(list, getActivity());
                                int page = bean.getData().getPageCount();
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                recycler_view.setLayoutManager(layoutManager);

                                recycler_view.setAdapter(adapter);
                                adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        ToastUtils.showToast(position + "");
                                    }
                                });
                            }
                        }
                    } else {
                        ToastUtils.showToast(bean.getMsg());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    private static void specialUpdate() {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                if (adapter!=null)
                    adapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }
    private void initData() {
        getOrderList();
    }

    public static void deleteOrder(String id) {
//                ID (integer, optional): *要删除的数据行ID,
//                UserId (string, optional): *用户名,
//                Token (string, optional): *登录凭证,
        Map<String,String>map = new HashMap<>();
        map.put("ID",id);
        map.put("UserId",userinfo.getUserId());
        map.put("Token",userinfo.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder= new OkHttpClient.Builder();

        deleteResult(RetrofitHttp.getRetrofit(builder.build()).delorderForm(map),id);
    }

    private static void deleteResult(Call<ResponseBody> call, final String id) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    JSONObject object = new JSONObject(result);
                    String success = object.has("success") ? object.getString("success") : "";
                    String msg = object.has("msg") ? object.getString("msg") : "";
                    if ("true".equals(success)) {
                        specialUpdate();
                        for (int i=0;i<list.size();i++){
                            if (list.get(i).getID().equals(id)){
                                list.remove(list.get(i));
                                adapter.notifyDataSetChanged();
                            }
                        }
                    } else {
                        ToastUtils.showToast(msg);
                    }
                    ToastUtils.showToast(result);
                    specialUpdate();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public static void updataOrder(String id,String newStatus,String oldStatus) {
//                ID(integer, optional): *订单ID,
//                NewStatus(integer, optional): *变更后的订单状态(0.已关闭 1. 未付款 2. 已付款 3. 已发货 4. 交易完成)
//        OldStatus(integer, optional): *变更前的订单状态(0.已关闭 1. 未付款 2. 已付款 3. 已发货 4. 交易完成)
//        Token(string, optional): *登录凭证,
//                timestamp(string, optional): *时间戳,
//                nonce(string, optional):随机数,
//                signature(string, optional):加密签名
        Map<String,String>map = new HashMap<>();
        map.put("ID",id);
        map.put("NewStatus",newStatus);
        map.put("OldStatus",oldStatus);
        map.put("Token",userinfo.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder= new OkHttpClient.Builder();
        updataResult(RetrofitHttp.getRetrofit(builder.build()).upStatus(map),id);
    }

    private static void updataResult(Call<ResponseBody> call, final String id) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    JSONObject object = new JSONObject(result);
                    String success = object.has("success") ? object.getString("success") : "";
                    String msg = object.has("msg") ? object.getString("msg") : "";
                    if ("true".equals(success)) {
                        specialUpdate();
                        for (int i=0;i<list.size();i++){
                            if (list.get(i).getID().equals(id)){
                                list.get(i).setOrderStatus("0");
                                adapter.notifyDataSetChanged();
                            }
                        }
                    } else {
                        ToastUtils.showToast(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    protected abstract View getContentView();

    protected abstract void initView();
}
