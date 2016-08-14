package com.example.alexander.decorator


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ExpandableListView
import android.widget.RadioGroup
import android.widget.SimpleExpandableListAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.color_tea_type.*
import kotlinx.android.synthetic.main.milkshake_portion_type.*
import kotlinx.android.synthetic.main.portion_type.*

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
                    //selectCondiment()
                    return false
               }
            })
        }
    }

    fun selectPortion()
    {
        when(beverageName){//тут будет помещаться тип
            "Латте", "Каппучино", "Кофе" -> goToPortionTypeActivity()
            "Чай" -> goToColorTeaActivity()
            "Молочный коктейль" -> goToMilkshakePortionTypeActivity()
            else -> { null }
        }
    }
///////
    fun goToPortionTypeActivity(){
        setContentView(R.layout.portion_type)
        var costPortion = findViewById(R.id.portionCost) as TextView
        when(beverageName){
            "Латте" ->  costPortion.setText("90 рублей")
            "Каппучино" -> costPortion.setText("80 рублей")
            "Кофе" -> costPortion.setText("120 рублей")
        }
        val radioGroup = findViewById(R.id.radioGroupForTwoPortion) as RadioGroup
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                when(beverageName){
                    "Латте" -> {
                        when (checkedId) {
                            R.id.radioStandard ->   costPortion.setText("90 рублей")
                            R.id.radioDouble -> costPortion.setText("130 рублей")
                        }
                    }
                    "Каппучино" -> {
                        when (checkedId) {
                            R.id.radioStandard ->   costPortion.setText("80 рублей")
                            R.id.radioDouble -> costPortion.setText("120 рублей")
                        }
                    }
                    "Кофе" -> {
                        when (checkedId) {
                            R.id.radioStandard ->   costPortion.setText("60 рублей")
                            R.id.radioDouble -> costPortion.setText("120 рублей")
                        }
                    }
                }
            }
        })
        buttonNextFromPortiontypeActivity.setOnClickListener {
            val intent = Intent(this, CondimentsActivity::class.java)
            startActivity(intent)
        }
    }
//////
    fun goToColorTeaActivity(){
        setContentView(R.layout.color_tea_type)
        var costPortion = findViewById(R.id.portionCostForTeaColor) as TextView
        val radioGroup = findViewById(R.id.radioGroupForTea) as RadioGroup
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                when (checkedId) {
                    R.id.radioBlackTea, R.id.radioYellowTea,
                    R.id.radioFruitTea, R.id.radioGreenTea ->  costPortion.setText("30 рублей")
                }
            }
        })
        buttonNextFromColorTeaTypeActivity.setOnClickListener {
            val intent = Intent(this, CondimentsActivity::class.java)
            startActivity(intent)
        }
    }
////
    fun goToMilkshakePortionTypeActivity(/*сюда будем помещать тип напитка из перечеслений*/){
        setContentView(R.layout.milkshake_portion_type)
        var costPortion = findViewById(R.id.portionCostForMilkShake) as TextView
        val radioGroup = findViewById(R.id.radioGroupForThreePortion) as RadioGroup
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                when (checkedId) {
                    R.id.radioLittle ->  costPortion.setText("50 рублей")
                    R.id.radioMiddle -> costPortion.setText("60 рублей")
                    R.id.radioBig ->  costPortion.setText("80 рублей")
                }
            }
        })
        buttonNextFromMilkshakeActivity.setOnClickListener {
            val intent = Intent(this, CondimentsActivity::class.java)
            startActivity(intent)
        }
    }
}


