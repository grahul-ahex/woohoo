package com.ahextech.woohoo.signup;

import com.ahextech.woohoo.MyApplication;
import com.ahextech.woohoo.POJO.RegistrationReponseModel;
import com.ahextech.woohoo.api.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahextech on 12/3/18.
 */

public class SignUpDetailsPresenter {
    private MyInterface myInterface;

    public SignUpDetailsPresenter(MyInterface myInterface) {
        this.myInterface = myInterface;
    }


    public void registerUser(String email, String password, String username) {
        Call<RegistrationReponseModel> call = MyApplication.getInstance().getClient()
                .create(APIService.class).registerUser(email, password, username);
        call.enqueue(new Callback<RegistrationReponseModel>() {
            @Override
            public void onResponse(Call<RegistrationReponseModel> call, Response<RegistrationReponseModel> response) {
                if (call.isExecuted()) {
                    switch (response.code()) {
                        case 200:
                            myInterface.onRegistrationCompleted();
                            break;
                        case 400:
                            myInterface.onRegistrationFailed();
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<RegistrationReponseModel> call, Throwable t) {

            }
        });
    }
}
