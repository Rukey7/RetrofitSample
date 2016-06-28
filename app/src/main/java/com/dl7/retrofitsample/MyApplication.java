package com.dl7.retrofitsample;

import android.app.Application;

import com.dl7.library.RetrofitService;


/**
 * Created by long on 2016/6/28.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitService.init(this);
    }
}
