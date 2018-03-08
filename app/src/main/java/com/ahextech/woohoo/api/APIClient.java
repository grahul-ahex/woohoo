package com.ahextech.woohoo.api;

import com.ahextech.woohoo.POJO.LoginModel;
import com.ahextech.woohoo.POJO.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ahextech on 7/3/18.
 */

public interface APIClient {

    @POST("/api/login")
    Call<LoginResponseModel> authenticate(@Body LoginModel loginModel);


}
