package com.example.alexander.testapplication.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.alexander.testapplication.R;
import com.example.alexander.testapplication.common.utils.DateUtils;
import com.example.alexander.testapplication.model.FeedItem;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.List;


@EActivity(R.layout.activity_preview)
public class PreviewRssItemActivity extends AppCompatActivity {
    private FeedItem mFeedItem;

    @ViewsById({
            R.id.date_text,
            R.id.title_text,
            R.id.description_text,
            R.id.author_text,
    })
    List<TextView> textViews;

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
        for (TextView textView : textViews) {
            switch (textView.getId()) {
                case R.id.date_text:
                  /*  try { //TODO:: add testing in different languages
                        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
                        Date pubDate = dateFormat.parse(mFeedItem.getPubDate());
                        textView.setText(DateUtils.getDateDifference(pubDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }*/
                    textView.setText(DateUtils.getLocateDate(
                            mFeedItem.getPubDate(),
                            getApplicationContext()));
                    break;
                case R.id.title_text:
                    textView.setText(mFeedItem.getTitle());
                    textView.setOnClickListener(title -> WebViewActivity_.intent(this).
                            extra("link", mFeedItem.getLink()).start());
                    YoYo.with(Techniques.BounceInDown).playOn(textView);
                    break;
                case R.id.description_text:
                    textView.setText(mFeedItem.getDescription());
                    break;
                case R.id.author_text:
                    textView.setText(mFeedItem.getAuthor());
                    break;
            }
        }
    }

    private void initImageView() {
        Picasso.with(thumbnail.getContext())
                .load(mFeedItem.getThumbnailUrl())
                .fit()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error_fallback)
                .into(thumbnail);
    }
}
