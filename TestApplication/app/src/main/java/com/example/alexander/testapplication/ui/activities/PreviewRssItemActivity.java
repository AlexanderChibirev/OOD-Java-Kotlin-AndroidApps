package com.example.alexander.testapplication.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.common.utils.DateUtils;
import com.example.alexander.testapplication.common.utils.ImageLoaderUtils;
import com.example.alexander.testapplication.model.FeedItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.List;


@EActivity(R.layout.activity_preview)
public class PreviewRssItemActivity extends AppCompatActivity {
    private FeedItem mFeedItem;
    public final static String LINK_EXTRA = "link";

    @ViewsById({
            R.id.date_text,
            R.id.title_button,
            R.id.description_text,
            R.id.author_text,
    })
    List<TextView> views;

    @ViewById(R.id.item_thumbnail)
    ImageView thumbnail;

    @AfterViews
    void init() {
        mFeedItem = getIntent().getParcelableExtra(
                FeedItem.class.getCanonicalName());
        initTextViewWithRssDate();
        initImageView();
    }


    private void initTextViewWithRssDate() {
        for (TextView view : views) {
            switch (view.getId()) {
                case R.id.date_text:
                    view.setText(DateUtils.getLocateDate(
                            mFeedItem.getPubDate(), this));
                    break;
                case R.id.title_button:
                    view.setText(mFeedItem.getTitle());
                    view.setOnClickListener(title -> WebViewActivity_.intent(this).
                            extra(LINK_EXTRA, mFeedItem.getLink()).start());
                    YoYo.with(Techniques.BounceInDown).playOn(view);
                    break;
                case R.id.description_text:
                    view.setText(mFeedItem.getDescription());
                    break;
                case R.id.author_text:
                    view.setText(mFeedItem.getAuthor());
                    break;
            }
        }
    }

    private void initImageView() {
        ImageLoaderUtils.downloadImage(
                thumbnail.getContext(),
                mFeedItem.getThumbnailUrl(),
                thumbnail);
    }
}
