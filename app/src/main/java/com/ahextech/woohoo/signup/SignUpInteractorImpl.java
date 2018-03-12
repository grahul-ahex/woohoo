package com.ahextech.woohoo.signup;


import com.ahextech.woohoo.MyApplication;
import com.ahextech.woohoo.POJO.UserNameAvailabilityResponse;
import com.ahextech.woohoo.api.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahextech on 8/3/18.
 */

public class SignUpInteractorImpl implements InteractorInterface, Callback<UserNameAvailabilityResponse> {
    private onUserNameAvailability listener;
    private String email;


    @Override
    public void checkUserNameAvailability(onUserNameAvailability listener, String email) {
        this.listener = listener;
        this.email = email;
        Call<UserNameAvailabilityResponse> call = MyApplication.getInstance()
                .getClient().create(APIService.class).checkUserNameAvailability(email);
        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<UserNameAvailabilityResponse> call, Response<UserNameAvailabilityResponse> response) {
        if (call.isExecuted()) {
            switch (response.code()) {
                case 200:
                    UserNameAvailabilityResponse model=response.body();
                    listener.onUserNameAvailable();
                    break;
                case 403:
                    listener.onUserNameUnavailable(email);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onFailure(Call<UserNameAvailabilityResponse> call, Throwable t) {
        listener.onConnectionStatus("No Internet Connection");
    }
}
