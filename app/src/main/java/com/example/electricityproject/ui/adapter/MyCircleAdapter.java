package com.example.electricityproject.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.MyCircleBean;

import java.util.List;

import retrofit2.http.PUT;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/20
 *@Time: 10:26:35
 *@Description:
 * */
public class MyCircleAdapter extends BaseQuickAdapter<MyCircleBean.ResultBean, BaseViewHolder> {
    private int number = 0;
    private boolean check = false;
    public MyCircleAdapter(int layoutResId, @Nullable List<MyCircleBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MyCircleBean.ResultBean item) {
        helper.setText(R.id.circle_title,item.getContent());
        helper.setText(R.id.circle_time,item.getCreateTime()+"");
        helper.setText(R.id.circle_num,item.getGreatNum()+"");
        ImageView circle_img = helper.getView(R.id.circle_img);
        final ImageView circle_zan = helper.getView(R.id.circle_zan);
        Glide.with(mContext).load(item.getImage()).into(circle_img);

        circle_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!check){
                    ++number;
                    helper.setText(R.id.circle_num, item.getGreatNum()+number +"");
                    Glide.with(mContext).load(R.mipmap.zan_false).into(circle_zan);
                    check = true;
                }else{
                    --number;
                    helper.setText(R.id.circle_num, item.getGreatNum()+number+"");
                    Glide.with(mContext).load(R.mipmap.zan_true).into(circle_zan);
                    check = false;
                }
            }
        });
    }

}
