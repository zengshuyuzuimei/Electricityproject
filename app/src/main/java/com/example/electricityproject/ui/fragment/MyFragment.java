package com.example.electricityproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.MyDataBean;
import com.example.electricityproject.di.contract.MyContract;
import com.example.electricityproject.di.presenter.MyPresenter;
import com.example.electricityproject.ui.activity.AddressActivity;
import com.example.electricityproject.ui.activity.MyCircleActivity;
import com.example.electricityproject.ui.activity.MyFootActivity;
import com.example.electricityproject.ui.activity.MyMoneyActivity;
import com.example.electricityproject.ui.activity.MydataActivity;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
public class MyFragment extends BaseFragment<MyContract.MyView, MyPresenter<MyContract.MyView>> implements MyContract.MyView {
    @BindView(R.id.my_data)
    TextView myData;
    @BindView(R.id.my_circle)
    TextView myCircle;
    @BindView(R.id.my_foot)
    TextView myFoot;
    @BindView(R.id.my_money)
    TextView myMoney;
    @BindView(R.id.my_address)
    TextView myAddress;
    Unbinder unbinder;
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_img)
    ImageView myImg;
    Unbinder unbinder1;
    @BindView(R.id.line3)
    LinearLayout line3;
    @BindView(R.id.line4)
    LinearLayout line4;

    @Override
    public void show(String string) {
        Gson gson = new Gson();
        MyDataBean myDataBean = gson.fromJson(string, MyDataBean.class);
        MyDataBean.ResultBean result = myDataBean.getResult();
        myName.setText(result.getNickName());
        Glide.with(getContext()).load(result.getHeadPic()).into(myImg);
    }

    @Override
    protected int getLayout() {
        return R.layout.my_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", 1);
        String sessionId = login.getString("sessionId", "");
        presenter.show(userId, sessionId);
    }

    @Override
    protected MyPresenter<MyContract.MyView> createPresenter() {
        return new MyPresenter<>();
    }


    @OnClick({R.id.my_data, R.id.my_circle, R.id.my_foot, R.id.my_money, R.id.my_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_data:
                startActivity(new Intent(getContext(), MydataActivity.class));
                break;
            case R.id.my_circle:
                startActivity(new Intent(getContext(), MyCircleActivity.class));
                break;
            case R.id.my_foot:
                startActivity(new Intent(getContext(), MyFootActivity.class));
                break;
            case R.id.my_money:
                startActivity(new Intent(getContext(), MyMoneyActivity.class));
                break;
            case R.id.my_address:
                startActivity(new Intent(getContext(), AddressActivity.class));
                break;
        }
    }


    @OnClick(R.id.my_img)
    public void onViewClicked() {

//        try {
//            //上传头像
//            SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//            int userId = login.getInt("userId", 1);
//            String sessionId = login.getString("sessionId", "");
//            //打开资产目录文件下的图片，针对该图片进行上传
//            InputStream inputStream = getActivity().getAssets().open("wu.png");
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            //将bitmap转换为file文件
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//            File file = new File(Environment.getExternalStorageDirectory(), "wu.png");
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(byteArrayOutputStream.toByteArray());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            //将文件明确成请求体
//
//            presenter.showImage(userId,sessionId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
