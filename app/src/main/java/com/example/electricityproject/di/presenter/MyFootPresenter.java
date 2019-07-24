package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.MyFootContract;
import com.example.electricityproject.di.model.MyFootModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 21:25:28
 *@Description:
 * */
public class MyFootPresenter<V extends MyFootContract.MyFootView> extends BasePresenter<V>{

    private final MyFootContract.MyFootModel myFootModel;

    public MyFootPresenter() {
        myFootModel = new MyFootModel();
    }

    public void show(int userId, String sessionId) {
        myFootModel.request(userId,sessionId, new MyFootContract.MyFootModel.MyFootCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
