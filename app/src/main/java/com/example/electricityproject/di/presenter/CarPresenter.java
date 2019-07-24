package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.CarContract;
import com.example.electricityproject.di.model.CarModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 11:18:29
 *@Description:
 * */
public class CarPresenter<V extends CarContract.CarView>extends BasePresenter<V>{

    private final CarContract.CarModel model;

    public CarPresenter() {
        model = new CarModel();
    }

    public void showRecycler(int userId, String sessionId) {
        model.request(userId,sessionId,new CarContract.CarModel.CarCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
