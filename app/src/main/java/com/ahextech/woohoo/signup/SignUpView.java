package com.ahextech.woohoo.signup;

/**
 * Created by ahextech on 8/3/18.
 */

public interface SignUpView {
    void showUserNameError(String username);

    void onValidUserName();

    void showProgressDialog();

    void showConnectionErrorMsg(String status);
}
