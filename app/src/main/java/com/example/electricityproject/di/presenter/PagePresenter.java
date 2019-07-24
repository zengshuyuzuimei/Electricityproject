package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.PageContract;
import com.example.electricityproject.di.model.PageModel;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 15:42:20
 *@Description:
 * */
public class PagePresenter<V extends PageContract.PageView> extends BasePresenter<V>{

    private final PageContract.PageModel model;

    public PagePresenter() {
        model = new PageModel();
    }

    public void showRecycler(int userId, String sessionId, int status) {
        model.request(userId,sessionId,status, new PageContract.PageModel.PageCallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }
}
