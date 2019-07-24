package com.example.electricityproject.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.StatusBean;
import com.example.electricityproject.data.event.OrderId;
import com.example.electricityproject.di.contract.PageContract;
import com.example.electricityproject.di.presenter.PagePresenter;
import com.example.electricityproject.ui.adapter.AllAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 19:56:29
 *@Description:
 * */
public class PageFragment extends BaseFragment<PageContract.PageView, PagePresenter<PageContract.PageView>> implements PageContract.PageView {

    private RadioGroup radio;
    private FrameLayout frame;
    private PlayFragment playFragment;
    private AppraiseFragment appraiseFragment;
    private ShoppingFragment shoppingFragment;
    private GoFragment goFragment;
    private AllFragment allFragment;


    @Override
    public void show(String string) {

    }

    @Override
    protected int getLayout() {
        return R.layout.page_fragment;
    }

    @Override
    protected void initView(View view) {
        radio = view.findViewById(R.id.page_radio);
        frame = view.findViewById(R.id.page_frame);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                switch (checkedId){
                    case R.id.page_btn1:
                        transaction.show(allFragment).hide(playFragment).hide(shoppingFragment).hide(goFragment).hide(appraiseFragment);
                        break;
                    case R.id.page_btn2:
                        transaction.show(playFragment).hide(allFragment).hide(shoppingFragment).hide(goFragment).hide(appraiseFragment);
                        break;
                    case R.id.page_btn3:
                        transaction.show(shoppingFragment).hide(playFragment).hide(allFragment).hide(goFragment).hide(appraiseFragment);
                        break;
                    case R.id.page_btn4:
                        transaction.show(goFragment).hide(playFragment).hide(shoppingFragment).hide(allFragment).hide(appraiseFragment);
                        break;
                    case R.id.page_btn5:
                        transaction.show(appraiseFragment).hide(playFragment).hide(shoppingFragment).hide(goFragment).hide(allFragment);
                        break;
                }
                transaction.commit();
            }
        });
    }

    @Override
    protected void initData() {
        allFragment = new AllFragment();
        goFragment = new GoFragment();
        shoppingFragment = new ShoppingFragment();
        appraiseFragment = new AppraiseFragment();
        playFragment = new PlayFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.page_frame,allFragment).show(allFragment)
                .add(R.id.page_frame,playFragment).hide(playFragment)
                .add(R.id.page_frame,shoppingFragment).hide(shoppingFragment)
                .add(R.id.page_frame,goFragment).hide(goFragment)
                .add(R.id.page_frame,appraiseFragment).hide(appraiseFragment)
                .commit();

    }

    @Override
    protected PagePresenter<PageContract.PageView> createPresenter() {
        return new PagePresenter<>();
    }
}
