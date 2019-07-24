package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.MyContract;
import com.example.electricityproject.di.model.MyModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 21:00:22
 *@Description:
 * */
public class MyPresenter<V extends MyContract.MyView> extends BasePresenter<V> {

    private final MyContract.MyModel myModel;

    public MyPresenter() {
        myModel = new MyModel();
    }

    public void show(int userId, String sessionId) {
        myModel.request(userId,sessionId, new MyContract.MyModel.MyCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
