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
 *@Time: 22:18:03
 *@Description:
 * */
public class PzshAdapter extends BaseQuickAdapter<RecyclerBean.ResultBean.PzshBean.CommodityListBeanX, BaseViewHolder> {
    public PzshAdapter(int layoutResId, @Nullable List<RecyclerBean.ResultBean.PzshBean.CommodityListBeanX> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecyclerBean.ResultBean.PzshBean.CommodityListBeanX item) {
        ImageView img = helper.getView(R.id.pzsh_img);
        helper.setText(R.id.pzsh_title,item.getCommodityName());
        helper.setText(R.id.pzsh_price,item.getPrice()+"");
        Glide.with(mContext).load(item.getMasterPic()).into(img);
    }
}
