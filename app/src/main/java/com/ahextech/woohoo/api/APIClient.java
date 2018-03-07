package com.ahextech.woohoo.api;

import com.ahextech.woohoo.POJO.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ahextech on 7/3/18.
 */

public interface APIClient {

    @POST("/api/login")
    @FormUrlEncoded
    Call<LoginResponseModel> authenticate(@Field("email") String email,
                                          @Field("password") String password);
}
