package com.example.electricityproject.ui.activity;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.electricityproject.data.sqlite.DaoMaster;
import com.example.electricityproject.data.sqlite.DaoSession;


/*
 *@Auther:吴亦凡的小可爱
 *@Date: 2019/7/17
 *@Time: 20:54:09
 *@Description:
 * */
public class App extends Application {

    public static Context context;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = App.this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "bawei");
        SQLiteDatabase writableDatabase = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
