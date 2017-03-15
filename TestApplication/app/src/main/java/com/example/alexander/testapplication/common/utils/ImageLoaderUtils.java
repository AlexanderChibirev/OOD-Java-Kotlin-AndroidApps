package com.example.alexander.testapplication.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.alexander.testapplication.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ImageLoaderUtils {
    private static final int THUMBNAIL_HEIGHT = 100;
    private static final int THUMBNAIL_WIDTH = 100;

    public static void downloadImage(Context context, String imageUrl, ImageView img) {
        Picasso.with(context)
                .load(imageUrl)
                .resize(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .error(R.drawable.ic_placeholder)
                .placeholder(R.drawable.ic_error_fallback)
                .into(img, new Callback() {
                    @Override
                    public void onSuccess() {
                        downloadFullImage(context, imageUrl, img);
                    }

                    @Override
                    public void onError() {
                        //Try again offline if cache failed
                        downloadFullImage(context, imageUrl, img);
                    }
                });
    }

    private static void downloadFullImage(Context context, String imageUrl, ImageView img) {
        Picasso.with(context)
                .load(imageUrl)
                .fit()
                .error(R.drawable.ic_placeholder)
                .placeholder(R.drawable.ic_error_fallback)
                .into(img, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
