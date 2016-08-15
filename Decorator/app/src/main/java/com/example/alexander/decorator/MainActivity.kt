package com.example.alexander.decorator


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val elvMain: ExpandableListView? = null
    var ah: AdapterHelper? = null
    var adapter: SimpleExpandableListAdapter? = null
    var beverageName: String? = null
    var groups: MutableList<String>  = mutableListOf("КОФЕ", "ЧАЙ", "МОЛОЧНЫЙ КОКТЕЙЛЬ")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonBegin.setOnClickListener {
            setContentView(R.layout.expandeblelist)
            var tvInfo = findViewById(R.id.tvInfo) as TextView

            var ah = AdapterHelper(this)
            adapter = ah.getAdapter()

            var elvMain = findViewById(R.id.elvMain) as ExpandableListView
            elvMain.setAdapter(adapter)
            elvMain.setOnChildClickListener(object : ExpandableListView.OnChildClickListener {
                override fun onChildClick(parent: ExpandableListView, v: View, groupPosition: Int, childPosition: Int, id: Long): Boolean {
                    beverageName = ah.getGroupChildText(groupPosition,childPosition)
                    selectPortion()
                    return false
               }
            })
        }
    }

    fun selectPortion()
    {
        when(beverageName){//тут будет помещаться тип
            "Латте", "Каппучино", "Кофе" -> {
                var intent = Intent(this, PortionTwoTypeActivity::class.java)
                intent.putExtra("beverageName", beverageName);
                startActivity(intent)
                super.finish();
            }
            "Чай" -> {
                var intent = Intent(this, ColorTeaTypeActivity::class.java)
                intent.putExtra("beverageName", beverageName);
                startActivity(intent)
                super.finish();
            }
            "Молочный коктейль" -> {
                var intent = Intent(this, MilkshakePortionTypeActivity::class.java)
                intent.putExtra("beverageName", beverageName);
                startActivity(intent)
                super.finish();
            }
            else -> { null }
        }
    }

}


