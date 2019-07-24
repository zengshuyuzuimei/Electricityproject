package com.example.electricityproject.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.electricityproject.R;
import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class MydataActivity extends AppCompatActivity {

    @BindView(R.id.my_data_img)
    ImageView myDataImg;
    @BindView(R.id.my_date_name)
    EditText myDateName;
    @BindView(R.id.my_data_update)
    TextView myDataUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydata);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.my_data_update)
    public void onViewClicked() {
        getEvent();

    }

    private void getEvent() {
        //请求接口
        String newName = myDateName.getText().toString();
        SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
        int userId = login.getInt("userId", 0);
        String sessionId = login.getString("sessionId", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.sendName(userId, sessionId, newName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        Toast.makeText(MydataActivity.this, body.string(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
