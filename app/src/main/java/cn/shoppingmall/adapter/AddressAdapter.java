package cn.shoppingmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.BaseAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.shoppingmall.MyApplication;
import cn.shoppingmall.R;
import cn.shoppingmall.activity.AddressChagerActivity;
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


/**
 * Created by ${易淼} on 2017/9/1.
 * 电话：15036145858
 * 邮箱：11058289@qq.com
 */

public class AddressAdapter extends BaseAdapter{
    private List<AddressBean.DataEntity>list;
    private Context context;
    private String BUNDLE = "BUNDLE";
    private String isDefault;

    public AddressAdapter(List<AddressBean.DataEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size()>0?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder v;
        if (convertView==null) {
             v = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_address_item, null);
            v.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            v.tel = (TextView) convertView.findViewById(R.id.tv_tel);
            v.tv_address  = (TextView) convertView.findViewById(R.id.tv_address);
            v.tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
            v.tv_edit = (TextView) convertView.findViewById(R.id.tv_edite);
            v.cb_setting = (CheckBox) convertView.findViewById(R.id.cb_setting);
            convertView.setTag(v);
        }else {
            v = (ViewHolder) convertView.getTag();
        }
        v.tel.setText(list.get(position).getTel());
        v.tv_name.setText(list.get(position).getName());
        v.tv_address.setText(list.get(position).getCity()+" "+list.get(position).getCity()+" "+list.get(position).getCounty()+"\n"+list.get(position).getAddr());
        isDefault = list.get(position).getIsDefault();
        if ("0".equals(isDefault)){
            v.cb_setting.setChecked(false);
        }else if ("1".equals(isDefault)){
            v.cb_setting.setChecked(true);
        }
        v.tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(context, AddressChagerActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(BUNDLE,list.get(position));
                        intent.putExtras(bundle);
                        context.startActivity(intent);

            }
        });
        v.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataEntity dataEntity = new GreenDaoUtlis(context).query();
                deleteAddress(dataEntity.getToken(),dataEntity.getUserId(),list.get(position).getID());
            }
        });
        v.cb_setting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            DataEntity dataEntity = new GreenDaoUtlis(context).query();
                            AddressChagerActivity.settingAddress(dataEntity.getToken(),dataEntity.getUserId(),list.get(position).getID());
                            notifyDataSetChanged();
        }
            }
        });
        return convertView;
    }

    class  ViewHolder{
        TextView tv_name,tel,tv_address,tv_edit,tv_delete;
        CheckBox cb_setting;

    }
    private  void deleteAddress(String token,String userId,String addressId) {

        Map<String, String> map = new HashMap<>();
        map.put("ID",addressId);
        map.put("UserId",userId);
        map.put("Token",token);
        map = NetUitls.getHashMapData(map);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        deleteAddressResult(RetrofitHttp.getRetrofit(builder.build()).delAddress(map));
    }

    private void deleteAddressResult(Call<ResponseBody> responseBody) {
        responseBody.enqueue(new Callback<ResponseBody>() {
            private String result;
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    result = response.body().string().toString();
                    AddressBean addressBean = MyApplication.gson.fromJson(result, AddressBean.class);
                    if ("true".equals(addressBean.getSuccess())) {
                        ToastUtils.showToast(addressBean.getMsg());
                        list = addressBean.getData();
                        notifyDataSetChanged();
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

}
