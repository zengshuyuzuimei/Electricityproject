package com.example.electricityproject.ui.activity;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.StatusBean;
import com.example.electricityproject.data.event.OrderId;
import com.example.electricityproject.di.contract.SubmitOrderContract;
import com.example.electricityproject.di.presenter.SubmitOrderPresenter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SubmitOrderActivity extends BaseActivity<SubmitOrderContract.SubmitOrderView, SubmitOrderPresenter<SubmitOrderContract.SubmitOrderView>> implements SubmitOrderContract.SubmitOrderView {

    @BindView(R.id.submit_btn)
    Button submitBtn;
    private String orderId;

    @Override
    public void show(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showStatus(String string) {
        Gson gson = new Gson();
        StatusBean statusBean = gson.fromJson(string, StatusBean.class);
        ArrayList<StatusBean.OrderListBean> listBeans = (ArrayList<StatusBean.OrderListBean>) statusBean.getOrderList();
        for (int i = 0; i < listBeans.size(); i++) {
            orderId = listBeans.get(i).getOrderId();
        }
    }

    @Override
    protected void initData() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        presenter.showStatus(userId,sessionId);
    }

    @Override
    protected SubmitOrderPresenter<SubmitOrderContract.SubmitOrderView> createPresenter() {
        return new SubmitOrderPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_submit_order;
    }


    @OnClick(R.id.submit_btn)
    public void onViewClicked() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        presenter.show(userId, sessionId,orderId,1);
    }

}
