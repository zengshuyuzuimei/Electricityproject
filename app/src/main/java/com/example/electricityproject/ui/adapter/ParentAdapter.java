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
 *@Date: 2019/7/18
 *@Time: 09:06:58
 *@Description:
 * */
public class ParentAdapter extends BaseQuickAdapter<Bean.ResultBean, BaseViewHolder> {
    public ParentAdapter(int layoutResId, @Nullable List<Bean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final Bean.ResultBean item) {
        helper.setText(R.id.parent_name,item.getCategoryName());
        final CheckBox parent_check = helper.getView(R.id.parent_check);
        helper.setChecked(R.id.parent_check,item.getParent_select());
        //获取子条目
        RecyclerView child_recycler = helper.getView(R.id.child_recycler);
        final List<Bean.ResultBean.ShoppingCartListBean> shoppingCartList = item.getShoppingCartList();
        //创建适配器
        final ChidAdapter chidAdapter = new ChidAdapter(R.layout.child_view, shoppingCartList);
        child_recycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        child_recycler.setAdapter(chidAdapter);
        chidAdapter.setOnChildSelected(new ChidAdapter.onChildSelected() {
            @Override
            public void setChild() {
                boolean check  = true;
                for (int i = 0; i <shoppingCartList.size() ; i++) {
                    boolean child_select = shoppingCartList.get(i).getChild_select();
                    check = check & child_select;
                }
                item.setParent_select(check);
                parent_check.setChecked(check);
                onParentSelected.setParent();
            }

        });
        parent_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = parent_check.isChecked();
                for (int i = 0; i <shoppingCartList.size() ; i++) {
                    shoppingCartList.get(i).setChild_select(checked);
                }
                item.setParent_select(checked);
                onParentSelected.setParent();
                chidAdapter.notifyDataSetChanged();
            }
        });

    }
    //接口回调
    public interface onParentSelected{
        public void setParent();
    }
    onParentSelected onParentSelected;
    public void setOnParentSelected(onParentSelected onParentSelected){
        this.onParentSelected = onParentSelected;
    }
}
