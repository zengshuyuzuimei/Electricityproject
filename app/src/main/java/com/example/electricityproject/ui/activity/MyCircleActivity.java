package com.example.electricityproject.ui.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.MyCircleBean;
import com.example.electricityproject.di.contract.MyCircleContract;
import com.example.electricityproject.di.presenter.MyCirclePresenter;
import com.example.electricityproject.ui.adapter.MyCircleAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCircleActivity extends BaseActivity<MyCircleContract.MyCircleView, MyCirclePresenter<MyCircleContract.MyCircleView>> implements MyCircleContract.MyCircleView {


    @BindView(R.id.my_circle_del)
    ImageView myCircleDel;
    @BindView(R.id.my_circle_recycler)
    RecyclerView myCircleRecycler;
    private MyCircleAdapter myCircleAdapter;

    @Override
    protected void initData() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        presenter.showRecycler(userId,sessionId);
    }

    @Override
    protected MyCirclePresenter<MyCircleContract.MyCircleView> createPresenter() {
        return new MyCirclePresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_circle;
    }

    @Override
    public void show(String string) {
        //解析数据
        Gson gson = new Gson();
        MyCircleBean myCircleBean = gson.fromJson(string, MyCircleBean.class);
        ArrayList<MyCircleBean.ResultBean> result = (ArrayList<MyCircleBean.ResultBean>) myCircleBean.getResult();
        //创建适配器
        myCircleAdapter = new MyCircleAdapter(R.layout.my_circle_view, result);
        myCircleRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        myCircleRecycler.setAdapter(myCircleAdapter);
    }

    @Override
    public void showDel(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        myCircleAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.my_circle_del)
    public void onViewClicked() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        presenter.showDel(userId,sessionId);
    }
}
