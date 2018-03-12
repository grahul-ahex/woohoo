package com.ahextech.woohoo.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahextech.woohoo.R;
import com.ahextech.woohoo.ShowProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahextech on 8/3/18.
 */

public class SignUpDetailsFragment extends Fragment implements View.OnClickListener, MyInterface {
    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.btn_complete)
    Button btnCompleteReg;
    private String email, password, firstName, lastName;
    private SignUpDetailsPresenter signUpDetailsPresenter;
    private SignUpCompleteInterface signUpCompleteInterface;
    private ShowProgressDialog progressDialog;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            validateFields();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private boolean isRegistered;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        signUpDetailsPresenter = new SignUpDetailsPresenter(this);
        signUpCompleteInterface = (SignUpCompleteInterface) getContext();
        Bundle bundle = getArguments();
        if (bundle != null) {
            email = bundle.getString("username");
            password = bundle.getString("password");
        }
        etFirstName.addTextChangedListener(textWatcher);
        etLastName.addTextChangedListener(textWatcher);
        validateFields();
        btnCompleteReg.setOnClickListener(this);
        return view;
    }

    private void validateFields() {
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        if (firstName.equals("") || lastName.equals("")) {
            btnCompleteReg.setEnabled(false);
            btnCompleteReg.setTextColor(getResources().
                    getColor(R.color.LoginDisabledTextColor));
        } else {
            btnCompleteReg.setEnabled(true);
            btnCompleteReg.setTextColor(getResources().
                    getColor(R.color.LoginEnabledTextColor));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_complete:
                progressDialog = new ShowProgressDialog(getContext(), "Please wait");
                progressDialog.showDialog();
                signUpDetailsPresenter.registerUser(email, password, firstName + " " + lastName);
                break;
        }
    }

    @Override
    public void onRegistrationCompleted() {
        progressDialog.hideDialog();
        signUpCompleteInterface.signUpCompleted();
    }

    @Override
    public void onRegistrationFailed() {
        progressDialog.hideDialog();
        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

    }
}
