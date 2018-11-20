package com.example.ekelly.magicthegathering.ui.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ekelly.magicthegathering.R;

import com.example.ekelly.magicthegathering.ui.card.CardFragment;
import com.example.ekelly.magicthegathering.ui.card.PageAdapter;
import com.example.ekelly.magicthegathering.ui.card_search.CardSearchFragment;
import com.example.ekelly.magicthegathering.ui.deck.DeckFragment;
import com.example.ekelly.magicthegathering.ui.game_tools.GameToolsFragment;
import com.example.ekelly.magicthegathering.ui.menu.MenuFragment;
import com.example.ekelly.magicthegathering.ui.signup.SignupFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public abstract class BaseActivity extends AppCompatActivity implements MvpView{
    public static final String MENU = "MENU";
    public static final String LOGIN_SIGNUP = "LOGIN_SIGNUP";
    public static final String CARD = "CARD";
    public static final String CARD_SEARCH = "CARD_SEARCH";
    public static final String DECK = "DECK";
    public static final String GAME_TOOLS = "GAME_TOOLS";


    public static boolean ADD_FRAGMENT_TO_BACKSTACK = true;
    public static boolean NOT_ADD_FRAGMENT_TO_BACKSTACK = false;

    private long mActivityId;
    public String mCurrentFragmentKey = MENU;
    private BaseFragment mFragment;
    private Bundle mBundle;

    //Nav Drawer
    @BindView(R.id.fragment_container) public FrameLayout mFragmentContainer;
    @BindView(R.id.layout_drawer) public DrawerLayout mDrawerLayout;
    @BindView(R.id.left_drawer) public NavigationView mNavView;
    @BindView(R.id.layout_main_content) public RelativeLayout mMainContent;
    @BindView(R.id.Toolbar) public Toolbar mToolbar;
    @BindView(R.id.home) public ImageView mivToolbarPrimary;
    @BindView(R.id.text_nav_cards) TextView mNavCards;
    @BindView(R.id.text_nav_decks) TextView mNavDecks;
    @BindView(R.id.text_nav_card_search) TextView mNavCardSearch;
    @BindView(R.id.text_nav_tools) TextView mNavTools;
    @BindView(R.id.text_nav_login) TextView mNavLogin;

    //TabLayout
    @BindView(R.id.tab_layout) TabLayout mTabLayout;
    @BindView(R.id.pager) ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        ButterKnife.bind(this);
        getTabLayout();
        initNavDrawer();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initNavDrawer() {
        //TODO This needs to be done dynamically
//        setPairedDeviceLabel("Tablet 1234");
//        setPairedDeviceStatus(getString(R.string.label_status_connected));
//        setLastSyncTimeLabel("2 Mins");

        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mNavView.setOutlineProvider(null);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float moveFactor = (mNavView.getWidth() * slideOffset);
                mMainContent.setTranslationX(moveFactor);
                mMainContent.setTranslationY(80f * slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    public void showNavDrawer() {
        closeSoftKeyboard();
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    protected void closeNavDrawer() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    public void closeSoftKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Lock Nav Drawer for Fragments that do not allow Nav Drawer access.
     */
    public void lockNavDrawer() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    /**
     * Unlock Nav Drawer for Fragments that allow Nav Drawer access.
     */
    public void unlockNavDrawer() {
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    /**
     * Hide Toolbar for Fragments that do not require it.
     */
    public void hideToolbar() {
        mToolbar.setVisibility(View.GONE);
    }

    /**
     * Show Toolbar when required.
     */
    public void showToolbar() {
        mToolbar.setVisibility(View.VISIBLE);
    }



    public FragmentTransaction getFragment(Bundle args, boolean addToBackStack, BaseFragment fragment,
            String fragmentKey) {
        return getFragmentAnim(args, addToBackStack, fragment, fragmentKey, android.R.anim.fade_in,
                android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
    }


    public FragmentTransaction getFragmentAnim(Bundle args, boolean addToBackStack, BaseFragment fragment,
            String fragmentKey, int animEnter, int animExit, int animBackEnter, int animBackExit) {
        mFragment = fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.setCustomAnimations(animEnter, animExit, animBackEnter, animBackExit);
        if (args != null) fragment.setArguments(args);
        ft.replace(R.id.fragment_container, fragment, fragmentKey);
        if ((addToBackStack) && (!getCurrentFragmentKey().equals(fragmentKey))) ft.addToBackStack(fragment.tag());
        setCurrentFragmentKey(fragmentKey);
        return ft;
    }

    public void getTabLayout(){
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 3"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 4"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 6"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 7"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 8"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 9"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 10"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 11"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 12"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 13"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 14"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 15"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 16"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 17"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hole 18"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PageAdapter adapter = new PageAdapter
                (getSupportFragmentManager(), mTabLayout
                        .getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void showTabLayout(){
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
    }

    public void hideTabLayout(){
        mTabLayout.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
    }



    /**
     * Load fragment based on Tag
     */
    public void loadFragment(Bundle args, String fragment, boolean addToBackStack) {
        switch (fragment) {
            case MENU:
                setCurrentFragmentKey(MENU);
                loadMenuFragment(args, addToBackStack);
                break;
            case CARD:
                setCurrentFragmentKey(CARD);
                loadCardFragment(args, addToBackStack);
                break;
            case CARD_SEARCH:
                setCurrentFragmentKey(CARD_SEARCH);
                loadCardSearchFragment(args, addToBackStack);
                break;
            case DECK:
                setCurrentFragmentKey(DECK);
                loadDeckFragment(args, addToBackStack);
                break;
            case LOGIN_SIGNUP:
                setCurrentFragmentKey(LOGIN_SIGNUP);
                loadLoginSignupFragment(args, addToBackStack);
                break;
            case GAME_TOOLS:
                setCurrentFragmentKey(GAME_TOOLS);
                loadGameToolsFragment(args, addToBackStack);
                break;
        }
    }

    /**
     * Load Menu Fragment
     *
     * @param args           Bundle
     * @param addToBackStack Boolean
     */
    public void loadMenuFragment(Bundle args, boolean addToBackStack) {
        getFragment(args, addToBackStack, new MenuFragment(), MENU).commit();
    }

    /**
     * Load Card Fragment
     *
     * @param args           Bundle
     * @param addToBackStack Boolean
     */
    public void loadCardFragment(Bundle args, boolean addToBackStack) {
        getFragment(args, addToBackStack, new CardFragment(), CARD).commit();
    }

    /**
     * Load Card Search Fragment
     *
     * @param args           Bundle
     * @param addToBackStack Boolean
     */
    public void loadCardSearchFragment(Bundle args, boolean addToBackStack) {
        getFragment(args, addToBackStack, new CardSearchFragment(), CARD_SEARCH).commit();
    }

    /**
     * Load Deck Fragment
     *
     * @param args           Bundle
     * @param addToBackStack Boolean
     */
    public void loadDeckFragment(Bundle args, boolean addToBackStack) {
        getFragment(args, addToBackStack, new DeckFragment(), DECK).commit();
    }

    /**
     * Load Login Signup Fragment
     *
     * @param args           Bundle
     * @param addToBackStack Boolean
     */
    public void loadLoginSignupFragment(Bundle args, boolean addToBackStack) {
        getFragment(args, addToBackStack, new SignupFragment(), LOGIN_SIGNUP).commit();
    }

    /**
     * Load Game Tools Fragment
     *
     * @param args           Bundle
     * @param addToBackStack Boolean
     */
    public void loadGameToolsFragment(Bundle args, boolean addToBackStack) {
        getFragment(args, addToBackStack, new GameToolsFragment(), GAME_TOOLS).commit();
    }



    /**
     * Get current fragment key
     *
     * @return String Current Fragment
     */
    @MainFragments
    public String getCurrentFragmentKey() {
        return mCurrentFragmentKey;
    }

    /**
     * Set current fragment
     */
    public void setCurrentFragmentKey(@MainFragments String fragment) {
        this.mCurrentFragmentKey = fragment;
    }

    /**
     * Definition of fragments supported
     */
    @StringDef({MENU, CARD, CARD_SEARCH, GAME_TOOLS, LOGIN_SIGNUP, DECK})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MainFragments {
    }


    /**
     * Get a fragment
     * @return fragment
     */
    public BaseFragment getFragment() {
        return mFragment;
    }
    /**
     * Track Bundle on BackPressed
     *
     * @return Bundle
     */
    public Bundle getBundle() {
        return mBundle;
    }

    public void setBundle(Bundle bundle) {
        mBundle = bundle;
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        if (getFragment() != null) {
            getFragment().onUserInteraction();
        }
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    public BaseActivity getBaseActivity() {
        return this;
    }

    public void setToolbarButton(int drawable){
        mivToolbarPrimary.setImageDrawable(getResources().getDrawable(drawable, getApplicationContext().getTheme()));
    }

    @OnClick(R.id.text_nav_cards)
    public void moveToCardFragment(){
        loadCardFragment(getBundle(), true);
        closeNavDrawer();
    }

    @OnClick(R.id.nav_logo)
    public void loadHomeFragment(){
        loadMenuFragment(getBundle(), true);
        closeNavDrawer();
    }
}
