package com.example.alexander.decorator

import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.example.alexander.decorator.Definitions.LiquorType

class LiquorActivity : AppCompatActivity() {
    var typess = LiquorType.values()
    //val condimentNames = arrayOf()
    val lvCondiment = findViewById(R.id.listViewCondiment) as ListView
    //val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, condimentNames)

}
