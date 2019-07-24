package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.FootBean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 21:32:17
 *@Description:
 * */
public class FootAdapter extends BaseQuickAdapter<FootBean.ResultBean, BaseViewHolder> {
    public FootAdapter(int layoutResId, @Nullable List<FootBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FootBean.ResultBean item) {
        helper.setText(R.id.foot_name,item.getCommodityName());
        helper.setText(R.id.foot_price,"￥"+item.getPrice());
        helper.setText(R.id.foot_ll,"已浏览"+item.getBrowseNum()+"次");
        ImageView img = helper.getView(R.id.foot_img);
        Glide.with(mContext).load(item.getMasterPic()).into(img);
    }
}
