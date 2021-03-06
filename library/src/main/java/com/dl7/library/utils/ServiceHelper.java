package com.dl7.library.utils;


import com.dl7.library.BaseResponse;
import com.dl7.library.OnRequestListener;
import com.dl7.library.RequestCallBack;

import java.util.List;

import retrofit2.Call;


/**
 * Created by long on 2016/5/10.
 * 请求处理的通用模块
 */
public final class ServiceHelper {

    private ServiceHelper() {
        throw new RuntimeException("ServiceHelper cannot be initialized!");
    }


    /**
     * 获取单个实体的处理操作
     *
     * @param call        请求
     * @param entityClass 实体类型
     * @param listener    监听器
     */
    public static <T> void callEntity(Call<BaseResponse> call, final Class<T> entityClass,
                                      final OnRequestListener<T> listener) {
        call.enqueue(new RequestCallBack() {
            @Override
            protected void onDataObtain(String jsonData) {
                T info = GsonHelper.convertEntity(jsonData, entityClass);
                if (info == null) {
                    if (listener != null) {
                        listener.onFailure("对象解析失败");
                    }
                } else {
                    if (listener != null) {
                        listener.onResponse(info);
                    }
                }
            }

            @Override
            protected void onError(String errMsg) {
                if (listener != null) {
                    listener.onFailure(errMsg);
                }
            }
        });
    }

    /**
     * 获取复数实体的处理操作
     *
     * @param call        请求
     * @param entityClass 实体类型
     * @param listener    监听器
     */
    public static <T> void callEntities(Call<BaseResponse> call, final Class<T> entityClass,
                                        final OnRequestListener<List<T>> listener) {
        call.enqueue(new RequestCallBack() {
            @Override
            protected void onDataObtain(String jsonData) {
                List<T> infos = GsonHelper.convertEntities(jsonData, entityClass);
                if (infos.size() == 0) {
                    if (listener != null) {
                        listener.onFailure("对象解析失败");
                    }
                } else {
                    if (listener != null) {
                        listener.onResponse(infos);
                    }
                }
            }

            @Override
            protected void onError(String errMsg) {
                if (listener != null) {
                    listener.onFailure(errMsg);
                }
            }
        });
    }
}
