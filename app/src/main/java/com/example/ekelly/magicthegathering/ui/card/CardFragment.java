package com.example.ekelly.magicthegathering.ui.card;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.ekelly.magicthegathering.R;
import com.example.ekelly.magicthegathering.ui.base.BaseFragment;
import com.example.ekelly.magicthegathering.ui.signup.SignupFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardFragment extends BaseFragment {

    private View mView;
    @BindView(R.id.editText2) EditText edit;
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
        mView = inflater.inflate(R.layout.tab1_fragment, container, false);
        ButterKnife.bind(this, mView);

        return mView;
    }
}
