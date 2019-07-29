package com.example.electricityproject.data.net;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 16:47:58
 *@Description:
 * */
public interface ApiService {
    //http://172.17.8.100/small/user/v1/register
    //http://172.17.8.100/small/user/v1/login
    //注册接口
    @FormUrlEncoded
    @POST("small/user/v1/register")
    Observable<ResponseBody> sendRegister(@Field("phone")String phone,@Field("pwd")String pwd);
    //登录接口
    @FormUrlEncoded
    @POST("small/user/v1/login")
    Observable<ResponseBody> sendLogin(@Field("phone")String phone,@Field("pwd")String pwd);
    //轮播图接口
    @GET("small/commodity/v1/bannerShow")
    Observable<ResponseBody> sendXBanner();
    //条目接口
    @GET("small/commodity/v1/commodityList")
    Observable<ResponseBody> sendRecycler();
    //商品详情接口
    @GET("small/commodity/v1/findCommodityDetailsById")
    Observable<ResponseBody> sendDetail(@Header("userId")int userId, @Header("sessionId")String sessionId, @Query("commodityId") int commodityId);
    //圈子接口http://172.17.8.100/small/circle/v1/findCircleList?page=1&count=2
    @GET("small/circle/v1/findCircleList")
    Observable<ResponseBody> sendQiu(@Header("userId")int userId, @Header("sessionId")String sessionId,@Query("page") int page,@Query("count") int count);
    //根据关键字查询接口
    @GET("small/commodity/v1/findCommodityByKeyword")
    //
    Observable<ResponseBody> sendKeyword(@Query("keyword")String keyword
    ,@Query("page") int page,@Query("count") int count
    );
    //查询购物车
    @GET("small/order/verify/v1/findShoppingCart")
    Observable<ResponseBody> sendCar(@Header("userId") int userId,@Header("sessionId")String sessionId );
    //根据订单状态查询数据
    @GET("small/order/verify/v1/findOrderListByStatus")
    Observable<ResponseBody> sendStatus(@Header("userId") int userId
            ,@Header("sessionId") String sessionId
            ,@Query("status") int status
            ,@Query("page") int page
            ,@Query("count") int count);
    //查询收货地址
    @GET("small/user/verify/v1/receiveAddressList")
    Observable<ResponseBody> sendAddress(@Header("userId") int userId
            , @Header("sessionId") String sessionId);
    //添加数据到购物车
    @PUT("small/order/verify/v1/syncShoppingCart")
    Observable<ResponseBody> sendAddCar(@Header("userId") int userId
            , @Header("sessionId") String sessionId
            , @Body RequestBody requestBody);
    //提交订单
    @POST("small/order/verify/v1/createOrder")
    Observable<ResponseBody> sendSubmitOrder(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@Body RequestBody requestBody);
    //我的圈子
    @GET("small/circle/verify/v1/findMyCircleById")
    Observable<ResponseBody> sendMyCircle(@Header("userId") int userId
            , @Header("sessionId") String sessionId
            , @QueryMap HashMap<String,Integer> hashMap);
    //删除我的圈子接口
    @DELETE("small/circle/verify/v1/deleteCircle")
    Observable<ResponseBody> sendMyCircleDel(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@Query("circleId") int circleId);
    //查询余额
    @GET("small/user/verify/v1/findUserWallet")
    Observable<ResponseBody> sendMyMoney(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@QueryMap HashMap<String,Integer> hashMap);
    //新增地址
    @FormUrlEncoded
    @POST("small/user/verify/v1/addReceiveAddress")
    Observable<ResponseBody> sendNewAddress(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@FieldMap HashMap<String,String> hashMap);
    //设为默认地址
    @FormUrlEncoded
    @POST("small/user/verify/v1/setDefaultReceiveAddress")
    Observable<ResponseBody> sendDefaultAddress(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@Field("id") int id);
    //创建订单接口
    @POST("small/order/verify/v1/createOrder")
    Observable<ResponseBody> sendOrder(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@Body RequestBody body);
    //支付接口
    @FormUrlEncoded
    @POST("small/order/verify/v1/pay")
    Observable<ResponseBody> sendPlay(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@Field("orderId") String orderId,@Field("payType") int payType);
    //确认收货
    @PUT("small/order/verify/v1/confirmReceipt")
    Observable<ResponseBody> sendHuo(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@Query("orderId") String orderId);
    //足迹接口
    @GET("small/commodity/verify/v1/browseList")
    Observable<ResponseBody> sendFoot(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@QueryMap HashMap<String,Integer> hashMap);
    //修改名称
    @PUT("small/user/verify/v1/modifyUserNick")
    Observable<ResponseBody> sendName(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@Query("nickName") String nickName);
    //根据用户ID查询我的资料
    @GET("small/user/verify/v1/getUserById")
    Observable<ResponseBody> sendMyData(@Header("userId") int userId
            , @Header("sessionId") String sessionId);
    //删除订单
    @DELETE("small/order/verify/v1/deleteOrder")
    Observable<ResponseBody> sendDelete(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@Query("orderId") String orderId);
    //上传头像
    @Multipart
    @POST("small/user/verify/v1/modifyHeadPic")
    Observable<ResponseBody> sendImg(@Header("userId") int userId
            , @Header("sessionId") String sessionId,@Part List<MultipartBody.Part> part);
}
