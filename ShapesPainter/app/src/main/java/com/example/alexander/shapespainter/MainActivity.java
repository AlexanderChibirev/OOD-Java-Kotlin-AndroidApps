package com.example.alexander.shapespainter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new PainterCanvas(this));
    }

    public Bitmap getLastPicture() {
        Bitmap savedBitmap = null;

       /* if (!mOpenLastFile && mSettings.forceOpenFile) {
            mSettings.lastPicture = null;
            mIsNewFile = true;
            return savedBitmap;
        }

        mSettings.forceOpenFile = false;

        if (mSettings.lastPicture != null) {
            if (new File(mSettings.lastPicture).exists()) {
                savedBitmap = BitmapFactory.decodeFile(mSettings.lastPicture);
                mIsNewFile = false;
            } else {
                mSettings.lastPicture = null;
            }
        }*/

        return savedBitmap;
    }
}
