package com.example.alexander.shapespainter.utils;

import android.os.Environment;

import com.example.alexander.shapespainter.R;
import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapesList;

import java.io.File;

public class FileSystem {
    private static final String FILE_SAVE = "";

    public static boolean saveFileWithShapes(ShapesList shapesList) {
        //FileOutputStream fos = new FileOutputStream(FILE_SAVE);
        for (Shape shape : shapesList.getShapes()) {
            shape.getSize();
            shape.getCenter();
        }
        return false;
    }

    public static boolean openFileWithShapes() {
        return false;
    }


    private String getSaveDir() {
        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + '/' + (R.string.app_name) + '/';

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }
}
