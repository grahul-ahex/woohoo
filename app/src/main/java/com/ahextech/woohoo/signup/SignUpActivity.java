package com.ahextech.woohoo.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ahextech.woohoo.R;
import com.ahextech.woohoo.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements ChangeFragments, SignUpCompleteInterface {
    @BindView(R.id.frameLayout_container)
    FrameLayout container;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        initRegistrationPage();
    }

    private void initRegistrationPage() {
        transaction.replace(R.id.frameLayout_container, new RegistrationFragment());
        transaction.commit();
    }

    @Override
    public void loadFragments(String username, String password) {
        SignUpDetailsFragment detailsFragment = new SignUpDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("password", password);
        detailsFragment.setArguments(bundle);
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_container, detailsFragment);
        transaction.commit();

    }

    @Override
    public void startLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void signUpCompleted() {
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout_container, new SignUpCompleteFragment());
        transaction.commit();
    }
}
