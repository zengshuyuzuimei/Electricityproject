package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.AddressContract;
import com.example.electricityproject.di.model.AddressModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/19
 *@Time: 11:53:27
 *@Description:
 * */
public class AddressPresenter<V extends AddressContract.AddressView> extends BasePresenter<V>{

    private final AddressContract.AddressModel model;

    public AddressPresenter() {
        model = new AddressModel();
    }

    public void showAddress(int userId, final String sessionId) {
        model.request(userId,sessionId, new AddressContract.AddressModel.AddressCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }

    public void showDefault(int userId, String sessionId, int id) {
        model.defaultRequest(userId,sessionId,id, new AddressContract.AddressModel.DefaultCallBack() {
            @Override
            public void getData(String string) {
                getView().showDefault(string);
            }
        });
    }
}
