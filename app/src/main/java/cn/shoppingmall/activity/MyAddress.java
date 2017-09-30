package cn.shoppingmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.adapter.AddressAdapter;
import cn.shoppingmall.bean.AddressBean;
import cn.shoppingmall.bean.DataEntity;
import cn.shoppingmall.greenDao.GreenDaoUtlis;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAddress extends BaseActivity {


    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.tv_back)
    TextView tvBack;
    private String ADDRESSBEAN = "ADDRESSBEAN";
    private String BUNDLE = "BUNDLE";
    private List<AddressBean.DataEntity> list;
    private DataEntity user;

    @Override
    protected void init() {
        listView.setFocusable(true);
        listView.setFocusableInTouchMode(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.size() > 0 && list != null) {
                    AddressBean.DataEntity dataEntity = list.get(position);
                    if (dataEntity == null) {
                        return;
                    }
                    Intent intent = new Intent(MyAddress.this, AddressChagerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BUNDLE, dataEntity);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        user = new GreenDaoUtlis(this).query();
        getAddress();
    }

    private void getAddress() {
        Map<String, String> map = new HashMap<>();
//        UserId (string, optional): *用户名,
//                Token (string, optional): *登录凭证,
        map.put("UserId", user.getUserId());
        map.put("Token", user.getToken());
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        getAddressResult(RetrofitHttp.getRetrofit(builder.build()).getAddressList(map));
    }

    private void getAddressResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string().toString();
                    AddressBean addressBean = MyApplication.gson.fromJson(result, AddressBean.class);
                    if ("true".equals(addressBean.getSuccess())) {
                        if (list != null) {
                            list.clear();
                        }
                        list = addressBean.getData();
                        listView.setAdapter(new AddressAdapter(list, MyAddress.this));
                    } else {
                        ToastUtils.showToast(addressBean.getMsg());
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

    @Override
    protected int viewId() {
        return R.layout.activity_my_address;
    }




    @OnClick({R.id.tv_back, R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_add_address:
                Intent intent = new Intent(this, AddressChagerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
