package com.example.alexander.downloadphoto

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    var urlName: EditText? = null
    var btnGetDataFromJsonFile: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        urlName = findViewById(R.id.urlName) as EditText
        btnGetDataFromJsonFile =  findViewById(R.id.getDataFromJsonFile) as Button
        btnGetDataFromJsonFile!!.setOnClickListener {
            getDateFromJsonFile()
        }
    }


    private fun getDateFromJsonFile() {
        val urlNameString = urlName!!.getText().toString()
    }

    private fun goToPhotoViewerActivity(){
        val intent = Intent(this, PhotoViewerActivity::class.java)
        intent.putExtra("fname", "")
        startActivity(intent)
    }

}

