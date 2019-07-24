package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 11:11:31
 *@Description:
 * */
public interface MyMoneyContract {
    public interface MyMoneyView{
        public void show(String string);
    }
    public interface MyMoneyPresenter{
        public void attachView();
        public void detachView();
    }
    public interface MyMoneyModel{

        public void request(int userId, String sessionId,MyMoneyCallBack myMoneyCallBack);

        public interface MyMoneyCallBack{
            public void getData(String string);
        }
    }
}
