package com.example.alexander.recycleviewwithdownloadphotos

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import java.util.*


class PhotoViewerActivity : AppCompatActivity() {

    private lateinit var jsonNamePhotos: ArrayList<String>
    private lateinit var jsonUrlPhotos: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_viewer)
        jsonUrlPhotos = intent.getStringArrayListExtra("urlPhotos")
        jsonNamePhotos = intent.getStringArrayListExtra("namePhotos")
        initViews();

        val battonExit =  findViewById(R.id.buttonExit) as Button
        battonExit.setOnClickListener {
            startActivity(Intent(applicationContext,ExitActivity::class.java))
            super.finish()
        }
    }

    private fun initViews() {
        val recyclerView = findViewById(R.id.card_recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setLayoutManager(layoutManager)

        val androidVersions = prepareData()
        val adapter = DataAdapter(applicationContext, androidVersions)
        recyclerView.setAdapter(adapter)
    }

    private fun prepareData(): ArrayList<PhotoData> {
        val arrayData = ArrayList<PhotoData>()
        for (i in 0..jsonNamePhotos.size - 1) {
            arrayData.add(PhotoData(jsonNamePhotos[i],jsonUrlPhotos[i]))
        }
        return arrayData
    }
}
