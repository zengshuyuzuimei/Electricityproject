package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.HomeContract;
import com.example.electricityproject.di.model.HomeModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 18:51:18
 *@Description:
 * */
public class HomePresenter<V extends HomeContract.HomeView> extends BasePresenter<V>{

    private final HomeContract.HomeModel model;

    public HomePresenter() {
        model = new HomeModel();
    }

    public void showXBanner() {
        model.request(new HomeContract.HomeModel.HomeCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
