package com.example.alexander.decorator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_milkshake_portion_type.*

class MilkshakePortionTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_milkshake_portion_type)
        var costPortion = findViewById(R.id.portionCostForMilkShake) as TextView
        val radioGroup = findViewById(R.id.radioGroupForThreePortion) as RadioGroup
        val m_intent = intent
        val beverageName = m_intent.getStringExtra("beverageName")
        var typePortion = "маленький"
        var costOrder = 50.0
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                when (checkedId) {
                    R.id.radioLittle ->  {
                        costPortion.setText("50 рублей")
                        costOrder = 50.0
                    }
                    R.id.radioMiddle -> {
                        costPortion.setText("60 рублей")
                        typePortion = "средний"
                        costOrder = 60.0
                    }
                    R.id.radioBig ->  {
                        costPortion.setText("80 рублей")
                        typePortion = "большой"
                        costOrder = 80.0
                    }
                }
            }
        })
        buttonNextFromMilkshakeActivity.setOnClickListener {
            var intent = Intent(this, CondimentsActivity::class.java)
            intent.putExtra("costOrder", costOrder.toString())
            intent.putExtra("materialOrder", beverageName + "($typePortion) ${costPortion.text}")
            startActivity(intent)
            super.finish();
        }
    }
}
