package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.SureContract;
import com.example.electricityproject.di.model.SureModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/19
 *@Time: 11:00:13
 *@Description:
 * */
public class SurePresenter<V extends SureContract.SureView> extends BasePresenter<SureContract.SureView> {

    private final SureContract.SureModel model;

    public SurePresenter() {
        model = new SureModel();
    }

    public void showAddress(int userId, String sessionId) {
        model.request(userId,sessionId,new SureContract.SureModel.SureCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }

    public void showRecycler(int userId, String sessionId) {
        model.requestView(userId,sessionId, new SureContract.SureModel.ViewCallBack() {
            @Override
            public void getData(String string) {
                getView().showView(string);
            }
        });
    }

    public void showOrder(int userId, String id, String sessionId, int totalPrice, int addressId) {
        model.requestOrder(userId,sessionId,id,totalPrice,addressId, new SureContract.SureModel.OrderCallBack() {
            @Override
            public void getData(String string) {
                getView().shoeOrder(string);
            }
        });
    }
}
