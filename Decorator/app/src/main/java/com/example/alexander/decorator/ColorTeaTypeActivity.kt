package com.example.alexander.decorator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_color_tea_type.*

class ColorTeaTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_tea_type)
        var costPortion = findViewById(R.id.portionCostForTeaColor) as TextView
        val radioGroup = findViewById(R.id.radioGroupForTea) as RadioGroup
        val beverageName = intent.getStringExtra("beverageName")
        var typePortion = "черный"
        var costOrder = 30.0

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                //спросить как по id узнавать имя кнопки, иначе код грамозкий
                when (checkedId) {
                    R.id.radioYellowTea ->  {
                        costPortion.setText("30 рублей")
                        typePortion = "желтый"
                        costOrder = 30.0
                    }
                    R.id.radioFruitTea ->  {
                        costPortion.setText("30 рублей")
                        typePortion = "фруктовый"
                        costOrder = 30.0
                    }
                    R.id.radioGreenTea ->  {
                        costPortion.setText("30 рублей")
                        typePortion = "зеленый"
                        costOrder = 30.0
                    }
                }
            }
        })
        buttonNextFromColorTeaTypeActivity.setOnClickListener {
            var intent = Intent(this, CondimentsActivity::class.java)
            intent.putExtra("costOrder", costOrder.toString())
            intent.putExtra("materialOrder", beverageName + "($typePortion) ${costPortion.text}")
            startActivity(intent)
            super.finish();
        }
    }
}
