package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class StatsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        enableEdgeToEdge()

        val recievedcounter = intent.getIntExtra("counter_value", 0)
        val recieverdcounterpressed = intent.getIntExtra("counterpressed", 0)
        val recievedresetpressed = intent.getIntExtra("resetpressed", 0)

        val tvCounter = findViewById<TextView>(R.id.StatsInfo)
        val tvCounterPressed = findViewById<TextView>(R.id.CounterPressed)
        val tvResetPressed = findViewById<TextView>(R.id.ResetPressed)
        val backbutton = findViewById<Button>(R.id.closebutton)


        tvCounter.text = "Aktuelle Punktzahl $recievedcounter"
        tvCounterPressed.text = "$recieverdcounterpressed mal gedrückt"
        tvResetPressed.text = "$recievedresetpressed mal Zurückgesetzt"

        backbutton.setOnClickListener {
            finish()
        }

    }
}