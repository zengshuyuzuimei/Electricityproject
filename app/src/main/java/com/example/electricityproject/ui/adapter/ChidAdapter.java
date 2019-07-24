package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.Bean;
import com.example.electricityproject.ui.widget.SubView;


import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 09:15:00
 *@Description:
 * */
public class ChidAdapter extends BaseQuickAdapter<Bean.ResultBean.ShoppingCartListBean, BaseViewHolder> {
    public ChidAdapter(int layoutResId, @Nullable List<Bean.ResultBean.ShoppingCartListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Bean.ResultBean.ShoppingCartListBean item) {
        helper.setText(R.id.child_name,item.getCommodityName());
        helper.setText(R.id.child_price,item.getPrice()+"");
        final CheckBox child_check = helper.getView(R.id.child_check);
        helper.setChecked(R.id.child_check,item.getChild_select());
        child_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = child_check.isChecked();
                item.setChild_select(checked);
                onChildSelected.setChild();
            }
        });
        ImageView imageView = helper.getView(R.id.child_img);
        Glide.with(mContext).load(item.getPic()).into(imageView);
        final SubView subView = helper.getView(R.id.subview);
        subView.setOnNumberChange(new SubView.onNumberChange() {
            @Override
            public void setNumber(int number) {
                if (onChildSelected!=null){
                    item.setCount(number);
                    subView.setNumber(number);
                    onChildSelected.setChild();
                }
            }
        });
    }
    //接口回调
    public interface onChildSelected{
        public void setChild();

    }
    onChildSelected onChildSelected;
    public void setOnChildSelected(onChildSelected onChildSelected){
        this.onChildSelected = onChildSelected;
    }
}
