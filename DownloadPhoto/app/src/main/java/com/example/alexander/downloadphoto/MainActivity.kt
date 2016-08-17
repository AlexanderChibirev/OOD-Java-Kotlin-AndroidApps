package com.example.alexander.downloadphoto

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private val persons = ArrayList<Person>()
    private var rv: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv) as RecyclerView//возможно тут ошибка// rv = сокращенно RecyclerView

        var llm = LinearLayoutManager(this)
        rv!!.setLayoutManager(llm); // !!бросаем NPE если null, ?возвращаем ноль если null  ?:
        rv!!.setHasFixedSize(true);//в каких ситуациях лучше ? а в каких !!
        rv!!.setHasFixedSize(true)
    }

    private fun initializeData(){
        persons.add(Person("Emma Wilson", "23 years old", R.drawable.emma))
        persons.add(Person("Love Dickers", "25 years old", R.drawable.lavery))
        persons.add(Person("Lillie Watts", "35 years old", R.drawable.lillie))
    }
}

