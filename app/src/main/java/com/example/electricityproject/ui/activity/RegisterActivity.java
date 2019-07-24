package com.example.electricityproject.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.di.contract.RegisterContract;
import com.example.electricityproject.di.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterContract.RegisterView, RegisterPresenter<RegisterContract.RegisterView>> implements RegisterContract.RegisterView {

    @BindView(R.id.user)
    EditText user;
    @BindView(R.id.eye)
    ImageView eye;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.register_login)
    TextView registerLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    public void show(String string) {
        if (string.equals("0000")){
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected RegisterPresenter<RegisterContract.RegisterView> createPresenter() {
        return new RegisterPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }


    @OnClick({R.id.register_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.btn_register:
                String name = user.getText().toString().trim();
                String pwds = pwd.getText().toString().trim();
                presenter.showRegister(name,pwds);
                break;
        }
    }
}
