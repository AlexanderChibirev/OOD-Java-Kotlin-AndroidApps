package com.example.alexander.testapplication.ui.activities;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.controller.AppController;
import com.example.alexander.testapplication.controller.ReadRss;
import com.example.alexander.testapplication.model.FeedItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.rv)
    RecyclerView recyclerView;

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    private AppController mAppController;
    private ArrayList<FeedItem> mFeedItems = new ArrayList<>();

    @AfterViews
    void init() {
        mAppController = new AppController(this);
        setSupportActionBar(toolbar);
        initRecyclerView();
        initBackdropImage();
        initSwipeRefreshLayout();
        startAnimationLogotype();
    }


    private void startAnimationLogotype() {
        YoYo.with(Techniques.FadeIn).playOn(findViewById(R.id.omega_r_logotype));
    }

    @OptionsItem(R.id.menu_preference)
    void onPreferencesClick() {
        showPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAppController.isInternetConnection()) {
            if (mAppController.isChangeUrl()) {
                refreshRV();
            }
        } else {
            Toast.makeText(this, R.string.check_network_connection, Toast.LENGTH_SHORT).show();
        }
    }

    private void showPreferences() {
        PreferencesActivity_.intent(this).start();
    }

    private void initBackdropImage() {
        Glide.with(this).
                load(R.drawable.backdrop_title_bg).
                into((ImageView) findViewById(R.id.backdrop));
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (!mAppController.isUrlEmpty()) {
                swipeRefreshLayout.post(this::updateRV);
            }
        });
    }

    private void updateRV() {
        swipeRefreshLayout.setRefreshing(true);
        recyclerView.getAdapter().notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (!mAppController.isUrlEmpty()) {
            refreshRV();
        } else {
            Toast.makeText(this, R.string.rrs_url_empty, Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshRV() {
        ReadRss readRss = new ReadRss(
                getApplicationContext(),
                recyclerView,
                // "https://www.amazon.de/rss/movers-and-shakers/beauty?tag=bodyfun-21",
                //http://backend.deviantart.com/rss.xml? //TODO:: rss urls for example
                mAppController.getRssUrl(),
                swipeRefreshLayout,
                mFeedItems);
        readRss.execute();
    }
}
