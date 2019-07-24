package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.LoginContract;
import com.example.electricityproject.di.model.LoginModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 15:58:41
 *@Description:
 * */
public class LoginPresenter<V extends LoginContract.LoginView> extends BasePresenter<V>{

    private final LoginContract.LoginModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    public void showLogin(String name, String pwds) {
        model.request(name,pwds, new LoginContract.LoginModel.LoginCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
