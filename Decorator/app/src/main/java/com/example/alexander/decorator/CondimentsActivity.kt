package com.example.alexander.decorator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class CondimentsActivity : AppCompatActivity() {

    val condimentNames = arrayOf("Сироп","Шоколад","Ликер")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condiments)
        val lvCondiment = findViewById(R.id.listViewCondiment) as ListView
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, condimentNames)
        lvCondiment.adapter = adapter

        lvCondiment.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, itemClicked: View, position: Int,id: Long) {
                val textView = itemClicked as TextView
                val strText = textView.getText().toString() // получаем текст нажатого элемента
                when(strText)
                {
                    "Шоколад" -> {
                        //Toast.makeText(getApplicationContext(),"Пора покормить кота!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, ChocolatePiecesActivity::class.java))
                    }
                    "Сироп" -> {
                        startActivity(Intent(applicationContext, SyrupActivity::class.java))
                    }
                    "Ликер" -> {
                        startActivity(Intent(applicationContext, LiquorActivity::class.java))
                    }
                }
            }
        })
    }
}
