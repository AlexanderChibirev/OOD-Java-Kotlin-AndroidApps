package com.example.alexander.testapplication.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.RecyclerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import static com.example.alexander.testapplication.R.id.rv;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @ViewById(rv)
    RecyclerView recyclerView;

    @ViewById(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    private SharedPreferences mSharedPreferences;
    private RecyclerAdapter mRecyclerAdapter;

    @AfterViews
    void init() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setSupportActionBar(toolbar);
        initRecyclerView();
        initRVAdapter();
        initBackdropImage();
        initSwipeRefreshLayout();
    }

    @OptionsItem(R.id.menu_preference)
    void onPreferencesClick() {
        showPreferences();
    }


    @Override
    public void onRefresh() {
        updateRV();
        //TODO::add refresh then you create rss model
    }

    @Override
    protected void onResume() {
        super.onResume();
        String rssFlow = mSharedPreferences.getString(
                getString(R.string.preference_address_rss_key), null);
        if (rssFlow != null) {
            //TODO:: set url rrs address
        }
    }

    private void updateRV() {
        swipeRefreshLayout.setRefreshing(true);
        mRecyclerAdapter.notifyDataSetChanged();
        //TODO::add parse rss url if you need
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

    private String getRssUrl() {
        return mSharedPreferences.getString(
                getString(R.string.preference_address_rss_key), null);
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
            Toast.makeText(
                    getApplicationContext(),
                    R.string.internet_connection_error,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initRVAdapter() {
        mRecyclerAdapter = new RecyclerAdapter((v, position) ->
                PreviewRssItemActivity_.intent(v.getContext()).start());
        recyclerView.setAdapter(mRecyclerAdapter);
    }

}
