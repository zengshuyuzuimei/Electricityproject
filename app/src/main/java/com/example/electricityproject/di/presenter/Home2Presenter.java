package com.example.electricityproject.di.presenter;

import com.example.electricityproject.di.contract.Home2Contract;
import com.example.electricityproject.di.model.Home2Model;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 20:13:13
 *@Description:
 * */
public class Home2Presenter<V extends Home2Contract.Home2View> extends BasePresenter<V>{

    private final Home2Contract.Home2Model model;

    public Home2Presenter() {
        model = new Home2Model();
    }

    public void showBanner() {
        model.request(new Home2Contract.Home2Model.Home2CallBack() {
            @Override
            public void getData(String string) {
                getView().show(string);
            }
        });
    }

    public void showGrid() {
        model.gridRequest(new Home2Contract.Home2Model.gridCallBack() {
            @Override
            public void getData(String string) {
                getView().showGrid(string);
            }
        });
    }

    public void showLine() {
        model.gridRequest(new Home2Contract.Home2Model.gridCallBack() {
            @Override
            public void getData(String string) {
                getView().showLine(string);
            }
        });
    }

    public void showStag() {
        model.gridRequest(new Home2Contract.Home2Model.gridCallBack() {
            @Override
            public void getData(String string) {
                getView().showStag(string);
            }
        });

    }
}
