package com.example.alexander.shapespainter.utils;

import android.os.Environment;
import android.util.Log;

import com.example.alexander.shapespainter.model.Shape;
import com.example.alexander.shapespainter.model.ShapeFactory;
import com.example.alexander.shapespainter.model.ShapeType;
import com.example.alexander.shapespainter.model.ShapesList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.vecmath.Vector2f;

public class FileSystem {
    private final static String LOG_TAG = "Error: ";
    private final static String DIR_SD = "ShapePainter";
    private final static String FILENAME_SD = "DateShapes.json";

    public static boolean saveFileWithShapes(ShapesList shapesList) {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return false;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // создаем каталог

        boolean isDirectoryCreated = sdPath.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = sdPath.mkdir();
        }
        if (isDirectoryCreated) {
            File sdFile = new File(sdPath, FILENAME_SD);
            try {
                // открываем поток для записи
                BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
                // пишем данные
                writeDataShapeInFile(shapesList, bw);
                // закрываем поток
                bw.close();
                Log.d(LOG_TAG, "Файл записан на SD: " + sdFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static void writeDataShapeInFile(ShapesList shapesList, BufferedWriter bw) throws IOException {

        JSONObject shapeData = new JSONObject();
        for (Shape shape : shapesList.getShapes()) {

            shapeData.put("shapeType", shape.getType().toString());

            Vector2f size = shape.getSize();
            shapeData.put("width", size.x);
            shapeData.put("height", size.y);

            Vector2f center = shape.getCenter();
            shapeData.put("x", center.x);
            shapeData.put("y", center.y);

            bw.write(shapeData.toString());
            bw.newLine();
        }
    }


    public static boolean readFileSD(ShapesList shapesList) throws IOException {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
            return false;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAME_SD);
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            parseJson(shapesList, br);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static void parseJson(ShapesList shapesList, BufferedReader br) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        String str;
        ShapeFactory shapeFactory = new ShapeFactory();
        while ((str = br.readLine()) != null) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(str);
            String shapeType = (String) jsonObject.get("shapeType");

            float x = (float) (double) jsonObject.get("x");
            float y = (float) (double) jsonObject.get("y");

            float width = (float) (double) jsonObject.get("width");
            float height = (float) (double) jsonObject.get("height");

            switch (ShapeType.valueOf(shapeType)) {
                case Triangle:
                    shapesList.addShape(shapeFactory.createShape(new Vector2f(x, y), width, height, ShapeType.Triangle));
                    break;
                case Ellipse:
                    shapesList.addShape(shapeFactory.createShape(new Vector2f(x, y), width, height, ShapeType.Ellipse));
                    break;
                case Rectangle:
                    shapesList.addShape(shapeFactory.createShape(new Vector2f(x, y), width, height, ShapeType.Rectangle));
                    break;
            }
        }
    }
}
