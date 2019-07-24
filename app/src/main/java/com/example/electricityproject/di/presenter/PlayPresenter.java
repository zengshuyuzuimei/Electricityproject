package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.PlayContract;
import com.example.electricityproject.di.model.PlayModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:52:16
 *@Description:
 * */
public class PlayPresenter<V extends PlayContract.PlayView> extends BasePresenter<V>{

    private final PlayContract.PlayModel model;

    public PlayPresenter() {
        model = new PlayModel();
    }

    public void show(int userId, String sessionId) {
        model.request(userId,sessionId, new PlayContract.PlayModel.PlayCallBack() {
            @Override
            public void getData(String string) {
                    getView().show(string);
            }
        });
    }

    public void showDel(int userId, String sessionId, String orderId) {
        model.requestDel(userId,sessionId,orderId, new PlayContract.PlayModel.DelCallBack() {
            @Override
            public void getData(String string) {
                getView().showDel(string);
            }
        });
    }
}
