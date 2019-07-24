package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 15:41:12
 *@Description:
 * */
public interface PageContract {
    public interface PageView{
        public void show(String string);
    }
    public interface PagePresenter{
        public void attachView();
        public void detachView();
    }
    public interface PageModel{
        public void request(int userId, String sessionId, int status, PageCallBack pageCallBack);
        public interface PageCallBack{
            public void getData(String string);
        }
    }
}
