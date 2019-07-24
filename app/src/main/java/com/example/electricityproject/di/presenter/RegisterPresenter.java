package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.RegisterContract;
import com.example.electricityproject.di.model.RegisterModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 17:12:05
 *@Description:
 * */
public class RegisterPresenter<V extends RegisterContract.RegisterView> extends BasePresenter<V>{

    private final RegisterContract.RegisterModel model;

    public RegisterPresenter() {
        model = new RegisterModel();
    }

    public void showRegister(String name, String pwds) {
        model.request(name, pwds, new RegisterContract.RegisterModel.RegisterCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
