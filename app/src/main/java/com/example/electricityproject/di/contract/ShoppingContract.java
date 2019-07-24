package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:27:41
 *@Description:
 * */
public interface ShoppingContract {
    public interface ShoppingView{
        public void show(String string);
        public void showHuo(String string);
    }
    public interface ShoppingPresenter{
        public void attachView();
        public void detachView();
    }
    public interface ShoppingModel{
        public void request(int userId, String sessionId, ShoppingCallBack shoppingCallBack);
        public interface ShoppingCallBack{
            public void getData(String string);
        }
        public void requestHuo(int userId, String sessionId, String orderId, HuoCallBack huoCallBack);
        public interface HuoCallBack{
            public void getData(String string);
        }
    }
}
