package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/19
 *@Time: 21:19:51
 *@Description:
 * */
public interface SubmitOrderContract {
    public interface SubmitOrderView{
        public void show(String string);
        public void showStatus(String string);
    }
    public interface SubmitOrderPresenter{
        public void attachView();
        public void detachView();
    }
    public interface SubmitOrderModel{
        public void request(int userId, String sessionId, String i, int id, SubmitOrderCallBack submitOrderCallBack);
        public interface SubmitOrderCallBack{
            public void getData(String string);
        }
        public void requestStatus(int userId, String sessionId, StatusCallBack statusCallBack);
        public interface StatusCallBack{
            public void getData(String string);
        }
    }
}
