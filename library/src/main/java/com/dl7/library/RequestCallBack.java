package com.dl7.library;

import com.dl7.library.utils.AssetsHelper;
import com.dl7.library.utils.GsonHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by long on 2016/5/9.
 * 对CallBack进行封装
 */
public abstract class RequestCallBack implements Callback<BaseResponse> {

    // 请求成功是的状态值
    private static final String RESPONSE_SUCC = "1";
    // 请求失败是的状态值
    private static final String RESPONSE_FAIL = "-1";

    // 测试用
    public static boolean mIsList = false;


    @Override
    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
        BaseResponse body = response.body();
        _handleResponse(body);
    }

    @Override
    public void onFailure(Call<BaseResponse> call, Throwable t) {
//        t.printStackTrace();
//        onError(t.getMessage());
        /*******************测试用，实际用上面的代码**********************/
        if (mIsList) {
            _handleResponse(GsonHelper.convertEntity(AssetsHelper.readList(), BaseResponse.class));
        } else {
            _handleResponse(GsonHelper.convertEntity(AssetsHelper.readData(), BaseResponse.class));
        }
    }

    /**
     * 处理应答
     * @param response 应答实体
     */
    private void _handleResponse(BaseResponse response) {
        try {
            if (RESPONSE_SUCC.equals(response.getStatus())) {
                // 请求成功才处理数据
                onDataObtain(GsonHelper.object2JsonStr(response.getData()));
            } else {
                onError(response.getMsg());
            }
        } catch (Exception e) {
            if (e.getMessage() == null) {
                onError("thread exiting with uncaught exception");
            } else {
                onError(e.getMessage());
            }
        }
    }

    /**
     * 获取json数据
     * @param jsonData json字符串
     * @return
     */
    protected abstract void onDataObtain(String jsonData);

    /**
     * 获取错误数据
     * @param errMsg 错误数据
     */
    protected abstract void onError(String errMsg);
}
