package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.QiuBean;

import java.util.List;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 17:09:11
 *@Description:
 * */
public class QiuAdapter extends BaseQuickAdapter<QiuBean.ResultBean, BaseViewHolder> {
    private boolean check = false;
    public int num ;
    public QiuAdapter(int layoutResId, @Nullable List<QiuBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, QiuBean.ResultBean item) {
        ImageView qiu_img = helper.getView(R.id.qiu_img);
        final ImageView img1 = helper.getView(R.id.qiu_img1);
        ImageView img2 = helper.getView(R.id.qiu_img2);
        final ImageView img_zan = helper.getView(R.id.qiu_zan);
        num = item.getGreatNum();
        helper.setText(R.id.qiu_name,item.getNickName());
        helper.setText(R.id.qiu_num,item.getGreatNum()+"");
        helper.setText(R.id.qiu_title,item.getContent());
        helper.setText(R.id.qiu_time,item.getCreateTime()+"");
        Glide.with(mContext).load(item.getHeadPic()).into(qiu_img);
        Glide.with(mContext).load(item.getImage()).into(img1);
        img_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check){
                    ++num;
                    helper.setText(R.id.qiu_num,num+"");
                    Glide.with(mContext).load(R.mipmap.zan_false).into(img_zan);
                    check = true;
                }else{
                    --num;
                    helper.setText(R.id.qiu_num,num+"");
                    Glide.with(mContext).load(R.mipmap.zan_true).into(img_zan);
                    check = false;
                }

            }
        });
    }
}
