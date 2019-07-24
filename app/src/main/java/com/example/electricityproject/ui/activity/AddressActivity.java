package com.example.electricityproject.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.AddressBean;
import com.example.electricityproject.di.contract.AddressContract;
import com.example.electricityproject.di.presenter.AddressPresenter;
import com.example.electricityproject.ui.adapter.AddressAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity<AddressContract.AddressView, AddressPresenter<AddressContract.AddressView>> implements AddressContract.AddressView {


    @BindView(R.id.address_recycler)
    RecyclerView addressRecycler;
    @BindView(R.id.add_address)
    Button addAddress;
    @BindView(R.id.address_over)
    TextView addressOver;

    @Override
    public void show(String string) {
        Gson gson = new Gson();
        AddressBean addressBean = gson.fromJson(string, AddressBean.class);
        ArrayList<AddressBean.ResultBean> result = (ArrayList<AddressBean.ResultBean>) addressBean.getResult();
        //创建适配器
        AddressAdapter addressAdapter = new AddressAdapter(R.layout.address_view, result);
        addressRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        addressRecycler.setAdapter(addressAdapter);
        addressAdapter.setOnDefaultAddress(new AddressAdapter.onDefaultAddress() {
            @Override
            public void setData(int id) {
                SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
                int userId = login.getInt("userId", 0);
                String sessionId = login.getString("sessionId", "");
                presenter.showDefault(userId,sessionId,id);
            }
        });
    }

    @Override
    public void showDefault(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        presenter.showAddress(userId, sessionId);
    }

    @Override
    protected AddressPresenter<AddressContract.AddressView> createPresenter() {
        return new AddressPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_address;
    }



    @OnClick({R.id.address_over, R.id.add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_over:
                finish();
                break;
            case R.id.add_address:
                startActivity(new Intent(AddressActivity.this,NewAddressActivity.class));
                break;
        }
    }
}
