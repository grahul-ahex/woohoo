package com.ahextech.woohoo.signup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ahextech.woohoo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahextech on 8/3/18.
 */

public class RegistrationFragment extends Fragment {
    private Context context;
    @BindView(R.id.username_layout)
    private LinearLayout userNameLayout;
    @BindView(R.id.et_username)
    private EditText etUserName;
    @BindView(R.id.password_layout)
    private LinearLayout passwordLayout;
    @BindView(R.id.et_password)
    private EditText etCreatePswd;
    @BindView(R.id.iv_show_password)
    private ImageView ivShowPassword;
    @BindView(R.id.tv_username_error)
    private TextView tvUserNameError;
    @BindView(R.id.layout_btn_next)
    private RelativeLayout btnNext;
    @BindView(R.id.tv_btn_text)
    private TextView tvButtonText;
    @BindView(R.id.tv_log_in)
    private TextView tvLogIn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this, view);
        this.context = getContext();
        return view;
    }
}
