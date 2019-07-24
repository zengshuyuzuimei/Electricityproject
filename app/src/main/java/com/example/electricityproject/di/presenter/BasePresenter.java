package com.example.electricityproject.di.presenter;

import java.lang.ref.WeakReference;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 15:57:21
 *@Description:
 * */
public class BasePresenter<V> {

    private WeakReference<V> weakReference;

    public void attachView(V v){
        weakReference = new WeakReference<>(v);
    }
    public V getView(){
        return weakReference.get();
    }
    public void detachView(){
        weakReference.clear();
    }
}
