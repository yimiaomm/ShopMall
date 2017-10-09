package cn.shoppingmall.http;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author：Anumbrella
 * Date：16/5/29 下午3:52
 */

public interface HttpInterface {
    /**
     * 创建用户
     */
    @FormUrlEncoded
    @POST("/user/register")
    Call<ResponseBody> registerUser(@FieldMap Map<String, String> map);

    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    Call<ResponseBody> login(@FieldMap Map<String, String> map);

    /**
     * 刷新个人信息的接口
     */
    @FormUrlEncoded
    @POST("/user/refreshuser")
    Call<ResponseBody> refreshUser(@FieldMap Map<String, String> map);


    /**
     * 获取商品内容
     */
    @FormUrlEncoded
    @POST("/shop/getprodview")
    Call<ResponseBody> getProductsContent(@FieldMap Map<String, String> action);


    /**
     * 获取推荐轮播图片
     */
    @FormUrlEncoded
    @POST("/shop/getslide")
    Call<ResponseBody> getBanners(@FieldMap Map<String, String> action);

    /**
     * 首页商品信息接口
     */
    @FormUrlEncoded
    @POST("/shop/gethomeprod")
    Call<ResponseBody> getHomeProd(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("/shop/getproductlist")
    Call<ResponseBody> getProductList(@FieldMap Map<String, String> map);
    /**
     * 商品分类
     * */
    @FormUrlEncoded
    @POST("/shop/getcategory")
    Call<ResponseBody>getCategory(@FieldMap Map<String,String>map);
    /***
     * 购物车接口
     */

    @FormUrlEncoded
    @POST("/shop/getshopcart")
    Call<ResponseBody> getShopCar(@FieldMap Map<String, String> map);


    /**
     * 加入购物车接口
     */
    @FormUrlEncoded
    @POST("/shop/addshopcart")
    Call<ResponseBody> addShopCart(@FieldMap Map<String, String> data);


    /**
     * 删除购物车商品的接口
     */
    @FormUrlEncoded
    @POST("/shop/delshopcart")
    Call<ResponseBody> delShopCart(@FieldMap Map<String, String> data);

/**
 * g更新购物车
 * */
    @FormUrlEncoded
    @POST("/shop/updateshopcart")
    Call<ResponseBody> updateShopCar(@FieldMap Map<String, String> data);


    /**
     * 读取收货地址列表
     */
    @FormUrlEncoded
    @POST("/addr/getaddresslist")
    Call<ResponseBody> getAddressList(@FieldMap Map<String, String> data);


    /**
     * 添加/修改收货地址
     */

    @FormUrlEncoded
    @POST("/addr/addaddress")
    Call<ResponseBody> addAddress(@FieldMap Map<String, String> data);

    /**
     * 设置默认收货地址
     */
    @FormUrlEncoded
    @POST("/addr/setdefault")
    Call<ResponseBody> setDefault(@FieldMap Map<String, String> data);


    /**
     * 删除收货地址
     */
    @FormUrlEncoded
    @POST("/addr/deladdress")
    Call<ResponseBody> delAddress(@FieldMap Map<String, String> data);

    /**
     * 读取订单列表
     */
    @FormUrlEncoded
    @POST("/order/getorderlist")
    Call<ResponseBody> getOrderList(@FieldMap Map<String, String> data);

    /**
     * 读取各个状态的订单数量
     */
    @FormUrlEncoded
    @POST("/order/getordercount")
    Call<ResponseBody> getOrderCount(@FieldMap Map<String, String> data);

    /**
     * 创建订单
     **/
    @FormUrlEncoded
    @POST("/order/createorder")
    Call<ResponseBody> creatOrder(@FieldMap Map<String, String> data);

    /**
     * 创建订单
     **/
    @FormUrlEncoded
    @POST("/order/delorderform")
    Call<ResponseBody> delorderForm(@FieldMap Map<String, String> data);

    /**
     * 更新订单状态
     **/
    @FormUrlEncoded
    @POST("/order/upstatus")
    Call<ResponseBody> upStatus(@FieldMap Map<String, String> data);

    /**
     * 订单支付
     **/
    @FormUrlEncoded
    @POST("/order/payment")
    Call<ResponseBody> paymentOrder(@FieldMap Map<String, String> data);
    /**
     * 读取省列表
     * */
    @FormUrlEncoded
    @POST("/user/getprovince")
    Call<ResponseBody>getProvince(@FieldMap Map<String,String>map);
    /**
     * 读取市列表
     * */
    @FormUrlEncoded
    @POST("/user/getcity")
    Call<ResponseBody>getCity(@FieldMap Map<String,String>map);
    /**
     * 读取县/区列表
     * */
    @FormUrlEncoded
    @POST("/user/getcounty")
    Call<ResponseBody>getCounty(@FieldMap Map<String,String>map);
}
