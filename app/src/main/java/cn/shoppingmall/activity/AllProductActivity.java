package cn.shoppingmall.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jude.easyrecyclerview.EasyRecyclerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.shoppingmall.R;
import cn.shoppingmall.adapter.CategorizeDetailProductAdapter;
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
    private String searchkeyWord;
    private GridLayoutManager girdLayoutManager;
    private CategorizeDetailProductAdapter adapter;

    @Override
    protected void init() {
        toolbar.setTitle("搜索商品");
        setToolbar(toolbar);
        if (getIntent().getBundleExtra("search") != null) {
            searchkeyWord = getIntent().getBundleExtra("search").getString("search");
        }
        adapter = new CategorizeDetailProductAdapter(this);
        girdLayoutManager = new GridLayoutManager(this, 2);
        girdLayoutManager.setSpanSizeLookup(adapter.obtainTipSpanSizeLookUp());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(girdLayoutManager);
        recyclerView.setAdapterWithProgress(adapter);
        recyclerView.setErrorView(R.layout.search_no_data);
//        adapter.addAll(setData(searchkeyWord));
        if (adapter.getCount() == 0) {
            recyclerView.showError();
        }
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

    private void getAllProd(String CategoryId,String serchText){
//                CategoryId (integer, optional): 商品类别,
//                SearchText (string, optional): 搜索内容(商品名称关键词搜索，支持多个关键词),
//                pageIndex (integer, optional): *第几页(起始请传入1),
//                pageSize (integer, optional): *每页条数,
//                timestamp (string, optional): *时间戳,
//                nonce (string, optional): 随机数,
//                signature (string, optional): 加密签名
        Map<String,String> map = new HashMap();
        map.put("CategoryId",CategoryId);
        map.put("SearchText",serchText);
        map.put("pageIndex",""+indext);
        map.put("pageSize","10");
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        prodResult(RetrofitHttp.getRetrofit(builder.build()).getProductList(map));
    }
    private void prodResult(Call<ResponseBody>call){
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    ToastUtils.showToast(result);
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
