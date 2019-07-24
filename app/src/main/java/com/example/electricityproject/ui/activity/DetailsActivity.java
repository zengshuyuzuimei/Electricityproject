package com.example.electricityproject.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.electricityproject.R;
import com.example.electricityproject.data.bean.DetailsBean;
import com.example.electricityproject.data.bean.Msg;
import com.example.electricityproject.di.contract.DetailsContract;
import com.example.electricityproject.di.presenter.DetailsPresenter;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

public class DetailsActivity extends BaseActivity<DetailsContract.DetailsView, DetailsPresenter<DetailsContract.DetailsView>> implements DetailsContract.DetailsView {


    @BindView(R.id.detail_back)
    ImageView detailBack;
    @BindView(R.id.detail_img1)
    Banner detailImg1;
    @BindView(R.id.detail_price)
    TextView detailPrice;
    @BindView(R.id.detail_name)
    TextView detailName;
    ImageView detailImg3;
    @BindView(R.id.detail_zl)
    TextView detailZl;
    @BindView(R.id.detail_buy)
    ImageView detailBuy;
    @BindView(R.id.detail_add)
    ImageView detailAdd;
    @BindView(R.id.web_view)
    WebView webView;
    private Context context;
    private DetailsBean.ResultBean resultBeans;

    @Override
    protected void initData() {
        context = this;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(Msg msg) {
        int id = msg.getId();
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        presenter.Show(id, userId, sessionId);
    }

    @Override
    protected DetailsPresenter<DetailsContract.DetailsView> createPresenter() {
        return new DetailsPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_details2;
    }

    @Override
    public void show(String string) {
        Log.e("aaa", "" + string);
        Gson gson = new Gson();
        DetailsBean detailsBean = gson.fromJson(string, DetailsBean.class);
        resultBeans = detailsBean.getResult();
        String picture = resultBeans.getPicture();
        String[] split = picture.split(",");
        final ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
        detailImg1.setImages(strings);
        detailImg1.isAutoPlay(true);
        detailImg1.setDelayTime(2000);
        detailImg1.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        detailImg1.start();
        detailName.setText(resultBeans.getCommodityName());
        detailPrice.setText(resultBeans.getPrice() + "");
        detailZl.setText("重量         " + resultBeans.getSaleNum() + "");
        webView.loadData("<html><title></title><body>" + resultBeans.getDetails() + "</body></html>", "text/html", "utf-8");
        webView.setWebChromeClient(new WebChromeClient());

    }

    @Override
    public void showAddCar(String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.detail_back, R.id.detail_buy, R.id.detail_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detail_back:
                finish();
                break;
            case R.id.detail_buy:
                break;
            case R.id.detail_add:
                Gson gson = new Gson();
                SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
                int userId = login.getInt("userId", 0);
                String sessionId = login.getString("sessionId", "");
                HashMap<String, Integer> hashMap = new HashMap<>();
                hashMap.put("count", 1);
                hashMap.put("commodityId", resultBeans.getCommodityId());
                String toJson = gson.toJson(hashMap);
                FormBody formBody = new FormBody.Builder()
                        .add("data", "[" + toJson + "]")
                        .build();
                presenter.showCar(userId, sessionId, formBody);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
