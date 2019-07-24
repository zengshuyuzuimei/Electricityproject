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
import com.example.electricityproject.di.contract.PlayContract;
import com.example.electricityproject.di.presenter.PlayPresenter;
import com.example.electricityproject.ui.activity.SubmitOrderActivity;
import com.example.electricityproject.ui.adapter.AllAdapter;
import com.example.electricityproject.ui.adapter.PlayAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:16:29
 *@Description:
 * */
public class PlayFragment extends BaseFragment<PlayContract.PlayView, PlayPresenter<PlayContract.PlayView>> implements PlayContract.PlayView {
    private RecyclerView play_recycler;
    @Override
    public void show(String string) {
        Gson gson = new Gson();
        StatusBean statusBean = gson.fromJson(string, StatusBean.class);
        ArrayList<StatusBean.OrderListBean> beans = (ArrayList<StatusBean.OrderListBean>) statusBean.getOrderList();
        //创建适配器
        PlayAdapter playAdapter = new PlayAdapter(R.layout.play_view, beans);
        play_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        play_recycler.setAdapter(playAdapter);
        playAdapter.setOnPlayChange(new AllAdapter.onPlayChange() {
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
        playAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDel(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayout() {
        return R.layout.play_fragment;
    }

    @Override
    protected void initView(View view) {
        play_recycler = view.findViewById(R.id.play_recycler);
    }

    @Override
    protected void initData() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 1);
        String sessionId = login.getString("sessionId", "");
        presenter.show(userId,sessionId);
    }

    @Override
    protected PlayPresenter<PlayContract.PlayView> createPresenter() {
        return new PlayPresenter<>();
    }
}
