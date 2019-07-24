package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 11:17:12
 *@Description:
 * */
public interface CarContract {
    public interface CarView{
        public void show(String string);
    }
    public interface CarPresenter{
        public void attachView();
        public void detachView();
    }
    public interface CarModel{
        public void request(int userId, String sessionId, CarCallBack carCallBack);
        public interface CarCallBack{
            public void getData(String string);
        }

    }
}
