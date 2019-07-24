package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:27:41
 *@Description:
 * */
public interface PlayContract {
    public interface PlayView{
        public void show(String string);
        public void showDel(String string);
    }
    public interface PlayPresenter{
        public void attachView();
        public void detachView();
    }
    public interface PlayModel{
        public void request(int userId, String sessionId, PlayCallBack playCallBack);
        public interface PlayCallBack{
            public void getData(String string);
        }
        public void requestDel(int userId, String sessionId, String orderId, DelCallBack delCallBack);
        public interface DelCallBack{
            public void getData(String string);
        }
    }
}
