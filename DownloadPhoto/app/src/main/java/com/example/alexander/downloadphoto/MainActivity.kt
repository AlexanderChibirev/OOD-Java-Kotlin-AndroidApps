package com.example.alexander.downloadphoto

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    var urlName: EditText? = null
    var btnGetDataFromJsonFile: Button? = null
    var networkDAO = NetworkDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        urlName = findViewById(R.id.urlName) as EditText
        btnGetDataFromJsonFile =  findViewById(R.id.getDataFromJsonFile) as Button
        btnGetDataFromJsonFile!!.setOnClickListener {
            getDateFromJsonFile()
        }
    }


    private fun getDateFromJsonFile(): ArrayList<PhotoData> {
        val urlNameString = urlName!!.getText().toString()
        val request = networkDAO.request(urlNameString)
        val allPhoto = ArrayList<PhotoData>()
        var root = JSONObject(request)
        val photos = root.getJSONArray("arrayPhotos")
        for(i in 0..photos.length() - 1) {
            val jsonPhotos = photos.getJSONObject(i)
            val urlPhotos= jsonPhotos.getString("url")
            val namePhotos =  jsonPhotos.getString("name")
            allPhoto.add(PhotoData(namePhotos,urlPhotos))
        }
        return allPhoto
    }

    private fun goToPhotoViewerActivity(){
        val intent = Intent(this, PhotoViewerActivity::class.java)
        intent.putExtra("fname", "")
        startActivity(intent)
    }

}

