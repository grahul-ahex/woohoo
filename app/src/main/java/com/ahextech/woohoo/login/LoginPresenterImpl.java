package com.ahextech.woohoo.login;

import com.ahextech.woohoo.POJO.LoginResponseModel;

/**
 * Created by ahextech on 7/3/18.
 */

public class LoginPresenterImpl implements LoginPresenterInterface, LoginInteractorImpl.onAuthCompletedListener, LoginInteractorImpl.onValidateFieldListener {
    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        interactor = new LoginInteractorImpl();
    }

    @Override
    public void onLoginButtonClicked(String email, String password) {
        view.showProgressDialog();
        interactor.authenticateUser(this, email, password);
    }

    @Override
    public void validateLoginFields(String email, String password) {
        interactor.validateFields(email, password, this);
    }

    @Override
    public void onAuthSuccess(LoginResponseModel model) {
        view.hideProgressDialog();
        String status = model.getStatus();
        view.onSuccessfulLogin(status);

    }

    @Override
    public void onAuthFailure(String status) {
        view.hideProgressDialog();
        view.onLoginFailure(status);
    }


    @Override
    public void onValidationSuccess() {
        view.showLoginButton();
    }

    @Override
    public void onValidationFailure() {
        view.hideLoginButton();
    }
}
