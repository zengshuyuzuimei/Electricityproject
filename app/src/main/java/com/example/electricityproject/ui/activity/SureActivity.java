package com.example.electricityproject.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.AddressBean;
import com.example.electricityproject.data.bean.Bean;
import com.example.electricityproject.di.contract.SureContract;
import com.example.electricityproject.di.presenter.SurePresenter;
import com.example.electricityproject.ui.adapter.PupopAdapter;
import com.example.electricityproject.ui.adapter.SureAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SureActivity extends BaseActivity<SureContract.SureView, SurePresenter<SureContract.SureView>> implements SureContract.SureView {


    @BindView(R.id.address_btn)
    Button addressBtn;
    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;
    @BindView(R.id.sure_name)
    TextView sureName;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.sure_phone)
    TextView surePhone;
    @BindView(R.id.sure_address)
    TextView sureAddress;
    @BindView(R.id.sure_detail)
    TextView sureDetail;
    @BindView(R.id.sure_btn)
    Button sureBtn;
    @BindView(R.id.sure_next)
    ImageView sureNext;
    private PopupWindow mPopupWindow;
    private RecyclerView pp_recycler;
    private ArrayList<AddressBean.ResultBean> result;
    private int userId;
    private String sessionId;
    private ArrayList<Bean.ResultBean> result1;
    private int addressId = 0;
    //pupowindow弹窗
    @Override
    public void show(String string) {
        Gson gson = new Gson();
        AddressBean addressBean = gson.fromJson(string, AddressBean.class);
        result = (ArrayList<AddressBean.ResultBean>) addressBean.getResult();
        showPopupWindow();
        //创建适配器
        PupopAdapter pupopAdapter = new PupopAdapter(R.layout.pupop_recycler_view, result);
        pp_recycler.setLayoutManager(new LinearLayoutManager(SureActivity.this, LinearLayoutManager.VERTICAL, false));
        pp_recycler.setAdapter(pupopAdapter);
        pupopAdapter.setOnChooseClick(new PupopAdapter.onChooseClick() {
            @Override
            public void getDate(String address, String phone, String realName, int id) {
                //把选择的内容展示到信息页面
                sureAddress.setText(address);
                sureName.setText(realName);
                surePhone.setText(phone);
                addressId = id;
                addressBtn.setVisibility(View.GONE);
                relative.setVisibility(View.VISIBLE);
                mPopupWindow.dismiss();
            }

        });
    }

    //商品信息
    @Override
    public void showView(String string) {
        Gson gson = new Gson();
        Bean bean = gson.fromJson(string, Bean.class);
        result1 = (ArrayList<Bean.ResultBean>) bean.getResult();
        //创建适配器
        SureAdapter sureAdapter = new SureAdapter(R.layout.sure_view, result1);
        //设置布局管理器
        orderRecycler.setLayoutManager(new LinearLayoutManager(SureActivity.this,LinearLayoutManager.VERTICAL,false));
        orderRecycler.setAdapter(sureAdapter);
        sureAdapter.setOnSureChecked(new SureAdapter.onSureChecked() {
            @Override
            public void getData(int price, int num) {
                sureDetail.setText("共"+num+"件商品需支付"+(num*price)+"元");
            }
        });
    }

    @Override
    public void shoeOrder(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SubmitOrderActivity.class));
    }

    @Override
    protected void initData() {
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        presenter.showRecycler(userId, sessionId);
    }

    @Override
    protected SurePresenter<SureContract.SureView> createPresenter() {
        return new SurePresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sure;
    }


    private void showPopupWindow() {
        //获取布局
        View contentView = LayoutInflater.from(SureActivity.this).inflate(R.layout.popup_view, null);
        //设置弹出框的位置
        mPopupWindow = new PopupWindow(contentView, RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        //给弹出框设置布局
        mPopupWindow.setContentView(contentView);
        //设置各个控件的点击事件
        pp_recycler = contentView.findViewById(R.id.pp_recycler);
        //在按钮下面显示
        mPopupWindow.showAsDropDown(addressBtn, Gravity.BOTTOM, 0, 0);

    }


    @OnClick({R.id.address_btn, R.id.sure_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_btn:
                SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
                userId = login.getInt("userId", 0);
                sessionId = login.getString("sessionId", "");
                presenter.showAddress(userId, sessionId);
                break;
            case R.id.sure_btn:
                for (int i = 0; i < result1.size(); i++) {
                    List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = result1.get(i).getShoppingCartList();
                    for (int j = 0; j <shoppingCartList.size() ; j++) {
                        int totalPrice = 0;
                        if (shoppingCartList.get(j).getChild_select()){
                            int price = shoppingCartList.get(j).getPrice();
                            int count = shoppingCartList.get(j).getCount();
                            int commodityId = shoppingCartList.get(j).getCommodityId();
                            HashMap<String, Integer> hashMap = new HashMap<>();
                            hashMap.put("commodityId",commodityId);
                            hashMap.put("amount",count);
                            Gson gson = new Gson();
                            //总价格
                            totalPrice += count*price;
                            //orderInfo集合
                            String s = gson.toJson(hashMap);
                            presenter.showOrder(userId,sessionId,"["+s+"]",totalPrice,addressId);
                        }
                    }
                }
                break;
        }
    }


    @OnClick(R.id.sure_next)
    public void onViewClicked() {
        showPopupWindow();
        //创建适配器
        PupopAdapter pupopAdapter = new PupopAdapter(R.layout.pupop_recycler_view, result);
        pp_recycler.setLayoutManager(new LinearLayoutManager(SureActivity.this, LinearLayoutManager.VERTICAL, false));
        pp_recycler.setAdapter(pupopAdapter);
        pupopAdapter.setOnChooseClick(new PupopAdapter.onChooseClick() {
            @Override
            public void getDate(String address, String phone, String realName, int id) {
                //把选择的内容展示到信息页面
                sureAddress.setText(address);
                sureName.setText(realName);
                surePhone.setText(phone);
                addressBtn.setVisibility(View.GONE);
                relative.setVisibility(View.VISIBLE);
                addressId = id;
                mPopupWindow.dismiss();
            }

        });
    }
}
