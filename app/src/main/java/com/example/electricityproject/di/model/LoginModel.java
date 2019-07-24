package com.example.electricityproject.di.model;

import android.annotation.SuppressLint;

import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;
import com.example.electricityproject.di.contract.LoginContract;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 15:56:42
 *@Description:
 * */
public class LoginModel implements LoginContract.LoginModel {
    @Override
    public void request(String name, String pwds, final LoginCallBack loginCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.sendLogin(name,pwds).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        loginCallBack.getData(string);
                    }
                });
    }
}
