package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.Bean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 15:00:02
 *@Description:
 * */
public class SureChildAdapter extends BaseQuickAdapter<Bean.ResultBean.ShoppingCartListBean, BaseViewHolder> {
    public SureChildAdapter(int layoutResId, @Nullable List<Bean.ResultBean.ShoppingCartListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean.ResultBean.ShoppingCartListBean item) {
        helper.setChecked(R.id.sure_child_check,item.getChild_select());
        helper.setText(R.id.sure_child_name,item.getCommodityName());
        helper.setText(R.id.sure_child_price,item.getPrice()+"");
        ImageView img = helper.getView(R.id.sure_child_img);
        Glide.with(mContext).load(item.getPic()).into(img);
    }
}
