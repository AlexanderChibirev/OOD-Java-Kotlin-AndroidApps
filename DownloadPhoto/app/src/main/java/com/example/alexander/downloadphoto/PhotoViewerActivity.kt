package com.example.alexander.downloadphoto

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*

class PhotoViewerActivity : AppCompatActivity() {

    private var rv: RecyclerView? = null
    private val photos = ArrayList<PhotoData>()
    private val jsonPhotos = intent.getStringArrayExtra("allPhoto")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_viewer)
        rv = findViewById(R.id.rv) as RecyclerView//возможно тут ошибка// rv = сокращенно RecyclerView

        var llm = LinearLayoutManager(this)
        rv!!.setLayoutManager(llm) // !!бросаем NPE если null, ?возвращаем ноль если null  ?:
        rv!!.setHasFixedSize(true)//в каких ситуациях лучше ? а в каких !!
        rv!!.setHasFixedSize(true)

        initializeData()
        initializeAdapter()
    }

    private fun downloadPhotosFromUrl()
    {

    }

    private fun initializeData(){
        //photos.add(PhotoData("Emma Wilson", R.drawable.emma))
        //photos.add(PhotoData("Love Dickers",R.drawable.lavery))
        //photos.add(PhotoData("Lillie Watts", R.drawable.lillie))
    }
    //http://www.jsoneditoronline.org/?id=e831231c5e268c86f742c39f48848717
    private fun initializeAdapter(){
        val adapter = RVAdapter(photos)
        rv!!.setAdapter(adapter)
    }
}
