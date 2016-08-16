package com.example.alexander.decorator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_last.*

class LastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)
        var materialOrder = findViewById(R.id.textViewOrder) as TextView
        materialOrder.setMovementMethod(ScrollingMovementMethod())
        materialOrder.setText(intent.getStringExtra("materialOrder"))
        var costOrder = findViewById(R.id.textViewCostOrder) as TextView
        costOrder.setText("Итог: " + intent.getStringExtra("costOrder") + " рублей")
        buttonBeginActivity.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            this@LastActivity.finish();
        }
    }
}
