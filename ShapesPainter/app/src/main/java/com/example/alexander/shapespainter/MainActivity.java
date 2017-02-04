package com.example.alexander.shapespainter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alexander.shapespainter.view.PainterCanvas;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    PainterCanvas mySurfaceView;
    Vector<ImageButton> mImageButtons = new Vector<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySurfaceView = (PainterCanvas) findViewById(R.id.myView);
        initButtonToolbars();
        onClick();
    }

    private void onClick() {
        for (final ImageButton imageButton : mImageButtons) {
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int imageButtonId = imageButton.getId();
                    if (imageButtonId == R.id.imageButtonTriangle) {
                        Toast.makeText(getApplicationContext(),
                                "triangle",
                                Toast.LENGTH_SHORT).show();
                    } else if (imageButtonId == R.id.imageButtonCircle) {
                        Toast.makeText(getApplicationContext(),
                                "circle",
                                Toast.LENGTH_SHORT).show();
                    } else if (imageButtonId == R.id.imageButtonRectangle) {
                        Toast.makeText(getApplicationContext(),
                                "rectangle",
                                Toast.LENGTH_SHORT).show();
                    } else if (imageButtonId == R.id.imageButtonUndo) {
                        Toast.makeText(getApplicationContext(),
                                "undo",
                                Toast.LENGTH_SHORT).show();
                    } else if (imageButtonId == R.id.imageButtonRedo) {
                        Toast.makeText(getApplicationContext(),
                                "redo",
                                Toast.LENGTH_SHORT).show();
                    } else if (imageButtonId == R.id.imageButtonTrash) {
                        Toast.makeText(getApplicationContext(),
                                "trash",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void initButtonToolbars() {
        mImageButtons.add((ImageButton) findViewById(R.id.imageButtonTriangle));
        mImageButtons.add((ImageButton) findViewById(R.id.imageButtonCircle));
        mImageButtons.add((ImageButton) findViewById(R.id.imageButtonRectangle));
        mImageButtons.add((ImageButton) findViewById(R.id.imageButtonUndo));
        mImageButtons.add((ImageButton) findViewById(R.id.imageButtonRedo));
        mImageButtons.add((ImageButton) findViewById(R.id.imageButtonTrash));
    }
}
