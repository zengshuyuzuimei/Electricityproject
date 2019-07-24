package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.Search2Bean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 23:51:26
 *@Description:
 * */
public class Search2Adapter extends BaseQuickAdapter<Search2Bean.ResultBean, BaseViewHolder> {
    public Search2Adapter(int layoutResId, @Nullable List<Search2Bean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Search2Bean.ResultBean item) {
        ImageView img = helper.getView(R.id.search2_pic);
        helper.setText(R.id.search2_name,item.getCommodityName());
        helper.setText(R.id.search2_price,item.getPrice()+"");
        helper.setText(R.id.search2_num,item.getSaleNum()+"");
        Glide.with(mContext).load(item.getMasterPic()).into(img);
    }
}
