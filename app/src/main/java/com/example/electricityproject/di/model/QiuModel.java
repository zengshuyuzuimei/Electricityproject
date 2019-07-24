package com.example.electricityproject.di.model;

import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;
import com.example.electricityproject.di.contract.QiuContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 16:57:04
 *@Description:
 * */
public class QiuModel implements QiuContract.QiuModel {
    @Override
    public void request(int userId, String sessionId, int page, final QiuCallBack qiuCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.sendQiu(userId,sessionId,page,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        qiuCallBack.getData(string);
                    }
                });
    }
}
