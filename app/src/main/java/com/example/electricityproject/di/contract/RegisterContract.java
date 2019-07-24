package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 15:55:42
 *@Description:
 * */
public interface RegisterContract {
    public interface RegisterView{
        public void show(String string);
    }
    public interface RegisterPresenter{
        public void attachView();
        public void detachView();
    }
    public interface RegisterModel{
        public void request(String name, String pwds,RegisterCallBack registerCallBack);
        public interface RegisterCallBack{
            public void getData(String string);
        }
    }
}
