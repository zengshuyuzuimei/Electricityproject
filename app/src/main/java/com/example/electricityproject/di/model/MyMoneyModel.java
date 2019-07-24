package com.example.electricityproject.di.model;

import android.annotation.SuppressLint;

import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;
import com.example.electricityproject.di.contract.MyMoneyContract;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 11:13:25
 *@Description:
 * */
public class MyMoneyModel implements MyMoneyContract.MyMoneyModel {

    @Override
    public void request(int userId, String sessionId, final MyMoneyCallBack myMoneyCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("page",1);
        hashMap.put("count",10);
        apiService.sendMyMoney(userId,sessionId,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        myMoneyCallBack.getData(string);
                    }
                });
    }
}
