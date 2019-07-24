package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 21:24:06
 *@Description:
 * */
public interface MyFootContract {
    public interface MyFootView{
        public void show(String string);
    }
    public interface MyFootPresenter{
        public void attachView();
        public void detachView();
    }
    public interface MyFootModel{
        public void request(int userId, String sessionId, MyFootCallBack myFootCallBack);
        public interface  MyFootCallBack{
            public void getData(String string);
        }

    }
}
