package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.AllContract;
import com.example.electricityproject.di.model.AllModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:28:39
 *@Description:
 * */
public class AllPresenter<V extends AllContract.AllView> extends BasePresenter<V>{

    private final AllContract.AllModel model;

    public AllPresenter() {
        model = new AllModel();
    }

    public void show(int userId, String sessionId) {
        model.request(userId,sessionId, new AllContract.AllModel.AllCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }

    public void showDel(int userId, String sessionId, String orderId) {
        model.requestDel(userId,sessionId,orderId, new AllContract.AllModel.DelCallBack() {
            @Override
            public void getData(String string) {
                getView().showDel(string);
            }
        });
    }
}
