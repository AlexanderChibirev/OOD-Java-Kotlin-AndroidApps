package com.example.alexander.testapplication.ui.activities;


import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexander.testapplication.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.List;


@EActivity(R.layout.activity_preview)
public class PreviewRssItemActivity extends AppCompatActivity {

    @ViewsById({
            R.id.date_text,
            R.id.title_text,
            R.id.description_text,
            R.id.author_text,
    })
    List<TextView> textViews;

    @ViewById(R.id.thumb_img)
    ImageView thumbnail;

    @AfterViews
    void init() {
        setTextViewWithRssDate();
        initImageView();
    }

    private void setTextViewWithRssDate() {
        for (TextView textView : textViews) {
            switch (textView.getId()) {
                case R.id.date_text:
                    textView.setText("data");
                    break;
                case R.id.title_text:
                    textView.setText("title");
                    textView.setOnClickListener(title -> WebViewActivity_.intent(this).
                            extra("link", "http://pmg.org.ru/nehe/").start());
                    break;
                case R.id.description_text:
                    textView.setText("description");
                    break;
                case R.id.author_text:
                    textView.setText("author");
                    break;
            }
        }
    }

    private void initImageView() {
        //TODO:: transfer picture from rv_activity with @extra picture annotation
        Picasso.with(thumbnail.getContext())
                .load("http://i.imgur.com/DvpvklR.png")
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error_fallback)
                .into(thumbnail);
    }
}
