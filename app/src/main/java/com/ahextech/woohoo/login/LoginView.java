package com.ahextech.woohoo.login;

/**
 * Created by ahextech on 7/3/18.
 */

public interface LoginView {


    void showLoginButton();
    void hideLoginButton();

    void showProgressDialog();

    void hideProgressDialog();

    void onSuccessfulLogin(String status);

    void showPassword();

    void hidePassword();


    void onLoginFailure(String status);
}
