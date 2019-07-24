package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 15:55:42
 *@Description:
 * */
public interface HomeContract {
    public interface HomeView{
        public void show(String string);
    }
    public interface HomePresenter{
        public void attachView();
        public void detachView();
    }
    public interface HomeModel{
        public void request(HomeCallBack homeCallBack);
        public interface HomeCallBack{
            public void getData(String string);
        }
    }
}
