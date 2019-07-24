package com.example.electricityproject.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.LoginBean;
import com.example.electricityproject.di.contract.LoginContract;
import com.example.electricityproject.di.presenter.LoginPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.LoginView, LoginPresenter<LoginContract.LoginView>> implements LoginContract.LoginView {

    @BindView(R.id.user)
    EditText user;
    @BindView(R.id.eye)
    ImageView eye;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    Button login;
    private SharedPreferences sp;
    private boolean ischeck;

    @Override
    protected void initData() {
        sp = getSharedPreferences("sp", MODE_PRIVATE);
        ischeck = sp.getBoolean("ischeck", false);
        check.setChecked(ischeck);
        if (check.isChecked()){
            user.setText(sp.getString("name",""));
            pwd.setText(sp.getString("pwd",""));
        }

    }



    @Override
    public void show(String string) {
        if (!string.equals("0000")){
            startActivity(new Intent(this,HomeActivity.class));
            Gson gson = new Gson();
            LoginBean loginBean = gson.fromJson(string, LoginBean.class);
            LoginBean.ResultBean resultBeans = loginBean.getResult();
            SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
            login.edit().putInt("userId",resultBeans.getUserId())
                    .putString("sessionId",resultBeans.getSessionId())
                    .commit();

        }else{
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick({R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.login:
                String name = user.getText().toString().trim();
                String pwds = pwd.getText().toString().trim();
                presenter.showLogin(name,pwds);
                SharedPreferences.Editor edit = sp.edit();
                if (check.isChecked()){
                    edit.putBoolean("ischeck",true)
                            .putString("name",name)
                            .putString("pwd",pwds);
                }else{
                    edit.clear();
                }
                edit.commit();
                break;
        }
    }
    @Override
    protected LoginPresenter<LoginContract.LoginView> createPresenter() {
        return new LoginPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
