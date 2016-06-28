package com.dl7.library;

import android.content.Context;

import com.dl7.library.user.UserService;
import com.dl7.library.utils.AssetsHelper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by long on 2016/5/9.
 * 整个网络通信服务的启动控制，必须先调用初始化函数才能正常使用网络通信接口
 */
public class RetrofitService {

    private RetrofitService() {
        throw new RuntimeException("RetrofitService cannot be initialized!");
    }

    /**
     * 初始化网络通信服务
     */
    public static void init(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.yoururl.com/")
                .build();
        UserService.init(retrofit);
        AssetsHelper.init(context);
    }

}
