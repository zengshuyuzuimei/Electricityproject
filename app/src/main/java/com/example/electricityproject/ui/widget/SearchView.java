package com.example.electricityproject.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.electricityproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 14:17:16
 *@Description:
 * */
public class SearchView extends LinearLayout {

    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.img_edit)
    EditText imgEdit;
    @BindView(R.id.img_search)
    ImageView imgSearch;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = inflate(context, R.layout.search_view, this);
        ButterKnife.bind(this, inflate);
    }

    @OnClick(R.id.img_search)
    public void onViewClicked() {
        String s = imgEdit.getText().toString();
        onSearchClick.setClick(s);
    }
    //接口回调
    public interface onSearchClick{
        public void setClick(String s);
    }
    onSearchClick onSearchClick;
    public void setOnSearchClick(onSearchClick onSearchClick){
        this.onSearchClick = onSearchClick;
    }
}
