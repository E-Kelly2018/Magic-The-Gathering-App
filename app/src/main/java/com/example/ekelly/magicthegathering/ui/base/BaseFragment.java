package com.example.ekelly.magicthegathering.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;


public class BaseFragment extends Fragment {
    private BaseActivity mActivity;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = getActivity() != null ?  (BaseActivity) getActivity() : (BaseActivity) context;
            this.mActivity = activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public Bundle getBaseArguments() {
        return getArguments();
    }


    public BaseActivity getBaseActivity() {
        if(mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }


    public void onBackPressed() {
        mActivity.onBackPressed();
    }

    /**
     * Do nothing, fragment could override this method to get notifications on user interaction from baseActivity
     */
    public void onUserInteraction() {

    }

    public String tag() {
        return "";
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}