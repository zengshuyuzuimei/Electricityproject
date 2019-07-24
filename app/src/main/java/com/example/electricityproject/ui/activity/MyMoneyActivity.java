package com.example.electricityproject.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.MyMoneyBean;
import com.example.electricityproject.di.contract.MyMoneyContract;
import com.example.electricityproject.di.presenter.MyMoneyPresenter;
import com.example.electricityproject.ui.adapter.MyMoneyAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMoneyActivity extends BaseActivity<MyMoneyContract.MyMoneyView, MyMoneyPresenter<MyMoneyContract.MyMoneyView>> implements MyMoneyContract.MyMoneyView {

    @BindView(R.id.my_money_yue)
    TextView myMoneyYue;
    @BindView(R.id.my_money_recycler)
    RecyclerView my_MoneyRecycler;

    @Override
    public void show(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        MyMoneyBean myMoneyBean = gson.fromJson(string, MyMoneyBean.class);
        MyMoneyBean.ResultBean result = myMoneyBean.getResult();
        myMoneyYue.setText(result.getBalance() + "");
        //创建适配器
        List<MyMoneyBean.ResultBean.DetailListBean> detailList = result.getDetailList();
        MyMoneyAdapter myMoneyAdapter = new MyMoneyAdapter(R.layout.my_money_view, detailList);
        my_MoneyRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        my_MoneyRecycler.setAdapter(myMoneyAdapter);
    }

    @Override
    protected void initData() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        presenter.show(userId, sessionId);
    }

    @Override
    protected MyMoneyPresenter<MyMoneyContract.MyMoneyView> createPresenter() {
        return new MyMoneyPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_money;
    }

}
