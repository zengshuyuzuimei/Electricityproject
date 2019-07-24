package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.MyMoneyContract;
import com.example.electricityproject.di.model.MyMoneyModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 11:13:39
 *@Description:
 * */
public class MyMoneyPresenter<V extends MyMoneyContract.MyMoneyView> extends BasePresenter<V>{

    private final MyMoneyContract.MyMoneyModel model;

    public MyMoneyPresenter() {
        model = new MyMoneyModel();
    }

    public void show(int userId, String sessionId) {
        model.request(userId,sessionId, new MyMoneyContract.MyMoneyModel.MyMoneyCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
