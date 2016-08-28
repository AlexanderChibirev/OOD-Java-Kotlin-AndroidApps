package com.example.alexander.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ListView mLvImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLvImages = (ListView) findViewById(R.id.lvImages);


        File dir = new File(Environment.getExternalStorageDirectory(), "Download/L0161");
        File[] filesArray = dir.listFiles();

        if (filesArray != null) {
            ImageAdapter adapter = new ImageAdapter(this, filesArray);
            mLvImages.setAdapter(adapter);
        }

    }


    static class ImageAdapter extends ArrayAdapter<File> {

        LayoutInflater mInflater;
        int mSize;

        public ImageAdapter(Context context, File[] objects) {
            super(context, R.layout.list_item, objects);
            mInflater = LayoutInflater.from(context);
            mSize = context.getResources().getDimensionPixelSize(R.dimen.image_size);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = mInflater.inflate(R.layout.list_item, parent, false);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Bitmap bitmap = getBitmap(position);
            imageView.setImageBitmap(bitmap);
            return view;
        }

        private Bitmap getBitmap(int position) {
            String filePath = getItem(position).getAbsolutePath();
            return Utils.decodeSampledBitmapFromResource(filePath, mSize, mSize);
        }


    }
}
