package com.example.electricityproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.Bean;
import com.example.electricityproject.data.bean.Msg;
import com.example.electricityproject.data.event.CarMsg;
import com.example.electricityproject.di.contract.CarContract;
import com.example.electricityproject.di.presenter.CarPresenter;
import com.example.electricityproject.ui.activity.SureActivity;
import com.example.electricityproject.ui.adapter.ParentAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 19:56:29
 *@Description:购物车页面
 * */
public class CarFragment extends BaseFragment<CarContract.CarView, CarPresenter<CarContract.CarView>> implements CarContract.CarView {

    private RecyclerView recycler;
    private CheckBox allCheck;
    private TextView price;
    private Button btn;
    private ArrayList<Bean.ResultBean> beans;
    private ParentAdapter parentAdapter;

    @Override
    protected int getLayout() {
        return R.layout.car_fragment;
    }

    @Override
    protected void initView(View view) {
        recycler = view.findViewById(R.id.recycler);
        allCheck = view.findViewById(R.id.all_check);
        price = view.findViewById(R.id.price);
        btn = view.findViewById(R.id.btn);
    }

    @Override
    protected void initData() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 1);
        String sessionId = login.getString("sessionId", "");
        presenter.showRecycler(userId,sessionId);
    }

    @Override
    protected CarPresenter<CarContract.CarView> createPresenter() {
        return new CarPresenter<>();
    }

    @Override
    public void show(String string) {
        //解析数据
        Gson gson = new Gson();
        final Bean bean = gson.fromJson(string, Bean.class);
        beans = (ArrayList<Bean.ResultBean>) bean.getResult();
        //创建适配器
        parentAdapter = new ParentAdapter(R.layout.parent_view, beans);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycler.setAdapter(parentAdapter);
        parentAdapter.setOnParentSelected(new ParentAdapter.onParentSelected() {
            @Override
            public void setParent() {
                boolean check = true;
                for (int i = 0; i < beans.size() ; i++) {
                    boolean parent_select = beans.get(i).getParent_select();
                    List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = beans.get(i).getShoppingCartList();
                    for (int j = 0; j < shoppingCartList.size(); j++) {
                        boolean child_select = shoppingCartList.get(j).getChild_select();
                        check = check & parent_select & child_select;
                    }
                }
                allCheck.setChecked(check);
                onRefresh();
            }
        });
        parentAdapter.notifyDataSetChanged();
    }

    public void onRefresh(){
        float totalPrice = 0;
        int totalCount=0;
        for (int i = 0; i < beans.size(); i++) {
            List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = beans.get(i).getShoppingCartList();
            for (int j = 0; j < shoppingCartList.size(); j++) {
                int count = shoppingCartList.get(j).getCount();
                int price = shoppingCartList.get(j).getPrice();
                if (shoppingCartList.get(j).getChild_select()){
                    totalPrice += price*count;
                    totalCount =totalCount+count;
                }
            }
        }
        price.setText("总价:$"+totalPrice);
        btn.setText("去结算（"+totalCount+"）");
    }

    @OnClick({R.id.all_check, R.id.btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_check:
                boolean checked = allCheck.isChecked();
                for (int i = 0; i <beans.size() ; i++) {
                    beans.get(i).setParent_select(checked);
                    for (int j = 0; j <beans.get(i).getShoppingCartList().size() ; j++) {
                        beans.get(i).getShoppingCartList().get(j).setChild_select(checked);
                    }
                }
                parentAdapter.notifyDataSetChanged();
                onRefresh();
                break;
            case R.id.btn:
                boolean checked1 = allCheck.isChecked();
                if (checked1){
                    startActivity(new Intent(getContext(),SureActivity.class));
                }
                break;
        }
    }
}
