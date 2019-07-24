package com.example.electricityproject.di.contract;

import okhttp3.FormBody;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 20:12:01
 *@Description:
 * */
public interface DetailsContract {
    public interface DetailsView{
        public void show(String string);
        public void showAddCar(String string);
    }
    public interface DetailsPresenter{
        public void attachView();
        public void detachView();
    }
    public interface DetailsModel{
        public void request(int i, int id, String sessionId, DetailsCallBack detailsCallBack);
        public interface DetailsCallBack{
            public void getData(String string);
        }
        public void requestCar(int userId, String sessionId, FormBody formBody, AddCarCallBack addCarCallBack);
        public interface AddCarCallBack{
            public void getData(String string);
        }
    }
}
