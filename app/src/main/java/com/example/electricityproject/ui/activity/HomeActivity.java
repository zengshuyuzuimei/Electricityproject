package com.example.electricityproject.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.electricityproject.R;
import com.example.electricityproject.di.contract.HomeContract;
import com.example.electricityproject.di.presenter.HomePresenter;
import com.example.electricityproject.ui.fragment.CarFragment;
import com.example.electricityproject.ui.fragment.HomeFragment;
import com.example.electricityproject.ui.fragment.MyFragment;
import com.example.electricityproject.ui.fragment.PageFragment;
import com.example.electricityproject.ui.fragment.QiuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<HomeContract.HomeView, HomePresenter<HomeContract.HomeView>> implements HomeContract.HomeView {


    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.radio)
    RadioGroup radio;
    private HomeFragment homeFragment;
    private QiuFragment qiuFragment;
    private CarFragment carFragment;
    private PageFragment pageFragment;
    private MyFragment myFragment;

    @Override
    public void show(String string) {
    }

    @Override
    protected void initData() {
        presenter.showXBanner();
        //开始事务管理器
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        initFragment();
        manager.add(R.id.frame,homeFragment).show(homeFragment)
                .add(R.id.frame,qiuFragment).hide(qiuFragment)
                .add(R.id.frame,carFragment).hide(carFragment)
                .add(R.id.frame,pageFragment).hide(pageFragment)
                .add(R.id.frame,myFragment).hide(myFragment)
                .commit();
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId){
                    case R.id.btn1:
                        transaction.show(homeFragment).hide(qiuFragment).hide(carFragment).hide(pageFragment).hide(myFragment);
                        break;
                    case R.id.btn2:
                        transaction.show(qiuFragment).hide(homeFragment).hide(carFragment).hide(pageFragment).hide(myFragment);
                        break;
                    case R.id.btn3:
                        transaction.show(carFragment).hide(qiuFragment).hide(homeFragment).hide(pageFragment).hide(myFragment);
                        break;
                    case R.id.btn4:
                        transaction.show(pageFragment).hide(qiuFragment).hide(carFragment).hide(homeFragment).hide(myFragment);
                        break;
                    case R.id.btn5:
                        transaction.show(myFragment).hide(qiuFragment).hide(carFragment).hide(pageFragment).hide(homeFragment);
                        break;
                }
                transaction.commit();
            }
        });
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        qiuFragment = new QiuFragment();
        carFragment = new CarFragment();
        pageFragment = new PageFragment();
        myFragment = new MyFragment();
    }

    @Override
    protected HomePresenter<HomeContract.HomeView> createPresenter() {
        return new HomePresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

}
