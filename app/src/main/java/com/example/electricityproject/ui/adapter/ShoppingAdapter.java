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
 *@Date: 2019/7/18
 *@Time: 19:29:24
 *@Description:
 * */
public class ShoppingAdapter extends BaseQuickAdapter<StatusBean.OrderListBean, BaseViewHolder> {
    public ShoppingAdapter(int layoutResId, @Nullable List<StatusBean.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatusBean.OrderListBean item) {
        helper.setText(R.id.shopping_code,"订单号"+item.getOrderId());
        helper.setText(R.id.shopping_time,"2019-07-18");
        helper.setText(R.id.shopping_boss,item.getExpressCompName());
        helper.setText(R.id.shopping_dan_code,item.getExpressSn()+"");
        RecyclerView child_view = helper.getView(R.id.shopping_child_recycler);
        List<StatusBean.OrderListBean.DetailListBean> detailList = item.getDetailList();
        //创建适配器
        ShoppingChildAdapter shoppingChildAdapter = new ShoppingChildAdapter(R.layout.shopping_child_view, detailList);
        child_view.setLayoutManager(new LinearLayoutManager(App.context,LinearLayoutManager.VERTICAL,false));
        child_view.setAdapter(shoppingChildAdapter);
        Button all_play = helper.getView(R.id.shopping_huo);
        all_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPlayChange!=null){
                    onPlayChange.getData();
                }
            }
        });
    }
    public interface onPlayChange{
        public  void getData();
    }
    onPlayChange onPlayChange;
    public  void  setOnPlayChange(onPlayChange onPlayChange){
        this.onPlayChange = onPlayChange;
    }
}
