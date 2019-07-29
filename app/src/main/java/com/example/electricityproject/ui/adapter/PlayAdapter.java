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

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 20:01:02
 *@Description:
 * */
public class PlayAdapter extends BaseQuickAdapter<StatusBean.OrderListBean, BaseViewHolder> {
    public PlayAdapter(int layoutResId, @Nullable List<StatusBean.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final StatusBean.OrderListBean item) {
        helper.setText(R.id.play_code,"订单号"+item.getOrderId());
        helper.setText(R.id.play_time,"2019-07-18");
        RecyclerView play_child_recycler = helper.getView(R.id.play_child_recycler);
        List<StatusBean.OrderListBean.DetailListBean> detailList = item.getDetailList();
        //创建适配器
        PlayChildAdapter playChildAdapter = new PlayChildAdapter(R.layout.play_child_view, detailList);
        play_child_recycler.setLayoutManager(new LinearLayoutManager(App.context,LinearLayoutManager.VERTICAL,false));
        play_child_recycler.setAdapter(playChildAdapter);
        for (int i = 0; i < detailList.size(); i++) {
            helper.setText(R.id.play_details,"共"+detailList.get(i).getCommodityCount()+"商品，需支付"+detailList.get(i).getCommodityPrice()+"元");
        }
        Button all_cal = helper.getView(R.id.play_cal);
        Button all_play = helper.getView(R.id.play_play);
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
                    onPlayChange.getCancel(item.getOrderId());
                }
            }
        });
    }
    public interface onPlayChange{
        public  void getData();
        public void getCancel( String id);
    }
    onPlayChange onPlayChange;
    public  void  setOnPlayChange(onPlayChange onPlayChange){
        this.onPlayChange = onPlayChange;
    }
}
