package com.example.alexander.decorator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_portion_two_type.*

class PortionTwoTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portion_two_type)
        var costPortion = findViewById(R.id.portionCost) as TextView
        val m_intent = intent
        val beverageName = m_intent.getStringExtra("beverageName")
        var typePortion = "стандартный"
        var costOrder = 0.0
        when(beverageName){
            "Латте" ->  {
                costPortion.setText("90 рублей")
                costOrder = 90.0
            }
            "Каппучино" -> {
                costPortion.setText("80 рублей")
                costOrder = 80.0
            }
            "Кофе" -> {
                costPortion.setText("120 рублей")
                costOrder = 120.0
            }
        }
        val radioGroup = findViewById(R.id.radioGroupForTwoPortion) as RadioGroup
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                when(beverageName){
                    "Латте" -> {
                        when (checkedId) {
                            R.id.radioStandard ->   {
                                costPortion.setText("90 рублей")
                                costOrder = 90.0
                                typePortion = "стандартный"
                            }
                            R.id.radioDouble -> {
                                costPortion.setText("130 рублей")
                                typePortion = "двойной"
                                costOrder = 130.0
                            }
                        }
                    }
                    "Каппучино" -> {
                        when (checkedId) {
                            R.id.radioStandard ->   {
                                costPortion.setText("80 рублей")
                                costOrder = 80.0
                                typePortion = "стандартный"
                            }
                            R.id.radioDouble -> {
                                costPortion.setText("120 рублей")
                                typePortion = "двойной"
                                costOrder = 120.0
                            }
                        }
                    }
                    "Кофе" -> {
                        when (checkedId) {
                            R.id.radioStandard ->   {
                                costPortion.setText("60 рублей")
                                typePortion = "стандартный"
                                costOrder = 120.0
                            }
                            R.id.radioDouble -> {
                                costPortion.setText("120 рублей")
                                typePortion = "двойной"
                                costOrder = 120.0
                            }
                        }
                    }
                }
            }
        })
        buttonNextFromPortiontypeActivity.setOnClickListener {
            var intent = Intent(this, CondimentsActivity::class.java)
            intent.putExtra("costOrder", costOrder.toString())
            intent.putExtra("materialOrder", beverageName + "($typePortion) ${costPortion.text}")
            startActivity(intent)
            super.finish();
        }
    }
}
