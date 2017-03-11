package com.example.alexander.testapplication.ui.activities;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.common.FeedLoader;
import com.example.alexander.testapplication.common.RSSResultReceiver;
import com.example.alexander.testapplication.common.utils.ToastMessageUtils;
import com.example.alexander.testapplication.controller.AppController;
import com.example.alexander.testapplication.model.FeedItem;
import com.example.alexander.testapplication.model.dataBase.DataUtils;
import com.example.alexander.testapplication.model.dataBase.FeedContentProvider;
import com.example.alexander.testapplication.ui.adapters.RecyclerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener
        , LoaderManager.LoaderCallbacks<Cursor> {


    @ViewById(R.id.rv)
    RecyclerView recyclerView;

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    Cursor cursor;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    SimpleCursorAdapter adapter;
    RSSResultReceiver receiver;
    private RecyclerAdapter mRecyclerAdapter;
    private AppController mAppController;
    private ArrayList<FeedItem> mFeedItems = new ArrayList<>();

    @AfterViews
    void init() {
        DataUtils.loadContentResolver(getContentResolver());

        int layoutID = android.R.layout.simple_list_item_1;
        String from[] = {
                FeedContentProvider.RSS_CHANNEL_TITLE,
        };
        int to[] = {
                android.R.id.text1
        };

        cursor = DataUtils.getAllChannels();
        startManagingCursor(cursor);
        adapter = new SimpleCursorAdapter(this, layoutID, cursor, from, to, 0);
        //DataUtils.loadContentResolver(getContentResolver());
       // cursor = DataUtils.getAllChannels();
        //startManagingCursor(cursor);


        //receiver = new RSSResultReceiver(new Handler(), this);





        mAppController = new AppController(this);
        //mAppController.updateRssData();
        setSupportActionBar(toolbar);

        initRecyclerView();
        initRVAdapter();
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
    public void onRefresh() {
        Intent intent = new Intent(getApplicationContext(), FeedLoader.class);
        intent.putExtra("url", mAppController.getRssUrl());
        intent.putExtra("receiver", receiver);
        startService(intent);
        updateRV();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRV();
    }

    private void updateRV() {
        swipeRefreshLayout.setRefreshing(true);
        mRecyclerAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
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
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (isInternetConnection()) {
            swipeRefreshLayout.post(this::updateRV);
        }
    }

    private boolean isInternetConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            ToastMessageUtils.showMessage(
                    getString(R.string.internet_connection_error),
                    getApplicationContext());
            return false;
        }
        return true;
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initRVAdapter() {
        mRecyclerAdapter = new RecyclerAdapter((v, position) -> PreviewRssItemActivity_.intent(v.getContext()).
                extra(FeedItem.class.getCanonicalName(), mFeedItems.get(position)).start(), mFeedItems);
        recyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
