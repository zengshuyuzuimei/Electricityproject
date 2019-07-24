package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.AddressBean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 08:36:13
 *@Description:
 * */
public class PupopAdapter extends BaseQuickAdapter<AddressBean.ResultBean, BaseViewHolder> {
    public PupopAdapter(int layoutResId, @Nullable List<AddressBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AddressBean.ResultBean item) {
        helper.setText(R.id.pp_phone,item.getPhone());
        helper.setText(R.id.pp_name,item.getRealName());
        helper.setText(R.id.pp_address,item.getAddress());
        TextView pp_choose = helper.getView(R.id.pp_choose);
        pp_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChooseClick!=null){
                    onChooseClick.getDate(item.getAddress(),item.getPhone(),item.getRealName(),item.getId());
                }
            }
        });
    }
    public interface onChooseClick{
        public void getDate(String address, String phone, String realName,int id);
    }
    onChooseClick onChooseClick;
    public void setOnChooseClick(onChooseClick onChooseClick){
        this.onChooseClick = onChooseClick;
    }
}
