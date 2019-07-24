package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.Bean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/22
 *@Time: 14:56:44
 *@Description:
 * */
public class SureAdapter extends BaseQuickAdapter<Bean.ResultBean, BaseViewHolder> {
    public SureAdapter(int layoutResId, @Nullable List<Bean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Bean.ResultBean item) {
        helper.setText(R.id.sure_name,item.getCategoryName());
        helper.setChecked(R.id.sure_check, item.getParent_select());
        final RecyclerView sure_child_recycler = helper.getView(R.id.sure_child_recycler);
        final List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = item.getShoppingCartList();
        //创建适配器
        final SureChildAdapter sureChildAdapter = new SureChildAdapter(R.layout.sure_child_view, shoppingCartList);
        //设置布局管理器你
        sure_child_recycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        sure_child_recycler.setAdapter(sureChildAdapter);
        final CheckBox check = helper.getView(R.id.sure_check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = check.isChecked();
                if (onSureChecked!=null){
                    for (int i = 0; i < shoppingCartList.size(); i++) {
                        int price = shoppingCartList.get(i).getPrice();
                        int count = shoppingCartList.get(i).getCount();
                        shoppingCartList.get(i).setChild_select(checked);
                        sureChildAdapter.notifyDataSetChanged();
                        onSureChecked.getData(price,count);
                    }
                }
            }
        });
    }
    public interface onSureChecked{
        public void getData(int price,int num);
    }
    onSureChecked onSureChecked;
    public void setOnSureChecked(onSureChecked onSureChecked){
        this.onSureChecked = onSureChecked;
    }
}
