package com.example.electricityproject.di.model;

import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;
import com.example.electricityproject.di.contract.GoContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 20:38:26
 *@Description:
 * */
public class GoModel implements GoContract.GoModel {
    @Override
    public void request(int userId, String sessionId, final GoCallBack goCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.sendStatus(userId,sessionId,3,1,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String string = body.string();
                        goCallBack.getData(string);
                    }
                });
    }
}
