package cn.shoppingmall.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.qqtheme.framework.picker.LinkagePicker;
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

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

public class AllProductActivity extends BaseActivity{

    @BindView(R.id.search_all_toolbar)
    Toolbar toolbar;

    @BindView(R.id.search_all_data)
    EasyRecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;
    private Handler handler = new Handler();
    private int indext = 1;
    private String searchkeyWord = "";
    private GridLayoutManager girdLayoutManager;
    private CategorizeDetailProductAdapter adapter;
    private String prodType = "-1";
    private int pageCount;
    private boolean isFresh=true;

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
        recyclerView.setAdapterWithProgress(adapter);
        if (adapter.getCount() == 0) {
            recyclerView.showError();
        }
        refresh_layout.setColorSchemeColors(getResources().getColor(R.color.red));
        refresh_layout.setRefreshing(true);
        refresh_layout.setOnRefreshListener(onRefreshListener);
//        adapter.setMore(R.layout.view_more,this);
//        adapter.setNoMore(R.layout.view_no_more);

        getAllProd(prodType, searchkeyWord);
        recyclerView.setErrorView(R.layout.search_no_data);
//        adapter.setError(R.layout.search_no_data);
//        recyclerView.setRefreshListener(this);
//        onRefresh();
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                refresh_layout.setRefreshing(false);
                if (newState == SCROLL_STATE_IDLE ) {
                    if (indext<=pageCount) {

                        refresh_layout.setRefreshing(true);
                        isFresh = false;
                        getAllProd(prodType, searchkeyWord);
                    }else {

                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            isFresh = true;
            getAllProd(prodType, searchkeyWord);
        }
    };



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

        if (isFresh){
            map.put("pageIndex", "" + indext);
            indext=1;
        }else {
          indext++;
            map.put("pageIndex", "" + indext);
        }
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
                    refresh_layout.setRefreshing(false);
                    String result = response.body().string();
                    ListProductContentModel bean = MyApplication.gson.fromJson(result, ListProductContentModel.class);
                    if (bean.getSuccess().equals("true")) {
                        ListProductContentModel.DataEntity dataEntity = bean.getData();
                        List<ListProductContentModel.DataEntity.DataListEntity> list = dataEntity.getDataList();
                        pageCount = dataEntity.getPageCount();
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

//    @Override
//    public void onRefresh() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //刷新
////                if (!isFresh) {
////                    adapter.pauseMore();
////                    return;
////                }
//                getAllProd(prodType, searchkeyWord);
//            }
//        }, 2000);
//    }
//
//    @Override
//    public void onLoadMore() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                adapter.clear();
//                //刷新
////                if (!isFresh) {
////                    adapter.pauseMore();
////                    return;
////                }
//                if (indext<=pageCount){
//                    indext++;
//                    getAllProd(prodType, searchkeyWord);
//                }
//            }
//        }, 2000);
//    }
}
