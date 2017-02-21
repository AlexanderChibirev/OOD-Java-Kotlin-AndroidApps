package com.example.alexander.shapespaintermvp.mvp.common;

import android.content.Context;

import com.example.alexander.shapespaintermvp.mvp.models.IShape;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeFactory;
import com.example.alexander.shapespaintermvp.mvp.models.ShapeType;
import com.example.alexander.shapespaintermvp.mvp.models.ShapesList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.vecmath.Vector2f;

import static android.content.Context.MODE_PRIVATE;

public class FileSystemUtils {
    private final static String FILENAME = "DateShapes.json";

    public static void saveFileWithStateShapes(ShapesList shapesList, Context context) {
        try {
            BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(context.openFileOutput(FILENAME, MODE_PRIVATE))));
            writeDataShapeInFile(shapesList, bw);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFileWithStateShapes(ShapesList shapesList, Context context, ShapeFactory shapeFactory) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(FILENAME)));
            parseJson(shapesList, br, shapeFactory);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static void writeDataShapeInFile(ShapesList shapesList, BufferedWriter bw) throws IOException {
        JSONObject shapeData = new JSONObject();
        for (IShape shape : shapesList.getShapes()) {

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

    private static void parseJson(ShapesList shapesList, BufferedReader br, ShapeFactory shapeFactory) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        String str;
        while ((str = br.readLine()) != null) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(str);
            String shapeType = (String) jsonObject.get("shapeType");

            float x = (float) (double) jsonObject.get("x");
            float y = (float) (double) jsonObject.get("y");

            float width = (float) (double) jsonObject.get("width");
            float height = (float) (double) jsonObject.get("height");

            shapesList.addShape(shapeFactory.createShape(new Vector2f(x, y), width, height, ShapeType.valueOf(shapeType)));
        }
    }
}
