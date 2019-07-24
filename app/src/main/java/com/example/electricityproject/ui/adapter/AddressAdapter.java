package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.AddressBean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/19
 *@Time: 14:29:40
 *@Description:
 * */
public class AddressAdapter extends BaseQuickAdapter<AddressBean.ResultBean, BaseViewHolder> {
    public AddressAdapter(int layoutResId, @Nullable List<AddressBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AddressBean.ResultBean item) {
        helper.setText(R.id.address_name,item.getRealName());
        helper.setText(R.id.address_phone,item.getPhone());
        helper.setText(R.id.address_title,item.getAddress());
        final CheckBox checkBox = helper.getView(R.id.address_check);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (checkBox.isChecked()){
                        helper.setText(R.id.address_check_name,"当前地址");
                        int id = item.getId();
                        helper.setChecked(R.id.address_check,true);
                        onDefaultAddress.setData(id);

                    }else{
                        helper.setText(R.id.address_check_name,"设为默认地址");
                    }
            }
        });
    }
    public interface onDefaultAddress{
        public void setData(int id);
    }
    onDefaultAddress onDefaultAddress;
    public void setOnDefaultAddress(onDefaultAddress onDefaultAddress){
        this.onDefaultAddress = onDefaultAddress;
    }
}
