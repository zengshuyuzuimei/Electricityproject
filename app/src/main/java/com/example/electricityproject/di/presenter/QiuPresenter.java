package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.QiuContract;
import com.example.electricityproject.di.model.QiuModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 16:57:33
 *@Description:
 * */
public class QiuPresenter<V extends QiuContract.QiuView> extends BasePresenter<V>{

    private final QiuContract.QiuModel model;

    public QiuPresenter() {
        model = new QiuModel();
    }

    public void showQiu(int userId, String sessionId, int page) {
        model.request(userId,sessionId,page,new QiuContract.QiuModel.QiuCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
