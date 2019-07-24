package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.NewAddressContract;
import com.example.electricityproject.di.model.NewAddressModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 11:52:10
 *@Description:
 * */
public class NewAddressPresenter<V extends NewAddressContract.NewAddressView> extends BasePresenter<V>{

    private final NewAddressContract.NewAddressModel model;

    public NewAddressPresenter() {
        model = new NewAddressModel();
    }

    public void show(int userId, String sessionId, String new_address, String new_name, String new_phone, String new_code, String new_detail) {
        model.request(userId,sessionId,new_address,new_name,new_phone,new_code,new_detail, new NewAddressContract.NewAddressModel.NewAddressCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
