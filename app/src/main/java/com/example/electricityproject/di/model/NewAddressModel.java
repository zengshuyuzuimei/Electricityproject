package com.example.electricityproject.di.model;

import android.annotation.SuppressLint;

import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;
import com.example.electricityproject.di.contract.NewAddressContract;
import com.google.gson.Gson;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 11:51:52
 *@Description:
 * */
public class NewAddressModel implements NewAddressContract.NewAddressModel {
    @SuppressLint("CheckResult")
    @Override
    public void request(int userId, String sessionId, String new_address, String new_name, String new_phone, String new_code, String new_detail, final NewAddressCallBack newAddressCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("realName",new_name);
        hashMap.put("phone",new_phone);
        hashMap.put("address",new_address);
        hashMap.put("zipCode",new_code);
        apiService.sendNewAddress(userId,sessionId,hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String string = body.string();
                        newAddressCallBack.getData(string);
                    }
                });
    }
}
