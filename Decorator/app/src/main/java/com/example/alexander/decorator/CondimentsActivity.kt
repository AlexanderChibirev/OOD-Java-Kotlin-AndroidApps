package com.example.alexander.decorator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_condiments.*

class CondimentsActivity : AppCompatActivity() {

    val condimentNames = arrayOf("Сироп","Шоколад","Ликер")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condiments)
        val lvCondiment = findViewById(R.id.listViewCondiment) as ListView
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, condimentNames)
        lvCondiment.adapter = adapter
        var materialOrder = intent.getStringExtra("materialOrder")
        var costOrder = intent.getStringExtra("costOrder")
        lvCondiment.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, itemClicked: View, position: Int,id: Long) {
                val textView = itemClicked as TextView
                val strText = textView.getText().toString() // получаем текст нажатого элемента
                when(strText)
                {
                    "Шоколад" -> {
                        var ChocolatePiecesActivityIntent = Intent(applicationContext, ChocolatePiecesActivity::class.java)
                        ChocolatePiecesActivityIntent.putExtra("materialOrder", materialOrder)
                        ChocolatePiecesActivityIntent.putExtra("costOrder", costOrder)
                        startActivity(ChocolatePiecesActivityIntent)
                        this@CondimentsActivity.finish();
                    }
                    "Сироп" -> {
                        var SyrupActivityIntent = Intent(applicationContext, SyrupActivity::class.java)
                        SyrupActivityIntent.putExtra("materialOrder", materialOrder)
                        SyrupActivityIntent.putExtra("costOrder", costOrder)
                        startActivity(SyrupActivityIntent)
                        this@CondimentsActivity.finish();
                    }
                    "Ликер" -> {
                        var LiquorActivityIntent = Intent(applicationContext, LiquorActivity::class.java)
                        LiquorActivityIntent.putExtra("materialOrder", materialOrder)
                        LiquorActivityIntent.putExtra("costOrder", costOrder)
                        startActivity(LiquorActivityIntent)
                        this@CondimentsActivity.finish();
                    }
                }
            }
        })
        buttonSetOrder.setOnClickListener(){
            var intent = Intent(applicationContext, LastActivity::class.java)
            intent.putExtra("materialOrder", materialOrder)
            intent.putExtra("costOrder", costOrder)
            startActivity(intent)
            super.finish();
        }
    }
}
