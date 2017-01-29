package com.example.alexander.shapespainter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexander.shapespainter.view.PainterCanvas;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //rotateScreen();//TODO::разобраться с поморотом телефона
        int width = this.getWindowManager().getDefaultDisplay().getWidth();
        setContentView (new PainterCanvas(this, width));

    }

    private void rotateScreen() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case R.id.dialog_exit:
                return createDialogExit();
            default:
                return super.onCreateDialog(id);
        }
    }

    private Dialog createDialogExit() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(R.string.exit_app_prompt);
        alert.setCancelable(false);
        alert.setTitle(R.string.exit_app_prompt_title);

        alert.setPositiveButton(R.string.yes,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        //ACTION_SAVE_AND_EXIT);
                    }
                });
        alert.setNegativeButton(R.string.no,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        return alert.create();
    }
}
