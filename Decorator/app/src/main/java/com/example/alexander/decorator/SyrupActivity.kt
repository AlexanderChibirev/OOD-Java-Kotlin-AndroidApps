package com.example.alexander.decorator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_syrup.*

class SyrupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_syrup)
        val radioGroup = findViewById(R.id.radioGroupSyrup) as RadioGroup
        var typeCondiment = "шоколадный"
        var costSyrop = 15.0
        var costOrder = intent.getStringExtra("costOrder")
        var materialOrder = intent.getStringExtra("materialOrder")
        var costSyrupTextView = findViewById(R.id.textViewCostSyrup) as TextView

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                when(checkedId){
                    R.id.radioButton1 -> {
                        costSyrop = 15.0
                        typeCondiment = "шоколадный"
                        costSyrupTextView.setText("15 рублей")
                    }
                    R.id.radioButton2 -> {
                        costSyrop = 20.0
                        typeCondiment = "кленовый"
                        costSyrupTextView.setText("20 рублей")
                    }
                }
            }
        })
        buttonAdderSyrup.setOnClickListener {
            var intent = Intent(this, CondimentsActivity::class.java)
            costSyrop += costOrder.toDouble()//не видит цену
            intent.putExtra("costOrder", costSyrop.toString())
            materialOrder += "\nСироп($typeCondiment) ${costSyrupTextView.text}"
            intent.putExtra("materialOrder", materialOrder)
            startActivity(intent)
            this@SyrupActivity.finish();
        }
    }
}
