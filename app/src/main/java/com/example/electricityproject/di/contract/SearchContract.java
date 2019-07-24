package com.example.electricityproject.di.contract;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 14:59:08
 *@Description:
 * */
public interface SearchContract {
    public interface SearchView{
        public void show(String string);
    }
    public interface SearchPresenter{
        public void attachView();
        public void detachView();
    }
    public interface SearchModel{
        public void request(String name, SearchCallBack searchCallBack);
        public interface SearchCallBack{
            public void getData(String string);
        }
    }
}
