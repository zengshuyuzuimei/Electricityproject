package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 09:57:22
 *@Description:
 * */
public interface MyCircleContract {
    public interface MyCircleView{
        public void show(String string);
        public void showDel(String string);
    }
    public interface MyCirclePresenter{
        public void attachView();
        public void detachView();
    }
    public interface MyCircleModel{
        public void request(int userId, String sessionId, MyCircleCallBack myCircleCallBack);
        public interface MyCircleCallBack{
            public void getData(String string);
        }
        public void requestDel(int userId, String sessionId, DelMyCircleCallBack delMyCircleCallBack);
        public interface DelMyCircleCallBack{
            public void getData(String string);
        }
    }
}
