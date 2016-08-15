package com.example.alexander.decorator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_chocolate_pieces.*

class ChocolatePiecesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chocolate_pieces)
        val radioGroup = findViewById(R.id.radioGroupChocolatePieces) as RadioGroup

        var typeCondiment = "1 кусочек шоколада"
        var costChocolate = 1.0
        var costOrder = intent.getStringExtra("costOrder")
        var materialOrder = intent.getStringExtra("materialOrder")
        var costPortion = findViewById(R.id.textViewCostChocolatePieces) as TextView

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                // TODO Auto-generated method stub
                when (checkedId) {
                    R.id.radioPart1 -> {
                        costChocolate = 1.0
                        typeCondiment = "1 кусочек"
                        costPortion.setText("1 рубль")
                    }
                    R.id.radioPart2 -> {
                        costChocolate = 2.0
                        typeCondiment = "2 кусочка"
                        costPortion.setText("2 рубля")
                    }
                    R.id.radioPart3 -> {
                        costChocolate = 3.0
                        typeCondiment = "3 кусочка"
                        costPortion.setText("3 рубля")
                    }
                    R.id.radioPart4 -> {
                        costChocolate = 4.0
                        typeCondiment = "4 кусочка"
                        costPortion.setText("4 рубля")
                    }
                    R.id.radioPart5 -> {
                        costChocolate = 5.0
                        typeCondiment = "5 кусочков" //запонить количество шоколада и складывать, проверять на их количество, если их больше чем нужно то текст не печатать только цену удвоить
                        costPortion.setText("5 рублей")
                    }
                }
            }
        })
        buttonAdder.setOnClickListener {
            var intent = Intent(this, CondimentsActivity::class.java)
            costChocolate += costOrder.toDouble()
            intent.putExtra("costOrder", costChocolate)
            Toast.makeText(getApplicationContext(),
                    costChocolate.toString(),
                    Toast.LENGTH_SHORT).show()
            materialOrder += "\nШоколад($typeCondiment) ${costPortion.text}"
            intent.putExtra("materialOrder", materialOrder)
            startActivity(intent)
            this@ChocolatePiecesActivity.finish();
        }
    }
}
