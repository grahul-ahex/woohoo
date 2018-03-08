package com.ahextech.woohoo.login;


import com.ahextech.woohoo.POJO.LoginModel;
import com.ahextech.woohoo.POJO.LoginResponseModel;
import com.ahextech.woohoo.api.APIService;
import com.ahextech.woohoo.api.APIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahextech on 7/3/18.
 */

public class LoginInteractorImpl implements LoginInteractor, Callback<LoginResponseModel> {
    private onAuthCompletedListener listener;

    @Override
    public void authenticateUser(onAuthCompletedListener listener, String email, String password) {
        this.listener = listener;
        APIService apiService = APIClient.getClient().create(APIService.class);
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail(email);
        loginModel.setPassword(password);
        Call<LoginResponseModel> call = apiService.authenticate(loginModel);
        call.enqueue(this);
    }

    @Override
    public void validateFields(String email, String password, onValidateFieldListener validateFieldListener) {
        if (email.equals("") || password.equals("")) {
            validateFieldListener.onValidationFailure();
        } else {
            validateFieldListener.onValidationSuccess();
        }
    }

    @Override
    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
        if (call.isExecuted()) {
            switch (response.code()) {
                case 200:
                    LoginResponseModel model = response.body();
                    int responseCode = response.code();
                    listener.onAuthSuccess(model);
                    break;
                case 404:
                    listener.onAuthFailure("Status: 404");
                    break;
                case 401:
                    listener.onAuthFailure("Status: 401");
                    break;
                case 500:
                    listener.onAuthFailure("Status: 500");
                    break;
                case 403:
                    listener.onAuthFailure("Status: 403");
                    break;
                case 504:
                    listener.onAuthFailure("Status: 504");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onFailure(Call<LoginResponseModel> call, Throwable t) {
        listener.onAuthFailure("Check your internet connection");
    }
}
