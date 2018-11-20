package com.example.ekelly.magicthegathering.ui.signup;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.ekelly.magicthegathering.R;
import com.example.ekelly.magicthegathering.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupFragment extends BaseFragment {

    @BindView(R.id.et_password) EditText mPassword;
    @BindView(R.id.et_username) EditText musername;
    @BindView(R.id.picker_score) NumberPicker score;
    @BindView(R.id.et_password_signup) EditText mCreatePassword;
    @BindView(R.id.et_username_signup) EditText mCreateUsername;
    @BindView(R.id.login_btn) Button mLogin;
    @BindView(R.id.text_create_account) TextView mCreateAccount;

    private View mView;

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    @Override
    public void onCreate(final Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_signup, container, false);
        ButterKnife.bind(this, mView);
        getBaseActivity().hideTabLayout();

        score.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        score.setMaxValue(10);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        score.setWrapSelectorWheel(true);

        getBaseActivity().mivToolbarPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().showNavDrawer();
            }
        });
        return mView;
    }

    @OnClick(R.id.text_create_account)
    public void creatAccount(){
        mLogin.setText("Signup");
        musername.setVisibility(View.GONE);
        mPassword.setVisibility(View.GONE);
        mCreateUsername.setVisibility(View.VISIBLE);
        mCreatePassword.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.login_btn)
    public void login(){
        if (mLogin.getText().toString().equals("Button")){
            Log.i("BTN_TEXT", "Hello");
        }else if (mLogin.getText().toString().equals("Signup")){
            Log.i("BTN_TEXT", "World");
        }
        getBaseActivity().loadMenuFragment(getBaseArguments(), false);
    }

    @Override
    public String tag() {
        return getBaseActivity().LOGIN_SIGNUP;
    }




}
