package com.example.alexander.shapespainter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.alexander.shapespainter.controller.Controller;
import com.example.alexander.shapespainter.view.PainterCanvas;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private Vector<ImageButton> mImageButtons = new Vector<>();
    private Controller mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mController = new Controller(getApplicationContext());
        mController.readFileWithStateShape();
        PainterCanvas mPainterCanvas = (PainterCanvas) findViewById(R.id.myView);
        mPainterCanvas.setController(mController);
        initButtonToolbars();
        onClick();
    }

    @Override
    public void onBackPressed() {
        createDialogExit();
    }

    private void onClick() {
        for (final ImageButton imageButton : mImageButtons) {
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int imageButtonId = imageButton.getId();
                    if (imageButtonId == R.id.imageButtonTriangle) {
                        mController.addTriangle();
                    } else if (imageButtonId == R.id.imageButtonCircle) {
                        mController.addEllipse();
                    } else if (imageButtonId == R.id.imageButtonRectangle) {
                        mController.addRectangle();
                    } else if (imageButtonId == R.id.imageButtonUndo) {
                        mController.undoCommand();
                    } else if (imageButtonId == R.id.imageButtonRedo) {
                        mController.redoCommand();
                    } else if (imageButtonId == R.id.imageButtonTrash) {
                        mController.deleteSelectedShape();
                    }
                }
            });
        }
    }

    private void createDialogExit() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainActivity.this);
        quitDialog.setTitle(R.string.exit_app_prompt);

        quitDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mController.saveStateShape();
                System.exit(0);
            }
        });

        quitDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        quitDialog.show();
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
