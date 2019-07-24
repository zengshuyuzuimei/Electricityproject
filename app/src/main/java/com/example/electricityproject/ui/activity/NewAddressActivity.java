package com.example.electricityproject.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.di.contract.NewAddressContract;
import com.example.electricityproject.di.presenter.NewAddressPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewAddressActivity extends BaseActivity<NewAddressContract.NewAddressView, NewAddressPresenter<NewAddressContract.NewAddressView>> implements NewAddressContract.NewAddressView {


    @BindView(R.id.new_address_name)
    EditText newAddressName;
    @BindView(R.id.new_address_phone)
    EditText newAddressPhone;
    @BindView(R.id.new_address_address)
    EditText newAddressAddress;
    @BindView(R.id.new_address_detail_address)
    EditText newAddressDetailAddress;
    @BindView(R.id.new_address_code)
    EditText newAddressCode;
    @BindView(R.id.new_address_btn)
    Button newAddressBtn;

    @Override
    public void show(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected NewAddressPresenter<NewAddressContract.NewAddressView> createPresenter() {
        return new NewAddressPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_new_address;
    }



    @OnClick(R.id.new_address_btn)
    public void onViewClicked() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
    int userId = login.getInt("userId", 0);
    String sessionId = login.getString("sessionId", "");
    String new_Address = newAddressAddress.getText().toString();
    String new_name = newAddressName.getText().toString();
    String new_phone = newAddressPhone.getText().toString();
    String new_code = newAddressCode.getText().toString();
    String new_detail = newAddressDetailAddress.getText().toString();
        presenter.show(userId, sessionId, new_Address, new_name, new_phone, new_code, new_detail);
}
}
