package com.example.electricityproject.di.model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;
import com.example.electricityproject.di.contract.Home2Contract;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 20:12:48
 *@Description:
 * */
public class Home2Model implements Home2Contract.Home2Model {

    private ApiService apiService;

    @Override
    public void request(final Home2CallBack home2CallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        apiService.sendXBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            home2CallBack.getData(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        home2CallBack.getData(message);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void gridRequest(final gridCallBack gridCallBack) {
        apiService.sendRecycler()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                String string = responseBody.string();
                gridCallBack.getData(string);
            }
        });
    }

    @Override
    public void stagRequest(StagCallBack stagCallBack) {
    }

    @Override
    public void lineRequest(LineCallBack lineCallBack) {

    }
}
