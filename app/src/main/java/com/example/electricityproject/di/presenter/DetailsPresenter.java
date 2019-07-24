package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.DetailsContract;
import com.example.electricityproject.di.model.DetailsModel;

import okhttp3.FormBody;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 15:01:25
 *@Description:
 * */
public class DetailsPresenter<V extends DetailsContract.DetailsView> extends BasePresenter<V>{

    private final DetailsContract.DetailsModel model;

    public DetailsPresenter() {
        model = new DetailsModel();
    }

    public void Show(int i, int id, String sessionId) {
        model.request(id,i,sessionId, new DetailsContract.DetailsModel.DetailsCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }

    public void showCar(int userId, String sessionId, FormBody formBody) {
        model.requestCar(userId,sessionId,formBody, new DetailsContract.DetailsModel.AddCarCallBack() {
            @Override
            public void getData(String string) {
                getView().showAddCar(string);
            }
        });
    }
}
