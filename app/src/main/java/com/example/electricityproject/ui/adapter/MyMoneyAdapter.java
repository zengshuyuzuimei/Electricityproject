package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.MyMoneyBean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/23
 *@Time: 10:13:13
 *@Description:
 * */
public class MyMoneyAdapter extends BaseQuickAdapter<MyMoneyBean.ResultBean.DetailListBean, BaseViewHolder> {

    public MyMoneyAdapter(int layoutResId, @Nullable List<MyMoneyBean.ResultBean.DetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMoneyBean.ResultBean.DetailListBean item) {
        helper.setText(R.id.money,item.getAmount()+"");
        helper.setText(R.id.time,item.getConsumerTime()+"");
    }
}
