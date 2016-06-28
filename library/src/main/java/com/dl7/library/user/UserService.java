package com.dl7.library.user;

import com.dl7.library.BaseResponse;
import com.dl7.library.OnRequestListener;
import com.dl7.library.utils.ServiceHelper;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by long on 2016/5/11.
 * 给客户端调用的接口
 */
public class UserService {

    private static IUserService sUserService;

    private UserService() {
        throw new RuntimeException("UserService cannot be initialized!");
    }

    /**
     * 初始化服务
     *
     * @param retrofit Retrofit
     */
    public static void init(Retrofit retrofit) {
        sUserService = retrofit.create(IUserService.class);
    }

    /**
     * 调用流程
     */
    public static void userInfo() {
        // 创建Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.yoururl.com/")
                .build();
        // 创建IUserService
        sUserService = retrofit.create(IUserService.class);
        // 创建请求
        Call<BaseResponse> call = sUserService.user(10086);
        // 执行请求
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Gson gson = new Gson();
                try {
                    BaseResponse body = response.body();
                    // 先判断状态值
                    if (body.getStatus().equals("1")) {
                        // 将Object对象转化为json字符串
                        String jsonData = gson.toJson(body.getData());
                        // 将json字符串转化为对象实体
                        UserInfo info = gson.fromJson(jsonData, UserInfo.class);
                        // 判断解析是否成功
                        if (info == null) {
                            onFailure(call, new Throwable("对象解析失败"));
                        } else {
                            // 获取到数据，开始处理数据
                        }
                    } else {
                        onFailure(call, new Throwable(body.getMsg()));
                    }
                } catch (Exception e) {
                    onFailure(call, e);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                // 失败处理
            }
        });
    }

    /**
     * 获取实体列表
     * @param param 参数
     * @param listener 请求监听
     * @return 通信回调接口，用来取消通信
     */
    public static Call userList(String param, OnRequestListener<List<UserInfo>> listener) {
        Call<BaseResponse> call = sUserService.userList(param);
        ServiceHelper.callEntities(call, UserInfo.class, listener);
        return call;
    }

    /**
     * 获取单个实体
     * @param id 参数
     * @param listener 请求监听
     * @return 通信回调接口，用来取消通信
     */
    public static Call userInfo(int id, OnRequestListener<UserInfo> listener) {
        Call<BaseResponse> call = sUserService.user(id);
        ServiceHelper.callEntity(call, UserInfo.class, listener);
        return call;
    }

}
