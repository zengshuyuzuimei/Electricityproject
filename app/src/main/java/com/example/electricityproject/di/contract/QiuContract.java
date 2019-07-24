package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 16:56:09
 *@Description:
 * */
public interface QiuContract {
    public interface QiuView{
        public void show(String string);
    }
    public interface QiuPresenter{
        public void attachView();
        public void detachView();
    }
    public interface QiuModel{
        public void request(int userId, String sessionId, int page, QiuCallBack qiuCallBack);
        public interface QiuCallBack{
            public void getData(String string);
        }
    }
}
