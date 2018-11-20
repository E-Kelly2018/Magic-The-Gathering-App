package com.example.ekelly.magicthegathering.ui.signup;

import com.example.ekelly.magicthegathering.ui.base.BasePresenter;



public class SignupPresenter extends BasePresenter<SignupView> {

    private SignupView mView;


    public SignupPresenter(){ }

    @Override
    public void attachView(SignupView view) {
        super.attachView(view);
        mView = view;
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}

