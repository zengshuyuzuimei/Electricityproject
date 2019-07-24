package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.StatusBean;
import com.example.electricityproject.ui.activity.App;

import java.util.List;

import retrofit2.http.POST;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 19:29:24
 *@Description:
 * */
public class AllAdapter extends BaseQuickAdapter<StatusBean.OrderListBean, BaseViewHolder> {
    public AllAdapter(int layoutResId, @Nullable List<StatusBean.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final StatusBean.OrderListBean item) {
        helper.setText(R.id.page_code,"订单号"+item.getOrderId());
        helper.setText(R.id.page_time,"2019-07-18");
        RecyclerView child_view = helper.getView(R.id.page_child_recycler);
        List<StatusBean.OrderListBean.DetailListBean> detailList = item.getDetailList();
        //创建适配器
        AllChildAdapter allChildAdapter = new AllChildAdapter(R.layout.status_all_child_view, detailList);
        child_view.setLayoutManager(new LinearLayoutManager(App.context,LinearLayoutManager.VERTICAL,false));
        child_view.setAdapter(allChildAdapter);
        for (int i = 0; i < detailList.size(); i++) {
            helper.setText(R.id.all_details,"共"+detailList.get(i).getCommodityCount()+"商品，需支付"+detailList.get(i).getCommodityPrice()+"元");
        }
        Button all_cal = helper.getView(R.id.all_cal);
        Button all_play = helper.getView(R.id.all_play);
        all_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPlayChange!=null){
                    onPlayChange.getData();
                }
            }
        });
        all_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPlayChange!=null){
                    String orderId = item.getOrderId();
                    onPlayChange.getDel(orderId);
                }
            }
        });
    }
    public interface onPlayChange{
        public  void getData();
        public void getDel(String orderId);
    }
    onPlayChange onPlayChange;
    public  void  setOnPlayChange(onPlayChange onPlayChange){
        this.onPlayChange = onPlayChange;
    }
}
