package cn.shoppingmall.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jude.easyrecyclerview.EasyRecyclerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.adapter.CategorizeDetailProductAdapter;
import cn.shoppingmall.bean.ButtomListBean;
import cn.shoppingmall.bean.ListProductContentModel;
import cn.shoppingmall.http.RetrofitHttp;
import cn.shoppingmall.utils.NetUitls;
import cn.shoppingmall.utils.ToastUtils;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductActivity extends BaseActivity {

    @BindView(R.id.search_all_toolbar)
    Toolbar toolbar;

    @BindView(R.id.search_all_data)
    EasyRecyclerView recyclerView;

    private int indext = 1;
    private String searchkeyWord = "";
    private GridLayoutManager girdLayoutManager;
    private CategorizeDetailProductAdapter adapter;
    private String prodType = "-1";

    @Override
    protected void init() {
        toolbar.setTitle("全部商品");
        setToolbar(toolbar);
        searchkeyWord = getIntent().getStringExtra("search");
        prodType = getIntent().getStringExtra("ProdType");
        adapter = new CategorizeDetailProductAdapter(this);
        girdLayoutManager = new GridLayoutManager(this, 2);
        girdLayoutManager.setSpanSizeLookup(adapter.obtainTipSpanSizeLookUp());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setLayoutManager(girdLayoutManager);
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setErrorView(R.layout.search_no_data);
//        adapter.addAll(setData(searchkeyWord));
        if (adapter.getCount() == 0) {
            recyclerView.showError();
        }
        getAllProd(prodType, searchkeyWord);
    }

    @Override
    protected int viewId() {
        return R.layout.activity_all_product;
    }

    /**
     * 建立toolbar
     *
     * @param toolbar
     */
    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllProd(String CategoryId, String serchText) {
        Map<String, String> map = new HashMap();
        map.put("CategoryId", CategoryId);
        map.put("SearchText", serchText);
        map.put("pageIndex", "" + indext);
        map.put("pageSize", "10");
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        prodResult(RetrofitHttp.getRetrofit(builder.build()).getProductList(map));
    }

    private void prodResult(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    ListProductContentModel bean = MyApplication.gson.fromJson(result, ListProductContentModel.class);
                    if (bean.getSuccess().equals("true")) {
                        ListProductContentModel.DataEntity dataEntity = bean.getData();
                        List<ListProductContentModel.DataEntity.DataListEntity> list = dataEntity.getDataList();
                        adapter.addAll(list);

                    } else {
                        ToastUtils.showToast(result);
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
