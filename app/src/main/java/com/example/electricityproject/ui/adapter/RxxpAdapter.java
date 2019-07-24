package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.RecyclerBean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/16
 *@Time: 21:04:06
 *@Description:
 * */
public class RxxpAdapter extends BaseQuickAdapter<RecyclerBean.ResultBean.RxxpBean.CommodityListBean, BaseViewHolder> {
    public RxxpAdapter(int layoutResId, @Nullable List<RecyclerBean.ResultBean.RxxpBean.CommodityListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecyclerBean.ResultBean.RxxpBean.CommodityListBean item) {
        ImageView img = helper.getView(R.id.rxxp_img);
        helper.setText(R.id.rxxp_title,item.getCommodityName());
        helper.setText(R.id.rxxp_price,item.getPrice()+"");
        Glide.with(mContext).load(item.getMasterPic()).into(img);
    }
}
