package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.StatusBean;
import com.example.electricityproject.ui.widget.SubView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 19:57:58
 *@Description:
 * */
public class GoChildAdapter extends BaseQuickAdapter<StatusBean.OrderListBean.DetailListBean, BaseViewHolder> {
    public GoChildAdapter(int layoutResId, @Nullable List<StatusBean.OrderListBean.DetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final StatusBean.OrderListBean.DetailListBean item) {
        final ImageView img = helper.getView(R.id.go_img);
        helper.setText(R.id.go_name,item.getCommodityName());
        helper.setText(R.id.go_price,item.getCommodityPrice()+"");
        String commodityPic = item.getCommodityPic();
        String[] split = commodityPic.split(",");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
        for (int i = 0; i < list.size(); i++) {
            Glide.with(mContext).load(list.get(i)).into(img);
        }
        Button go_appraise = helper.getView(R.id.go_appraise);
        go_appraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAppraise!=null){
                    onAppraise.getData();
                }
            }
        });
    }
    public interface onAppraise{
        public void getData();
    }
    onAppraise onAppraise;
    public void setOnAppraise(onAppraise onAppraise){
        this.onAppraise = onAppraise;
    }
}
