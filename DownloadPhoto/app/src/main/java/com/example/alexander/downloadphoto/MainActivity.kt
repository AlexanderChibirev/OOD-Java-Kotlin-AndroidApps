package com.example.alexander.downloadphoto

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView;

class MainActivity : AppCompatActivity() {

    private val persons: List<Person>? = null
    private val rv: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        rv?.setHasFixedSize(true)
    }
}