package com.ahextech.woohoo.api;

import com.ahextech.woohoo.POJO.LoginModel;
import com.ahextech.woohoo.POJO.LoginResponseModel;
import com.ahextech.woohoo.POJO.RegistrationModel;
import com.ahextech.woohoo.POJO.RegistrationReponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ahextech on 7/3/18.
 */

public interface APIService {

    @POST("/api/login/")
    Call<LoginResponseModel> authenticate(@Body LoginModel loginModel);

    @POST("/apiv2/user/")
    Call<RegistrationReponseModel> registerUser(@Body RegistrationModel regModel);

}
