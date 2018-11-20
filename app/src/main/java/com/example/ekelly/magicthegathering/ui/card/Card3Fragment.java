package com.example.ekelly.magicthegathering.ui.card;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ekelly.magicthegathering.R;
import com.example.ekelly.magicthegathering.ui.base.BaseFragment;

public class Card3Fragment extends BaseFragment {
    private View mView;

    public static CardFragment newInstance() {
        return new CardFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tab3, container, false);
        return mView;
    }
}
