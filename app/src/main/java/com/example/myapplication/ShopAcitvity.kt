package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ShopAcitvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shop)

        //Import all Objects
        val tvCounter = findViewById<TextView>(R.id.countervalue)
        val backButton = findViewById<Button>(R.id.backbutton)

        //Get intent value
        val counter_value = intent.getIntExtra("counter_value", 0)
        tvCounter.text = "$counter_value"

        //BackButton logic
        backButton.setOnClickListener {
            finish()
        }
    }
}