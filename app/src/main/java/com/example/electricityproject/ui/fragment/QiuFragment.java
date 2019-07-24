package com.example.electricityproject.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.QiuBean;
import com.example.electricityproject.di.contract.QiuContract;
import com.example.electricityproject.di.presenter.QiuPresenter;
import com.example.electricityproject.ui.adapter.QiuAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.FlyRefreshHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 19:56:29
 *@Description:
 * */
public class QiuFragment extends BaseFragment<QiuContract.QiuView, QiuPresenter<QiuContract.QiuView>> implements QiuContract.QiuView {
    private RecyclerView qiu_recycler;
    private SmartRefreshLayout qiu_smart;
    private int page = 1;
    @Override
    public void show(String string) {
        Gson gson = new Gson();
        QiuBean qiuBean = gson.fromJson(string, QiuBean.class);
        ArrayList<QiuBean.ResultBean> result = (ArrayList<QiuBean.ResultBean>) qiuBean.getResult();
        //创建适配器
        QiuAdapter qiuAdapter = new QiuAdapter(R.layout.qiu_view, result);
        qiu_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        qiu_recycler.setAdapter(qiuAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.qiu_fragment;
    }

    @Override
    protected void initView(View view) {
        qiu_recycler = view.findViewById(R.id.qiu_recycler);
        qiu_smart = view.findViewById(R.id.qiu_smart);
    }

    @Override
    protected void initData() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        final int userId = login.getInt("userId", 0);
        final String sessionId = login.getString("sessionId", "");
        qiu_smart.setRefreshHeader(new ClassicsHeader(getContext()));
        qiu_smart.setRefreshFooter(new ClassicsFooter(getContext()));
        qiu_smart.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.showQiu(userId,sessionId,page);
                refreshLayout.finishLoadMore(2000/*,false*/);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.showQiu(userId,sessionId,page);
                refreshLayout.finishRefresh(2000/*,false*/);
            }
        });
        presenter.showQiu(userId,sessionId,page);
    }

    @Override
    protected QiuPresenter<QiuContract.QiuView> createPresenter() {
        return new QiuPresenter<>();
    }

}
