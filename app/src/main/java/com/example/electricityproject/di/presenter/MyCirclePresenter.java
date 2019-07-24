package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.MyCircleContract;
import com.example.electricityproject.di.model.MyCircleModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 09:58:21
 *@Description:
 * */
public class MyCirclePresenter<V extends MyCircleContract.MyCircleView> extends BasePresenter<V>{

    private final MyCircleContract.MyCircleModel model;

    public MyCirclePresenter() {
        model = new MyCircleModel();
    }

    public void showRecycler(int userId, String sessionId) {
        model.request(userId,sessionId, new MyCircleContract.MyCircleModel.MyCircleCallBack() {
            @Override
            public void getData(String string) {
            getView().show(string);
            }
        });
    }

    public void showDel(int userId, String sessionId) {
        model.requestDel(userId, sessionId, new MyCircleContract.MyCircleModel.DelMyCircleCallBack() {
            @Override
            public void getData(String string) {
                getView().showDel(string);
            }
        });
    }
}
