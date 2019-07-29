package com.example.electricityproject.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.StatusBean;
import com.example.electricityproject.di.contract.AppraiseContract;
import com.example.electricityproject.di.presenter.AppraisePresenter;
import com.example.electricityproject.ui.adapter.AppraiseAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 19:14:55
 *@Description:评价页面
 * */
public class AppraiseFragment extends BaseFragment<AppraiseContract.AppraiseView, AppraisePresenter<AppraiseContract.AppraiseView>> implements AppraiseContract.AppraiseView {
    private RecyclerView appraise_recycler;
    @Override
    public void show(String string) {
        Gson gson = new Gson();
        StatusBean statusBean = gson.fromJson(string, StatusBean.class);
        ArrayList<StatusBean.OrderListBean> list = (ArrayList<StatusBean.OrderListBean>) statusBean.getOrderList();
        //创建适配器
        AppraiseAdapter appraiseAdapter = new AppraiseAdapter(R.layout.appraise_view, list);
        appraise_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        appraise_recycler.setAdapter(appraiseAdapter);
        appraiseAdapter.setOnImgClick(new AppraiseAdapter.onImgClick() {
            @Override
            public void getData() {
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.appraise_pupop_view, null);
                PopupWindow mpopupWindow = new PopupWindow(inflate, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
                mpopupWindow.setContentView(inflate);
                View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.appraise_fragment, null);
                mpopupWindow.showAsDropDown(inflate1);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.appraise_fragment;
    }

    @Override
    protected void initView(View view) {
        appraise_recycler = view.findViewById(R.id.appraise_recycler);
    }

    @Override
    protected void initData() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 1);
        String sessionId = login.getString("sessionId", "");
        presenter.show(userId,sessionId);
    }

    @Override
    protected AppraisePresenter<AppraiseContract.AppraiseView> createPresenter() {
        return new AppraisePresenter<>();
    }
}
