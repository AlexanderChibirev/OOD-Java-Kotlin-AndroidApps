package com.example.alexander.decorator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_liquor.*

class LiquorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liquor)
        val radioGroup = findViewById(R.id.radioGroupLiquor) as RadioGroup

        var typeLiquor = "шоколадный"
        var costLiquor = 225.0
        var costOrder = intent.getStringExtra("costOrder").toDouble()
        var materialOrder = intent.getStringExtra("materialOrder")

        var costLiquorView = findViewById(R.id.textViewCostLiquor) as TextView

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                when(checkedId) {
                    R.id.radioButtonLiquorChocolate -> {
                        costLiquor = 15.0
                        typeLiquor = "шоколадный"
                        costLiquorView.setText("15 рублей")
                    }
                    R.id.radioButtonLiquorWalnut -> {
                        costLiquor = 25.0
                        typeLiquor = "грецкий орех"
                        costLiquorView.setText("25 рублей")
                    }
                }
            }
        })
        buttonAdderLiquor.setOnClickListener {
            var intent = Intent(this, CondimentsActivity::class.java)
            costOrder += costLiquor
            intent.putExtra("costOrder", costLiquor)
            materialOrder += "\nЛикер($typeLiquor) ${costLiquorView.text}"
            intent.putExtra("materialOrder", materialOrder)
            startActivity(intent)
            this@LiquorActivity.finish();
        }
    }
}
