package com.ahextech.woohoo.homescreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ahextech.woohoo.R;
import com.ahextech.woohoo.sharefpref.SessionManager;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ahextech.woohoo.sharefpref.SharefPrefKeys.KEY_EMAIL;
import static com.ahextech.woohoo.sharefpref.SharefPrefKeys.KEY_PASSWORD;

public class HomeScreenActivity extends AppCompatActivity implements
        View.OnClickListener, HomeScreenView {
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.btn_log_out)
    Button btnLogOut;
    private HomeScreenPresenterImpl homeScreenPresenter;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        homeScreenPresenter = new HomeScreenPresenterImpl(this);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> userDetails = sessionManager.getUserDetails();
        tvEmail.setText(userDetails.get(KEY_EMAIL));
        tvPassword.setText(userDetails.get(KEY_PASSWORD));
        btnLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_log_out) {
            sessionManager.logoutUser();


        }
    }

    @Override
    public void onLogOutClicked() {

    }
}
