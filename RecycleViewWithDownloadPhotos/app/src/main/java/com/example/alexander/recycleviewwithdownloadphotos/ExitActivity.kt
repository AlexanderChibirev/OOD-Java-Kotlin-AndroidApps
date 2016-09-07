package com.example.alexander.recycleviewwithdownloadphotos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import java.io.File

class ExitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exit)
        val buttonYes = findViewById(R.id.buttonYes) as Button
        val buttonNo = findViewById(R.id.buttonNo) as Button
        buttonYes.setOnClickListener {
            val file = File("sdcard/photoalbum/")
            if(deleteDirectory(file)) setToats("Фотографии удалены")
            else setToats("Произошла ошибка, попробуйте еще раз")
            exitActivity()
        }

        buttonNo.setOnClickListener {
            setToats("Фотографии успешно сохранились на sd карте")
            exitActivity()
        }
    }

    private fun exitActivity(){
        finish();
        System.exit(0);
    }

    private fun setToats(message: String){
        val toast = Toast.makeText(applicationContext,
                message, Toast.LENGTH_SHORT).
                show()
    }


    private fun deleteDirectory(directory: File): Boolean {
        if (directory.exists()) {
            val files = directory.listFiles()
            if (null != files) {
                for (i in files.indices) {
                    if (files[i].isDirectory) {
                        deleteDirectory(files[i])
                    } else {
                        files[i].delete()
                    }
                }
            }
        }
        return directory.delete()
    }

}
