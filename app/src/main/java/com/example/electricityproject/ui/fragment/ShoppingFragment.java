package com.example.electricityproject.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.StatusBean;
import com.example.electricityproject.di.contract.ShoppingContract;
import com.example.electricityproject.di.presenter.ShoppingPresenter;
import com.example.electricityproject.ui.adapter.ShoppingAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:14:55
 *@Description:
 * */
public class ShoppingFragment extends BaseFragment<ShoppingContract.ShoppingView, ShoppingPresenter<ShoppingContract.ShoppingView>> implements ShoppingContract.ShoppingView {
    private RecyclerView shopping_recycler;
    @Override
    public void show(String string) {
        Gson gson = new Gson();
        StatusBean statusBean = gson.fromJson(string, StatusBean.class);
        final ArrayList<StatusBean.OrderListBean> listBeans = (ArrayList<StatusBean.OrderListBean>) statusBean.getOrderList();
        //创建适配器
        ShoppingAdapter shoppingAdapter = new ShoppingAdapter(R.layout.shopping_view, listBeans);
        shopping_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        shopping_recycler.setAdapter(shoppingAdapter);
        shoppingAdapter.setOnPlayChange(new ShoppingAdapter.onPlayChange() {
            @Override
            public void getData() {
                SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                int userId = login.getInt("userId", 1);
                String sessionId = login.getString("sessionId", "");
                for (int i = 0; i < listBeans.size(); i++) {
                    String orderId = listBeans.get(i).getOrderId();
                    presenter.showHuo(userId,sessionId,orderId);
                }

            }
        });
    }

    @Override
    public void showHuo(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayout() {
        return R.layout.shopping_fragment;
    }

    @Override
    protected void initView(View view) {
        shopping_recycler = view.findViewById(R.id.shopping_recycler);
    }

    @Override
    protected void initData() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 1);
        String sessionId = login.getString("sessionId", "");
        presenter.show(userId,sessionId);
    }

    @Override
    protected ShoppingPresenter<ShoppingContract.ShoppingView> createPresenter() {
        return new ShoppingPresenter<>();
    }
}
