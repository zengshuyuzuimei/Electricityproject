package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
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
 *@Time: 22:12:21
 *@Description:
 * */
public class MlssAdapter extends BaseQuickAdapter<RecyclerBean.ResultBean.MlssBean.CommodityListBeanXX, BaseViewHolder> {
    public MlssAdapter(int layoutResId, @Nullable List<RecyclerBean.ResultBean.MlssBean.CommodityListBeanXX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecyclerBean.ResultBean.MlssBean.CommodityListBeanXX item) {
        ImageView img = helper.getView(R.id.mlss_img);
        helper.setText(R.id.mlss_title,item.getCommodityName());
        helper.setText(R.id.mlss_price,item.getPrice()+"");
        Glide.with(mContext).load(item.getMasterPic()).into(img);
    }
}
