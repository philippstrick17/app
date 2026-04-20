package com.example.myapplication

import android.content.SharedPreferences
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

        //Multiplyer Shop Button
        val shopbutton1  = findViewById<Button>(R.id.shopbutton01)
        val shopbutton2  = findViewById<Button>(R.id.shopbutton02)

        var multiplier = 0.0

        //Get intent value
        val counter_value = intent.getFloatExtra("counter_value", 0.0f)
        tvCounter.text = String.format("%.0f", counter_value)

        shopbutton1.setOnClickListener {
            if (counter_value >= 100) {
                multiplier += 0.1

                //Logic for Multiplyer
                tvMultiply.text = "$multiplier x"

                //Shared Prefrences
                val sharedPref = getSharedPreferences("multiplier", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putFloat("multiplier_key", multiplier.toFloat())
                editor.apply()
            }
        }

        //BackButton logic
        backButton.setOnClickListener {
            finish()
        }
    }
}