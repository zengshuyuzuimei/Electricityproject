package com.example.electricityproject.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.HomeMessag;
import com.example.electricityproject.data.sqlite.DaoSession;
import com.example.electricityproject.data.sqlite.FlowDao;
import com.example.electricityproject.data.table.Flow;
import com.example.electricityproject.ui.widget.FlowView;
import com.example.electricityproject.ui.widget.SearchView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private FlowView flowView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.search);
        flowView = findViewById(R.id.flow);
        //获取数据库对象
        //点击事件
        searchView.setOnSearchClick(new SearchView.onSearchClick() {
            @Override
            public void setClick(String s) {
                Toast.makeText(SearchActivity.this, s, Toast.LENGTH_SHORT).show();
                TextView textView = new TextView(SearchActivity.this);
                textView.setText(s);
                flowView.addView(textView);
                HomeMessag homeMessag = new HomeMessag();
                homeMessag.setName(s);
                EventBus.getDefault().postSticky(homeMessag);
                startActivity(new Intent(SearchActivity.this,Search2Activity.class));
            }
        });

    }
}
