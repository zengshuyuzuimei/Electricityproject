package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/19
 *@Time: 11:52:26
 *@Description:
 * */
public interface AddressContract {
    public interface AddressView{
        public void show(String string);
        public void showDefault(String string);
    }
    public interface AddressPresenter{
        public void attachView();
        public void detachView();
    }
    public interface AddressModel{
        public void request(int login, String sessionId, AddressCallBack addressCallBack);
        public interface AddressCallBack{
            public void getData(String string);
        }
        public void defaultRequest(int userId, String sessionId, int id, DefaultCallBack defaultCallBack);
        public interface DefaultCallBack{
            public void getData(String string);
        }
    }
}
