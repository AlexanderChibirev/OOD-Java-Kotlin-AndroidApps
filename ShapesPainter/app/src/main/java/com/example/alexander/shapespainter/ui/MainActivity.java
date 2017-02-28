package com.example.alexander.shapespainter.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.alexander.shapespainter.R;
import com.example.alexander.shapespainter.controller.Controller;
import com.example.alexander.shapespainter.model.ShapeType;
import com.example.alexander.shapespainter.view.PainterCanvas;

import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Vector<ImageButton> mImageButtons = new Vector<>();
    private Controller mController;

    @BindView(R.id.myView)
    PainterCanvas mPainterCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mController = new Controller(getApplicationContext());
        mController.readFileWithStateShape();
        mPainterCanvas.setController(mController);
        initButtonToolbars();
        initOnClickListenerImageButton();
    }

    @Override
    public void onBackPressed() {
        createDialogExit();
    }

    private void initOnClickListenerImageButton() {
        for (final ImageButton imageButton : mImageButtons) {
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int imageButtonId = imageButton.getId();
                    mPainterCanvas.freezePainterThread();
                    switch (imageButtonId) {
                        case R.id.imageButtonTriangle:
                            mController.addShape(ShapeType.Triangle);
                            break;
                        case R.id.imageButtonCircle:
                            mController.addShape(ShapeType.Ellipse);
                            break;
                        case R.id.imageButtonRectangle:
                            mController.addShape(ShapeType.Rectangle);
                            break;
                        case R.id.imageButtonUndo:
                            mController.undoCommand();
                            break;
                        case R.id.imageButtonRedo:
                            mController.redoCommand();
                            break;
                        case R.id.imageButtonTrash:
                            mController.deleteSelectedShape();
                            break;
                    }
                    mPainterCanvas.activatePainterThread();
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
