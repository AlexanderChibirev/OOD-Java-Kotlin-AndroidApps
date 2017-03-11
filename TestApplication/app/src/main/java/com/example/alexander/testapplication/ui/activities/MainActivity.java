package com.example.alexander.testapplication.ui.activities;


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
public class MainActivity extends AppCompatActivity{

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
        //mAppController.updateRssData();
        setSupportActionBar(toolbar);
        initRecyclerView();
        initBackdropImage();
        initSwipeRefreshLayout();
        startAnimationLogotype();

       /* recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                       // mFeedItems.get(position);
                             // PreviewRssItemActivity_.
                              //        intent(getApplicationContext())
                             //          .extra(FeedItem.class.getCanonicalName(), mFeedItems.get(position)).start();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );*/
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
        if (!mAppController.canUpdateRV()) {
            ReadRss readRss = new ReadRss(
                    getApplicationContext(),
                    recyclerView,
                    "https://www.amazon.de/rss/movers-and-shakers/beauty?tag=bodyfun-21",
                    // mAppController.getRssUrl(),
                    swipeRefreshLayout,
                    mFeedItems);
            readRss.execute();
        }
    }
    //если настройки изменились(проверяем в контроллере),
    // то обновляем в readrss заполняем либо через парсер либо через базу данных


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
            if (mAppController.canUpdateRV()) {
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
    }
}
