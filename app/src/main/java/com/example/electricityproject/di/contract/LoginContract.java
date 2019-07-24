package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 15:55:42
 *@Description:
 * */
public interface LoginContract {
    public interface LoginView{
        public void show(String string);
    }
    public interface LoginPresenter{
        public void attachView();
        public void detachView();
    }
    public interface LoginModel{
        public void request(String name, String pwds, LoginCallBack loginCallBack);
        public interface LoginCallBack{
            public void getData(String string);
        }
    }
}
