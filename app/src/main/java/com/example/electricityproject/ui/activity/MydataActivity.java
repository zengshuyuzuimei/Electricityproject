package com.example.electricityproject.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.electricityproject.R;
import com.example.electricityproject.data.constant.Constant;
import com.example.electricityproject.data.net.ApiService;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
                        finish();
                    }
                });
    }

    @OnClick({R.id.my_data_update, R.id.my_data_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_data_update:
                getEvent();
                break;
            case R.id.my_data_img:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&data!=null){
            Uri data1 = data.getData();
            Glide.with(this).load(data1).into(myDataImg);
            SharedPreferences login = getSharedPreferences("login", MODE_PRIVATE);
            int userId = login.getInt("userId", 0);
            String sessionId = login.getString("sessionId", "");
            //转换图片类型
            String [] pro = {MediaStore.Audio.Media.DATA};
            Cursor cursor = managedQuery(data1, pro, null, null, null);
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String string = cursor.getString(columnIndexOrThrow);
            File file = new File(string);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody image1 = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", file.getName(), requestBody)
                    .build();
            //获取multipartbody中所有的部分
            List<MultipartBody.Part> parts = image1.parts();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            apiService.sendImg(userId,sessionId,parts)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody body) throws Exception {
                            String string = body.string();
                            Toast.makeText(MydataActivity.this, string, Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }
}
