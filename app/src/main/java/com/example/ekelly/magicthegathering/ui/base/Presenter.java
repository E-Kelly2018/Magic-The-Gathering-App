package com.example.ekelly.magicthegathering.ui.base;

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}

