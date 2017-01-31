package com.example.alexander.shapespainter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.alexander.shapespainter.view.PainterCanvas;

public class MainActivity extends AppCompatActivity {
    PainterCanvas mySurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySurfaceView  = (PainterCanvas)findViewById(R.id.myview);


        GridView gridview = (GridView) findViewById(R.id.redo_undo_trash_tools);
        gridview.setAdapter(new ImageToolsAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                switch(position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });
    }


}
