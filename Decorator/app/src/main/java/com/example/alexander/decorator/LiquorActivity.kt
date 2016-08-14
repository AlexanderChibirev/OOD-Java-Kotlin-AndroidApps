package com.example.alexander.decorator
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_liquor.*

class LiquorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liquor)
        val radioGroup = findViewById(R.id.radioGroupLiquor) as RadioGroup
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
            }
        })
        buttonAdderLiquor.setOnClickListener {
            val intent = Intent(this, CondimentsActivity::class.java)
            startActivity(intent)
        }
    }
}
