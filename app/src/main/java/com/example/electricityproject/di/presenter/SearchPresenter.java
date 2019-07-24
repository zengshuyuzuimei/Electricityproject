package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.SearchContract;
import com.example.electricityproject.di.model.SearchModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 15:01:25
 *@Description:
 * */
public class SearchPresenter<V extends SearchContract.SearchView> extends BasePresenter<V>{

    private final SearchContract.SearchModel model;

    public SearchPresenter() {
        model = new SearchModel();
    }

    public void showRecycler(final String name) {
        model.request(name,new SearchContract.SearchModel.SearchCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
