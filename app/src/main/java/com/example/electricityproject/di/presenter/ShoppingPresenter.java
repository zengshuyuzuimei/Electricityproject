package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.ShoppingContract;
import com.example.electricityproject.di.model.ShoppingModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 20:11:56
 *@Description:
 * */
public class ShoppingPresenter<V extends ShoppingContract.ShoppingView> extends BasePresenter<V>{

    private final ShoppingModel model;

    public ShoppingPresenter() {
        model = new ShoppingModel();
    }

    public void show(int userId, String sessionId) {
        model.request(userId,sessionId, new ShoppingContract.ShoppingModel.ShoppingCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }

    public void showHuo(int userId, String sessionId, String orderId) {
        model.requestHuo(userId,sessionId,orderId, new ShoppingContract.ShoppingModel.HuoCallBack() {
            @Override
            public void getData(String string) {
                getView().showHuo(string);
            }
        });
    }
}
