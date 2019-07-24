package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
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
 *@Date: 2019/7/22
 *@Time: 20:03:42
 *@Description:
 * */
public class PlayChildAdapter extends BaseQuickAdapter<StatusBean.OrderListBean.DetailListBean, BaseViewHolder> {
    public PlayChildAdapter(int layoutResId, @Nullable List<StatusBean.OrderListBean.DetailListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final StatusBean.OrderListBean.DetailListBean item) {
        final ImageView img = helper.getView(R.id.play_img);
        helper.setText(R.id.play_name,item.getCommodityName());
        helper.setText(R.id.play_price,item.getCommodityPrice()+"");
        String commodityPic = item.getCommodityPic();
        String[] split = commodityPic.split(",");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
        for (int i = 0; i < list.size(); i++) {
            Glide.with(mContext).load(list.get(i)).into(img);
        }
        final SubView subView = helper.getView(R.id.play_subview);
        subView.setNumber(item.getCommodityCount());
        subView.setOnNumberChange(new SubView.onNumberChange() {
            @Override
            public void setNumber(int number) {
                subView.setNumber(number);
                item.setCommodityCount(number);
            }
        });
    }
}
