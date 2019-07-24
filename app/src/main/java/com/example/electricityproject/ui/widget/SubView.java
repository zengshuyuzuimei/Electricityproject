package com.example.electricityproject.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricityproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/18
 *@Time: 11:56:28
 *@Description:
 * */
public class SubView extends LinearLayout {
    @BindView(R.id.del)
    TextView del;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.add)
    TextView add;
    private int number = 0;
    public SubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = inflate(context, R.layout.sub_view, this);
        ButterKnife.bind(this, inflate);
    }

    @OnClick({R.id.del, R.id.add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.del:
                if (number>1){
                    --number;
                    num.setText(number+"");
                    if (onNumberChange!=null){
                        onNumberChange.setNumber(number);
                    }
                }else{
                    Toast.makeText(getContext(), "不能在减少宝贝啦~~~~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add:
                ++number;
                num.setText(number+"");
                if (onNumberChange!=null){
                    onNumberChange.setNumber(number);
                }
                break;
        }
    }

    public interface onNumberChange{
        public void setNumber(int number);
    }
    onNumberChange onNumberChange;
    public void setOnNumberChange(onNumberChange onNumberChange){
        this.onNumberChange = onNumberChange;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        num.setText(number+"");
    }
}
