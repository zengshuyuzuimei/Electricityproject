package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/19
 *@Time: 10:58:30
 *@Description:
 * */
public interface SureContract {
    public interface SureView{
        public void show(String string);
        public void showView(String string);
        public void shoeOrder(String string);
    }
    public interface SurePresenter{
        public void attachView();
        public void detachView();
    }
    public interface SureModel{
        public void request(int userId, String sessionId, SureCallBack sureCallBack);
        public interface SureCallBack{
            public void getData(String string);
        }
        public void requestView(int userId, String sessionId, ViewCallBack viewCallBack);
        public interface ViewCallBack{
            public void getData(String string);
        }
        public void requestOrder(int userId, String id, String sessionId, int totalPrice, int addressId, OrderCallBack orderCallBack);
        public interface OrderCallBack{
            public void getData(String string);
        }
    }
}
