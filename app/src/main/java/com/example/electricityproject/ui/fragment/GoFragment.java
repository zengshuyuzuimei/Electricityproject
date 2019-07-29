package com.example.electricityproject.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.StatusBean;
import com.example.electricityproject.di.contract.GoContract;
import com.example.electricityproject.di.presenter.GoPresenter;
import com.example.electricityproject.ui.adapter.GoAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:14:55
 *@Description:待评价
 * */
public class GoFragment extends BaseFragment<GoContract.GoView, GoPresenter<GoContract.GoView>> implements GoContract.GoView {
    private RecyclerView go_recycler;
    @Override
    public void show(String string) {
        Gson gson = new Gson();
        StatusBean statusBean = gson.fromJson(string, StatusBean.class);
        ArrayList<StatusBean.OrderListBean> list = (ArrayList<StatusBean.OrderListBean>) statusBean.getOrderList();
        //创建适配器
        GoAdapter goAdapter = new GoAdapter(R.layout.go_view, list);
        go_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        go_recycler.setAdapter(goAdapter);
        goAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayout() {
        return R.layout.go_fragment;
    }

    @Override
    protected void initView(View view) {
        go_recycler = view.findViewById(R.id.go_recycler);
    }

    @Override
    protected void initData() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 1);
        String sessionId = login.getString("sessionId", "");
        presenter.show(userId,sessionId);
    }

    @Override
    protected GoPresenter<GoContract.GoView> createPresenter() {
        return new GoPresenter<>();
    }
}
