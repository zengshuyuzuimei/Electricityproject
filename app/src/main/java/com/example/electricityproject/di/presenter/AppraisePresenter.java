package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.AppraiseContract;
import com.example.electricityproject.di.model.AppraiseModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 20:58:25
 *@Description:
 * */
public class AppraisePresenter<V extends AppraiseContract.AppraiseView> extends BasePresenter<AppraiseContract.AppraiseView>{

    private final AppraiseContract.AppraiseModel model;

    public AppraisePresenter() {
        model = new AppraiseModel();
    }

    public void show(int userId, String sessionId) {
        model.request(userId,sessionId, new AppraiseContract.AppraiseModel.AppraiseCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
