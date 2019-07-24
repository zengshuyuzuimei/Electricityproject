package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.GoContract;
import com.example.electricityproject.di.model.GoModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 20:38:38
 *@Description:
 * */
public class GoPresenter<V extends GoContract.GoView> extends BasePresenter<V>{

    private final GoContract.GoModel model;

    public GoPresenter() {
        model = new GoModel();
    }

    public void show(int userId, String sessionId) {
        model.request(userId,sessionId, new GoContract.GoModel.GoCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
