package com.dl7.library.user;

import com.dl7.library.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by long on 2016/5/11.
 * 用户信息Api
 */
public interface IUserService {

    @GET("user/your")
    Call<BaseResponse> user(@Query("id") int id);

    @GET("users/your/list")
    Call<BaseResponse> userList(@Query("param") String param);
}
