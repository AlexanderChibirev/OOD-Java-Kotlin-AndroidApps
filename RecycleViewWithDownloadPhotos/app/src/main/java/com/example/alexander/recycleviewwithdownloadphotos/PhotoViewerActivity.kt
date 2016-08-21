package com.example.alexander.recycleviewwithdownloadphotos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*


class PhotoViewerActivity : AppCompatActivity() {
    private val jsonUrlPhotos = intent.getStringArrayListExtra("urlPhotos")
    private val jsonNamePhotos = intent.getStringArrayListExtra("namePhotos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_viewer)
        initViews();
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
