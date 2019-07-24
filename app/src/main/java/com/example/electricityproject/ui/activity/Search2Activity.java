package com.example.electricityproject.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.HomeMessag;
import com.example.electricityproject.data.bean.Search2Bean;
import com.example.electricityproject.di.contract.SearchContract;
import com.example.electricityproject.di.presenter.SearchPresenter;
import com.example.electricityproject.ui.adapter.Search2Adapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Search2Activity extends BaseActivity<SearchContract.SearchView, SearchPresenter<SearchContract.SearchView>> implements SearchContract.SearchView {

    @BindView(R.id.search_recycler)
    RecyclerView searchRecycler;
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.img_edit)
    EditText imgEdit;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    private Search2Adapter search2Adapter;

    @Override
    protected void initData() {

    }

    @Override
    protected SearchPresenter<SearchContract.SearchView> createPresenter() {
        return new SearchPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getHomeMessage(HomeMessag messag) {
        String name = messag.getName();
        presenter.showRecycler(name);
        imgEdit.setText(name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void show(String string) {
        Gson gson = new Gson();
        Search2Bean search2Bean = gson.fromJson(string, Search2Bean.class);
        ArrayList<Search2Bean.ResultBean> result = (ArrayList<Search2Bean.ResultBean>) search2Bean.getResult();
        //创建适配器
        search2Adapter = new Search2Adapter(R.layout.search2_view, result);
        searchRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        searchRecycler.setAdapter(search2Adapter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_search)
    public void onViewClicked() {
        finish();
    }
}

