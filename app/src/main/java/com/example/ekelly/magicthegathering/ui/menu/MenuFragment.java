package com.example.ekelly.magicthegathering.ui.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ekelly.magicthegathering.R;
import com.example.ekelly.magicthegathering.ui.base.BaseFragment;

import butterknife.ButterKnife;

public class MenuFragment extends BaseFragment {
    private View mView;

    public static MenuFragment newInstance() {
        return new MenuFragment();
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
        mView = inflater.inflate(R.layout.menu_fragment, container, false);
        ButterKnife.bind(this, mView);
        getBaseActivity().showTabLayout();
        return mView;
    }
}
