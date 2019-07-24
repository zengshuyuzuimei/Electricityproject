package com.example.electricityproject.di.model;

import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;
import com.example.electricityproject.di.contract.ShoppingContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 20:11:34
 *@Description:
 * */
public class ShoppingModel implements ShoppingContract.ShoppingModel {

    @Override
    public void request(int userId, String sessionId, final ShoppingCallBack shoppingCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.sendStatus(userId,sessionId,2,1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String string = body.string();
                        shoppingCallBack.getData(string);
                    }
                });
    }

    @Override
    public void requestHuo(int userId, String sessionId, String orderId, final HuoCallBack huoCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.sendHuo(userId,sessionId,orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String string = body.string();
                        huoCallBack.getData(string);
                    }
                });
    }
}
