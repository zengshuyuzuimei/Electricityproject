package com.example.electricityproject.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.electricityproject.di.presenter.BasePresenter;

import butterknife.ButterKnife;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 15:59:32
 *@Description:
 * */
public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {

    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        presenter = createPresenter();
        initData();
        presenter.attachView((V) this);
    }

    protected abstract void initData();

    protected abstract P createPresenter();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }
}
