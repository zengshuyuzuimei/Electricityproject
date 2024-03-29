package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.StatusBean;
import com.example.electricityproject.ui.activity.App;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 19:29:24
 *@Description:
 * */
public class GoAdapter extends BaseQuickAdapter<StatusBean.OrderListBean, BaseViewHolder> {
    public GoAdapter(int layoutResId, @Nullable List<StatusBean.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatusBean.OrderListBean item) {
        helper.setText(R.id.go_code,"订单号"+item.getOrderId());
        helper.setText(R.id.go_time,"2019-07-18");
        RecyclerView child_view = helper.getView(R.id.go_child_recycler);
        List<StatusBean.OrderListBean.DetailListBean> detailList = item.getDetailList();
        //创建适配器
        GoChildAdapter goChildAdapter = new GoChildAdapter(R.layout.go_child_view, detailList);
        child_view.setLayoutManager(new LinearLayoutManager(App.context,LinearLayoutManager.VERTICAL,false));
        child_view.setAdapter(goChildAdapter);
        goChildAdapter.setOnAppraise(new GoChildAdapter.onAppraise() {
            @Override
            public void getData() {
                Toast.makeText(mContext, "就不评价", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
