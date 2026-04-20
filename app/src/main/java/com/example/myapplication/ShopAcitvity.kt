package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class ShopAcitvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shop)

        //Import all Objects
        val tvCounter = findViewById<TextView>(R.id.countervalue)
        val backButton = findViewById<Button>(R.id.backbutton)
        val tvMultiply = findViewById<TextView>(R.id.multiplyertext)

        //Get intent value
        val counter_value = intent.getFloatExtra("counter_value", 0.0f)
        tvCounter.text = String.format("%.0f", counter_value)

        //Logic for Multiplyer
        var multiplyer = 0f
        tvMultiply.text = "$multiplyer x"

        //BackButton logic
        backButton.setOnClickListener {
            finish()
        }
    }
}