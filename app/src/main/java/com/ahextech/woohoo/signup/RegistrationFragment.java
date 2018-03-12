package com.ahextech.woohoo.signup;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahextech.woohoo.R;
import com.ahextech.woohoo.ShowProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahextech on 8/3/18.
 */

public class RegistrationFragment extends Fragment implements SignUpView, View.OnClickListener, View.OnTouchListener {
    @BindView(R.id.username_layout)
    LinearLayout userNameLayout;
    @BindView(R.id.et_username)
    EditText etUserName;
    @BindView(R.id.password_layout)
    LinearLayout passwordLayout;
    @BindView(R.id.et_password)
    EditText etCreatePwd;
    @BindView(R.id.iv_show_password)
    ImageView ivShowPassword;
    @BindView(R.id.tv_username_error)
    TextView tvUserNameError;
    @BindView(R.id.layout_btn_next)
    RelativeLayout btnNext;
    @BindView(R.id.tv_btn_text)
    TextView tvButtonText;
    @BindView(R.id.tv_log_in)
    TextView tvLogIn;
    @BindView(R.id.tv_choose_username)
    TextView tvPageTitle;

    @BindView(R.id.btn_proceed)
    Button btnProceed;
    private Context context;
    private String email, username, password;
    private SignUpPresenterImpl signUpPresenter;
    private ChangeFragments myInterface;
    private ShowProgressDialog showProgressDialog;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tvUserNameError.setVisibility(View.INVISIBLE);
            userNameLayout.setBackground(getResources()
                    .getDrawable(R.drawable.pwd_creation_bg));
        }

        @Override
        public void afterTextChanged(Editable s) {
            validateUserName();
            validatePassword();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this, view);
        myInterface = (ChangeFragments) getContext();
        this.context = getContext();
        signUpPresenter = new SignUpPresenterImpl(this);
        etUserName.addTextChangedListener(textWatcher);
        etCreatePwd.addTextChangedListener(textWatcher);
        tvLogIn.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnProceed.setOnClickListener(this);
        ivShowPassword.setOnTouchListener(this);
        validateUserName();
        validatePassword();
        return view;
    }

    private void validatePassword() {
        password = etCreatePwd.getText().toString();
        if (password.equals("")) {
            btnProceed.setEnabled(false);
            btnProceed.setTextColor(getResources()
                    .getColor(R.color.LoginDisabledTextColor));
        } else {
            btnProceed.setEnabled(true);
            btnProceed.setTextColor(getResources()
                    .getColor(R.color.LoginEnabledTextColor));

        }
    }

    private void validateUserName() {
        username = etUserName.getText().toString();
        if (username.equals("")) {
            btnNext.setEnabled(false);
            tvButtonText.setTextColor(getResources().getColor(R.color.LoginDisabledTextColor));
        } else {
            btnNext.setEnabled(true);
            tvButtonText.setTextColor(getResources().getColor(R.color.LoginEnabledTextColor));

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void showUserNameError(String username) {
        showProgressDialog.hideDialog();
        String errorMessage = username + " " + getResources().getString(R.string.username_error);
        tvUserNameError.setText(errorMessage);
        tvUserNameError.setVisibility(View.VISIBLE);
        userNameLayout
                .setBackground(getResources().getDrawable(R.drawable.username_background));
        btnNext.setEnabled(false);
        tvButtonText.setTextColor(getResources().getColor(R.color.LoginDisabledTextColor));
    }

    @Override
    public void onValidUserName() {
        showProgressDialog.hideDialog();
        tvUserNameError.setVisibility(View.INVISIBLE);
        passwordLayout.setVisibility(View.VISIBLE);
        userNameLayout.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);
        btnProceed.setVisibility(View.VISIBLE);
        tvPageTitle.setText(getResources().getText(R.string.create_password));

    }

    @Override
    public void showProgressDialog() {
        showProgressDialog = new ShowProgressDialog(getContext(), "Please wait");
        showProgressDialog.showDialog();
    }


    @Override
    public void showConnectionErrorMsg(String status) {
        Snackbar.make(tvLogIn, status, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_log_in:
                myInterface.startLoginActivity();
                break;
            case R.id.layout_btn_next:
                email = username + "@woohoo.com";
                signUpPresenter.validateUserName(email);
                break;
            case R.id.btn_proceed:
                if (password.length() < 8) {
                    Toast.makeText(context, "Password should be of min 8 characters", Toast.LENGTH_SHORT).show();
                } else {
                    myInterface.loadFragments(email, password);

                }

                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                etCreatePwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                etCreatePwd.moveCursorToVisibleOffset();
                etCreatePwd.setSelection(etCreatePwd.getText().toString().length());
                break;
            case MotionEvent.ACTION_UP:
                etCreatePwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
        }
        return true;
    }
}
