package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 20:59:16
 *@Description:
 * */
public interface MyContract {
    public interface MyView{
        public void show(String string);
    }
    public interface MyPresenter{
        public void attachView();
        public void detachView();
    }
    public interface MyModel{
        public void request(int userId, String sessionId, MyCallBack myCallBack);
        public interface MyCallBack{
            public void getData(String string);
        }

    }
}
