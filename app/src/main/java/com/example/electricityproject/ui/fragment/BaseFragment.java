package com.example.electricityproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.electricityproject.di.contract.Home2Contract;
import com.example.electricityproject.di.presenter.BasePresenter;
import com.example.electricityproject.di.presenter.Home2Presenter;
import com.example.electricityproject.ui.activity.BaseActivity;

import butterknife.ButterKnife;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 20:11:26
 *@Description:fragment抽基类
 * */
public abstract class BaseFragment<V,P extends BasePresenter<V>> extends Fragment {

    public P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        initView(view);
        ButterKnife.bind(this,view);
        return view;
    }
    protected abstract int getLayout();
    protected abstract void initView(View view);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V) this);
        initData();
    }

    protected abstract void initData();

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }
}
