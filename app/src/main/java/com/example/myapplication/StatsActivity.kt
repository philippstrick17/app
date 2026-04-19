package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        enableEdgeToEdge()

        val recievedvalue = intent.getIntExtra("counter_value", 0)

        val tvInfo = findViewById<TextView>(R.id.StatsInfo)
        tvInfo.text = "Dein aktueller Stand ist: $recievedvalue"
    }
}