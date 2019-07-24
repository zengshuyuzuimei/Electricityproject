package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.SubmitOrderContract;
import com.example.electricityproject.di.model.SubmitOrderModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/19
 *@Time: 21:20:52
 *@Description:
 * */
public class SubmitOrderPresenter<V extends SubmitOrderContract.SubmitOrderView> extends BasePresenter<V>{

    private final SubmitOrderContract.SubmitOrderModel model;

    public SubmitOrderPresenter() {
        model = new SubmitOrderModel();
    }

    public void show(int userId, String sessionId, String orderId, int type) {
        model.request(userId,sessionId,orderId,type, new SubmitOrderContract.SubmitOrderModel.SubmitOrderCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }

    public void showStatus(int userId, String sessionId) {
        model.requestStatus(userId,sessionId, new SubmitOrderContract.SubmitOrderModel.StatusCallBack() {
            @Override
            public void getData(String string) {
                getView().showStatus(string);
            }
        });
    }
}
