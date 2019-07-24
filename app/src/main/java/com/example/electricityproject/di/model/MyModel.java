package com.example.electricityproject.di.model;

import android.annotation.SuppressLint;

import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;
import com.example.electricityproject.di.contract.MyContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 21:00:06
 *@Description:
 * */
public class MyModel implements MyContract.MyModel {
    @Override
    public void request(int userId, String sessionId, final MyCallBack myCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.sendMyData(userId,sessionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String string = body.string();
                        myCallBack.getData(string);
                    }
                });
    }
}
