package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
public class AppraiseAdapter extends BaseQuickAdapter<StatusBean.OrderListBean, BaseViewHolder> {
    public AppraiseAdapter(int layoutResId, @Nullable List<StatusBean.OrderListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatusBean.OrderListBean item) {
        helper.setText(R.id.appraise_code,"订单号"+item.getOrderId());
        RecyclerView child_view = helper.getView(R.id.appraise_child_recycler);
        List<StatusBean.OrderListBean.DetailListBean> detailList = item.getDetailList();
        //创建适配器
        AppraiseChildAdapter allChildAdapter = new AppraiseChildAdapter(R.layout.appraise_child_view, detailList);
        child_view.setLayoutManager(new LinearLayoutManager(App.context,LinearLayoutManager.VERTICAL,false));
        child_view.setAdapter(allChildAdapter);
        ImageView img = helper.getView(R.id.appraise_more);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onImgClick!=null){
                    onImgClick.getData();
                }
            }
        });
    }
    public interface onImgClick{
        public void getData();
    }
    onImgClick onImgClick;
    public void setOnImgClick(onImgClick onImgClick){
        this.onImgClick = onImgClick;
    }
}
