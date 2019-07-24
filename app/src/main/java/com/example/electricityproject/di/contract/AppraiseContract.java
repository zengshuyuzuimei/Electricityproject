package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:27:41
 *@Description:
 * */
public interface AppraiseContract {
    public interface AppraiseView{
        public void show(String string);
    }
    public interface AppraisePresenter{
        public void attachView();
        public void detachView();
    }
    public interface AppraiseModel{
        public void request(int userId, String sessionId, AppraiseCallBack appraiseCallBack);
        public interface  AppraiseCallBack{
            public void getData(String string);
        }

    }
}
