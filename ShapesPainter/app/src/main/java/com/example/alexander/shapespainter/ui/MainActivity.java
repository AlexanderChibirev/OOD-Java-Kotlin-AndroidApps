package com.example.alexander.shapespainter.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import com.example.alexander.shapespainter.R;
import com.example.alexander.shapespainter.controller.Controller;
import com.example.alexander.shapespainter.model.ShapeType;
import com.example.alexander.shapespainter.view.PainterCanvas;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Controller mController;

    @BindView(R.id.myView)
    PainterCanvas mPainterCanvas;
    @BindView(R.id.imageButtonCircle)
    ImageButton imageButtonCircle;
    @BindView(R.id.imageButtonRectangle)
    ImageButton imageButtonRectangle;
    @BindView(R.id.imageButtonTriangle)
    ImageButton imageButtonTriangle;
    @BindView(R.id.imageButtonUndo)
    ImageButton imageButtonUndo;
    @BindView(R.id.imageButtonRedo)
    ImageButton imageButtonRedo;
    @BindView(R.id.imageButtonTrash)
    ImageButton imageButtonTrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mController = new Controller(getApplicationContext());
        mController.readFileWithStateShape();
        mPainterCanvas.setController(mController);
        
        initOnClickListenerImageButton();
    }

    @Override
    public void onBackPressed() {
        createDialogExit();
    }

    private void initOnClickListenerImageButton() {
        imageButtonCircle.setOnClickListener(view -> mController.addShape(ShapeType.Ellipse));
        imageButtonRectangle.setOnClickListener(view -> mController.addShape(ShapeType.Rectangle));
        imageButtonTriangle.setOnClickListener(view -> mController.addShape(ShapeType.Triangle));
        imageButtonUndo.setOnClickListener(view -> mController.undoCommand());
        imageButtonRedo.setOnClickListener(view -> mController.redoCommand());
        imageButtonTrash.setOnClickListener(view -> mController.deleteSelectedShape());
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
}
