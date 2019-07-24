package com.example.electricityproject.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.FootBean;
import com.example.electricityproject.di.contract.MyFootContract;
import com.example.electricityproject.di.presenter.MyFootPresenter;
import com.example.electricityproject.ui.adapter.FootAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFootActivity extends BaseActivity<MyFootContract.MyFootView, MyFootPresenter<MyFootContract.MyFootView>> implements MyFootContract.MyFootView {

    @BindView(R.id.foot_recycler)
    RecyclerView footRecycler;

    @Override
    public void show(String string) {
        Gson gson = new Gson();
        FootBean footBean = gson.fromJson(string, FootBean.class);
        ArrayList<FootBean.ResultBean> result = (ArrayList<FootBean.ResultBean>) footBean.getResult();
        //创建适配器
        FootAdapter footAdapter = new FootAdapter(R.layout.my_foot_view, result);
        footRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        footRecycler.setAdapter(footAdapter);
    }

    @Override
    protected void initData() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        presenter.show(userId, sessionId);
    }

    @Override
    protected MyFootPresenter<MyFootContract.MyFootView> createPresenter() {
        return new MyFootPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_foot;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
