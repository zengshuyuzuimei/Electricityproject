package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:27:41
 *@Description:
 * */
public interface GoContract {
    public interface GoView{
        public void show(String string);
    }
    public interface GoPresenter{
        public void attachView();
        public void detachView();
    }
    public interface GoModel{
        public void request(int userId, String sessionId, GoCallBack goCallBack);
        public interface GoCallBack{
            public void getData(String string);
        }

    }
}
