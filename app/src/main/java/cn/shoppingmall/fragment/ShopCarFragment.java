package cn.shoppingmall.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.ShopCarDeatil;
import cn.shoppingmall.adapter.ShoppingDataAdapter;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.bean.ShopCarBean;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import cn.shoppingmall.view.PromptDialog;
import cn.shoppingmall.viewHolder.ShoppingDataViewHolder;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.data;
import static android.R.attr.id;
import static android.media.CamcorderProfile.get;
import static cn.shoppingmall.R.id.mRecyclerView;
import static cn.shoppingmall.R.id.shopping_spend;

public class ShopCarFragment extends BaseFragment {
    @BindView(R.id.shopping)
    TextView shopping;

    static TextView shopping_toal_data;
    @BindView(R.id.shop_head_action)
    LinearLayout shop_head_action;

    private static EasyRecyclerView recyclerView;
    @BindView(R.id.shop_content)
    LinearLayout shopContent;
    @BindView(R.id.tv_delete)
    TextView tv_delete;

    @BindView(R.id.shop_total)
    TextView shop_total;
    @BindView(R.id.shopping_pay)
    LinearLayout shopping_pay;
    @BindView(R.id.shopping_calculate_layout)
    LinearLayout shopping_calculate_layout;
    @BindView(R.id.shop_end_action)
    LinearLayout action_layout;
    private static TextView shopping_spend;
    private static TextView shopping_data_count_sum;
    @BindView(R.id.tv_freight)
    TextView tv_freight;
    @BindView(R.id.shopping_edit)
    TextView shopping_edit;

    private  static CheckBox check_all;
    public static ShopCarFragment object = new ShopCarFragment();


    private static ArrayList<ShopCarBean.DataEntity.ShopCartListEntity> arrayList = new ArrayList<>();

    private static Context mContext;

    private static ShoppingDataAdapter adapter;

    private GridLayoutManager girdLayoutManager;
    //    public  static  CheckBox checkAll;
    public static String isCheckAll = "0";

    public static boolean isCheckSingle = false;

    private boolean isEditState = false;

