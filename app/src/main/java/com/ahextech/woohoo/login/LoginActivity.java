package com.ahextech.woohoo.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahextech.woohoo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    @BindView(R.id.et_email_address)
    EditText etEmail;
    @BindView(R.id.et_login_password)
    EditText etPassword;
    @BindView(R.id.iv_no_view_password)
    ImageView ivShowPassword;
    @BindView(R.id.iv_view_password)
    ImageView ivHidePassword;
    @BindView(R.id.tv_forgot_details)
    TextView tvForgotDetails;
    @BindView(R.id.layout_btn_login)
    RelativeLayout loginButton;
    @BindView(R.id.tv_login_button_text)
    TextView tvLogin;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;

    private LoginPresenterImpl presenter;
    private String email, password;
    private ProgressDialog dialog;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            validateFields();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImpl(this);

        loginButton.setOnClickListener(this);
        ivHidePassword.setOnClickListener(this);
        ivShowPassword.setOnClickListener(this);
        tvForgotDetails.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        loginButton.setEnabled(false);
        etEmail.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
        validateFields();

    }

    @Override
    public void showLoginButton() {
        loginButton.setEnabled(true);
        tvLogin.setTextColor(getResources().getColor(R.color.LoginEnabledTextColor));

    }

    @Override
    public void hideLoginButton() {
        loginButton.setEnabled(false);
        tvLogin.setTextColor(getResources().getColor(R.color.LoginDisabledTextColor));
    }

    @Override
    public void showProgressDialog() {
        dialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
        dialog.setMessage("Authenticating.....");
        dialog.setCanceledOnTouchOutside(true);
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void hideProgressDialog() {
        dialog.hide();
    }

    @Override
    public void onSuccessfulLogin(String status) {
        Snackbar.make(etEmail, status, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showPassword() {

    }

    @Override
    public void hidePassword() {

    }

    @Override
    public void onLoginFailure(String status) {
        Snackbar.make(etEmail, status, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_btn_login:
                presenter.onLoginButtonClicked(email, password);
                break;
            case R.id.tv_forgot_details:
                Toast.makeText(this, "Forgot Details", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_sign_up:
                Toast.makeText(this, "Sign Up", Toast.LENGTH_SHORT).show();

                break;
            case R.id.iv_view_password:
                ivShowPassword.setVisibility(View.VISIBLE);
                ivHidePassword.setVisibility(View.GONE);
                Toast.makeText(this, "Show Password", Toast.LENGTH_SHORT).show();

                break;
            case R.id.iv_no_view_password:
                ivHidePassword.setVisibility(View.VISIBLE);
                ivShowPassword.setVisibility(View.GONE);

                Toast.makeText(this, "Hide Password", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private void validateFields() {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        presenter.validateLoginFields(email, password);
    }
}
