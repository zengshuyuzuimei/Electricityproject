package com.example.electricityproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.StatusBean;
import com.example.electricityproject.di.contract.AllContract;
import com.example.electricityproject.di.presenter.AllPresenter;
import com.example.electricityproject.ui.activity.SubmitOrderActivity;
import com.example.electricityproject.ui.adapter.AllAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:14:55
 *@Description:全部订单
 * */
public class AllFragment extends BaseFragment<AllContract.AllView, AllPresenter<AllContract.AllView>> implements AllContract.AllView {
    private RecyclerView all_recycler;
    @Override
    protected int getLayout() {
        return R.layout.all_fragment;
    }

    @Override
    protected void initView(View view) {
        all_recycler = view.findViewById(R.id.all_recycler);
    }

    @Override
    protected void initData() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 1);
        String sessionId = login.getString("sessionId", "");
        presenter.show(userId,sessionId);
    }

    @Override
    protected AllPresenter<AllContract.AllView> createPresenter() {
        return new AllPresenter<>();
    }

    @Override
    public void show(String string) {
        Gson gson = new Gson();
        StatusBean statusBean = gson.fromJson(string, StatusBean.class);
        final List<StatusBean.OrderListBean> beans = statusBean.getOrderList();
        //创建适配器
        AllAdapter allAdapter = new AllAdapter(R.layout.status_all_view, beans);
        all_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        all_recycler.setAdapter(allAdapter);
        allAdapter.setOnPlayChange(new AllAdapter.onPlayChange() {
            @Override
            public void getData() {
                startActivity(new Intent(getContext(), SubmitOrderActivity.class));
            }

            @Override
            public void getDel(String orderId) {
                SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                int userId = login.getInt("userId", 1);
                String sessionId = login.getString("sessionId", "");
                presenter.showDel(userId,sessionId,orderId);
            }
        });
        allAdapter.notifyDataSetChanged();

    }

    @Override
    public void showDel(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }
}