    private static int uid;
    private static ShopCarBean carBean;
    private static DataEntity userinfo;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        adapter = new ShoppingDataAdapter(mContext);
    }


    @Override
    public void onResume() {
        if (adapter.getCount() == 0 && adapter != null) {
            recyclerView.showError();
        }
        super.onResume();
    }
    @Override
    public void onStart() {
        super.onStart();
        editActionState();
        userinfo = new GreenDaoUtlis(getActivity()).queryDefult();
        if (userinfo == null) {
            recyclerView.setErrorView(R.layout.shopping_no_data_error);
            return;
        }
        readShopCarList();
    }


    @Override
    public void onPause() {
        Log.e("","");
        isEditState = false;
        super.onPause();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_car;
    }

    @Override
    public void init() {
        check_all = (CheckBox) view.findViewById(R.id.check_all);
        shopping_toal_data = (TextView) view.findViewById(R.id.shopping_toal_data);
        shopping_data_count_sum = (TextView) view.findViewById(R.id.shopping_data_count_sum);
        shopping_spend = (TextView) view.findViewById(R.id.shopping_spend);
        recyclerView = (EasyRecyclerView) view.findViewById(R.id.shopping_list_data);
        setHasOptionsMenu(true);
        check_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isCheckAll.equals("0")){
                    isCheckAll="-1";
                }else {
                    updataShopCartMulti(isChecked);
                }
            }
        });
        adapter = new ShoppingDataAdapter(cxt);
        girdLayoutManager = new GridLayoutManager(getActivity(), 2);
        girdLayoutManager.setSpanSizeLookup(adapter.obtainTipSpanSizeLookUp());
        recyclerView.setLayoutManager(girdLayoutManager);

    }


    private void deleteMit(){
//        UserId (string, optional): *用户名 ,
//                IDList (string, optional): *要删除的数据行ID列表(英文逗号隔开) ,
//                Token (string, optional): *登录凭证 ,

        if (null ==carBean){
            ToastUtils.showToast("请添加商品");
            return;
        }
        if (!carBean.getSuccess().equals("true")){
            return;
        }
        ShopCarBean.DataEntity dataEntity = carBean.getData();

        if (dataEntity==null){
            return;
        }
        List<ShopCarBean.DataEntity.ShopCartListEntity>list = dataEntity.getShopCartList();
        if (list.size()<=0||list==null){
            ToastUtils.showToast("请添加商品");
            return;
        }

        Map<String,String>map = new HashMap<>();
        map.put("UserId",userinfo.getUserId());
        StringBuffer str = new StringBuffer();
        for (int i = 0;i<list.size();i++){
            String isCheck = list.get(i).getIsCheck();
            if ("true".equals(isCheck)){
              str.append(list.get(i).getID());
                str.append(",");
            }
        }
        if (TextUtils.isEmpty(str)){
            ToastUtils.showToast("请选择删除商品");
            return;
        }
        String newstr = str.subSequence(0,str.lastIndexOf(",")).toString();
        map.put("IDList",newstr);
        map.put("Token",userinfo.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        deleteMitResult(RetrofitHttp.getRetrofit(builder.build()).delShopCartMulti(map));
    }
    private void deleteMitResult(Call<ResponseBody>call){
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
// {"success":true,"msg":"删除成功","data":{"TotalNum":0,"TotalPrice":0.0,"Freight":0.0,"ShopCartList":[]},"errcode":""}
                    String result  = response.body().string().toString();
                    ShopCarBean carBeans = MyApplication.gson.fromJson(result,ShopCarBean.class);
                    if (carBeans.getSuccess().equals("true")){
                        carBean = carBeans;
                        initData();
                        specialUpdate();
                    }else {
                        ToastUtils.showToast(carBeans.getMsg());
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


    private void readShopCarList() {
//        UserId (string, optional): *用户名 ,
//                Token (string, optional): *登录凭证 ,
        DataEntity data = new GreenDaoUtlis(getActivity()).query();
        if (data == null) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("UserId", data.getUserId());
        map.put("Token", data.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getShopCarResult(RetrofitHttp.getRetrofit(builder.build()).getShopCar(map));
    }

    private void getShopCarResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    carBean = MyApplication.gson.fromJson(result, ShopCarBean.class);
                    if (carBean.getData() == null) {
                        recyclerView.showError();
                        recyclerView.setErrorView(R.layout.shopping_no_data_error);
                        return;
                    }
                    if ("true".equals(carBean.getSuccess())) {
                        initData();
                        if (adapter.getCount() != 0) {
                            if (action_layout.getVisibility() == View.GONE) {
                                action_layout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (adapter.getCount() == 0) {
                                recyclerView.showError();
                                action_layout.setVisibility(View.VISIBLE);

                            }
                        }
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

    private static void initData(){
        List<ShopCarBean.DataEntity.ShopCartListEntity> shoplist = carBean.getData().getShopCartList();
        if (adapter.getCount() != 0) {
            adapter.clear();
        }
        adapter.addAll(shoplist);
        List<String> numList = new ArrayList<>();
        for (int i = 0; i < shoplist.size(); i++) {
            ShopCarBean.DataEntity.ShopCartListEntity entity = shoplist.get(i);
            String isCheck = entity.getIsCheck();
            shoplist.get(i).setPid(i);
            if (isCheck.equals("true")) {
                numList.add("" + i);
                adapter.setCheckBoolean(i, true);
            } else {
                adapter.setCheckBoolean(i, false);
            }
        }

        if (shoplist.size()<=0){
            check_all.setClickable(false);
        }else {
            check_all.setClickable(true);
        }
        if (numList.size()==adapter.getCount()){
            isCheckAll = "0";
            check_all.setChecked(true);
        }else {
            check_all.setChecked(false);
        }
        recyclerView.setAdapterWithProgress(adapter);
        specialUpdate();
        shopping_spend.setText(carBean.getData().getTotalPrice());
        shopping_data_count_sum.setText(carBean.getData().getTotalNum());
        shopping_toal_data.setText("(" + String.valueOf(adapter.getCount()) + ")");
    }
    public void checkAllState() {
        Iterator iterator = adapter.getIsCheckList().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Integer key = (Integer) entry.getKey();
            adapter.setCheckBoolean(key, true);
        }
        specialUpdate();
    }

    public void unCheckAll() {
        Iterator iterator = adapter.getIsCheckList().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Integer key = (Integer) entry.getKey();
            adapter.setCheckBoolean(key, false);
        }
        specialUpdate();
    }

    private static void specialUpdate() {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                if (adapter != null)
                    adapter.notifyDataSetChanged();
            }
        };
        handler.post(r);
    }

    public static boolean ischeckAllState() {
        Iterator iterator = adapter.getIsCheckList().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            boolean val = (Boolean) entry.getValue();
            if (val != true) {
                return false;
            }
        }
        return true;
    }




    @OnClick({R.id.shopping_pay, R.id.shopping_edit, R.id.tv_delete,R.id.check_all})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.check_all:
                break;
            case R.id.shopping_edit:
                if (isEditState) {
                    editActionState();
                } else {
                    finishActionState();
                }
                break;
            case R.id.tv_delete:
                new PromptDialog.Builder(getContext())
                        .setTitle("提示")
                        .setTitleColor(R.color.white)
                        .setViewStyle(PromptDialog.VIEW_STYLE_TITLEBAR_SKYBLUE)
                        .setMessage("确定删除这些商品吗?")
                        .setMessageSize(20f)
                        .setButton1("确定", new PromptDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                deleteMit();
                                dialog.dismiss();
                            }
                        })
                        .setButton2("取消", new PromptDialog.OnClickListener() {
                            @Override
                            public void onClick(Dialog dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.shopping_pay:
                if (null ==carBean){
                    ToastUtils.showToast("请添加商品");
                    return;
                }
                if (!carBean.getSuccess().equals("true")){
                    return;
                }
                ShopCarBean.DataEntity dataEntity = carBean.getData();

                if (dataEntity==null){
                    return;
                }
                List<ShopCarBean.DataEntity.ShopCartListEntity>list = dataEntity.getShopCartList();
                if (list.size()<=0||list==null){
                    ToastUtils.showToast("请添加商品");
                    return;
                }

                StringBuffer str = new StringBuffer();
                for (int i = 0;i<list.size();i++){
                    String isCheck = list.get(i).getIsCheck();
                    if ("true".equals(isCheck)){
                        str.append(list.get(i).getID());
                    }
                }
                if (TextUtils.isEmpty(str)){
                    ToastUtils.showToast("请选择结算商品");
                    return;
                }

                Intent intent = new Intent(getActivity(), ShopCarDeatil.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CartBean", carBean.getData());
                intent.putExtra("bundle", bundle);
                startActivity(intent);

//                if (getTotalSum() > 0) {
//                    setUploadOrderData();
//                    Intent intent = new Intent();
////                    intent.putParcelableArrayListExtra("data", arrayList);
////                    intent.setClass(getContext(), ProductPayDetailActivity.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(mContext, "没有选择商品", Toast.LENGTH_SHORT).show();
//                }
                break;
        }
    }

    public void editActionState() {
        ShoppingDataAdapter.setDisplay(false);
        shopping_edit.setText("编辑");
        isEditState = false;
        shopping_calculate_layout.setVisibility(View.VISIBLE);
        tv_delete.setVisibility(View.GONE);
        specialUpdate();
    }

    public void finishActionState() {
        ShoppingDataAdapter.setDisplay(true);
        shopping_edit.setText("完成");
        isEditState = true;
        shopping_calculate_layout.setVisibility(View.GONE);
        tv_delete.setVisibility(View.VISIBLE);
        specialUpdate();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.shopcar_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_shopcar:
                ToastUtils.showToast(item.getTitle().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    //删除操作
    public static void deleteOrder(String ID) {
//        ID (integer, optional): *要删除的数据行ID ,
//                UserId (string, optional): *用户名 ,
//                Token (string, optional): *登录凭证 ,
//                timestamp (string, optional): *时间戳 ,
//                nonce (string, optional): 随机数 ,
//                signature (string, optional): 加密签名
        Map<String, String> map = new HashMap<>();
        map.put("ID", ID);
        map.put("UserId", userinfo.getUserId());
        map.put("Token", userinfo.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        deleteOrderResult(RetrofitHttp.getRetrofit(builder.build()).delShopCart(map), ID);
    }

    private static void deleteOrderResult(Call<ResponseBody> call, final String carid) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    ShopCarBean carBeans = MyApplication.gson.fromJson(result, ShopCarBean.class);

                    if ("true".equals(carBeans.getSuccess())) {
                        carBean = carBeans;
                        ToastUtils.showToast(carBean.getMsg());
                        initData();
                        specialUpdate();
                    } else {
                        ToastUtils.showToast(carBeans.getMsg());
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

    //更新操作的网络请求
    public static void updataShopCar(String ID, String num, String isCheck) {
//        UserId (string, optional): *用户名 ,
//                ID (integer, optional): *购物车商品ID ,
//                BuyNum (integer, optional): *商品数量(大于0有效) ,
//                IsCheck (boolean, optional): *选中状态(true选中) ,
//                Token (string, optional): *登录凭证 ,
//                timestamp (string, optional): *时间戳 ,
//                nonce (string, optional): 随机数 ,
//                signature (string, optional): 加密签名
        Map<String, String> map = new HashMap<>();
        map.put("ID", ID);
        map.put("BuyNum", num);
        map.put("IsCheck", isCheck);
        map.put("UserId", userinfo.getUserId());
        map.put("Token", userinfo.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        updataShopCarresult(RetrofitHttp.getRetrofit(builder.build()).updateShopCar(map));
    }

    private static void updataShopCarresult(final Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    ShopCarBean carBeans = MyApplication.gson.fromJson(result, ShopCarBean.class);
                    if (carBeans.getSuccess().equals("true")){
                        carBean = carBeans;
                        if (carBean==null){
                            adapter.clear();
                            specialUpdate();
                            return;
                        }
                        initData();
                        specialUpdate();
                    }else {
                        ToastUtils.showToast(carBeans.getMsg());
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
    private void updataShopCartMulti(boolean isCheck){
        Map<String,String>map = new HashMap<>();
        map.put("UserId",userinfo.getUserId());
        map.put("IDList","");
        map.put("IsCheck",""+isCheck);
        map.put("Token",userinfo.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        updataShopCartMultiResult(RetrofitHttp.getRetrofit(builder.build()).updataShopCartMulti(map));
    }
    private void updataShopCartMultiResult(final Call<ResponseBody>call){
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    ResponseBody body = response.body();
                    if (body==null)return;
                    String result = response.body().string().toString();
                    if (result==null){
                        return;
                    }
                    ShopCarBean carBeans = MyApplication.gson.fromJson(result,ShopCarBean.class);
                    if ("true".equals(carBeans.getSuccess())){
                        carBean = carBeans;
                        initData();
                    }else {
                        ToastUtils.showToast(carBeans.getMsg());
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
}
