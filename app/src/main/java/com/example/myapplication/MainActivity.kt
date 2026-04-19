package com.example.myapplication


import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.selects.SelectInstance

class MainActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private var soundId: Int = 0
    private var counter = 0

    private fun vibrate() {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(50)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .build()

        soundId = soundPool.load(this, R.raw.ping, 1)

        val tvCounter = findViewById<TextView>(R.id.counterText)
        val btnClick = findViewById<Button>(R.id.meinErsterButton)
        val resetClick = findViewById<Button>(R.id.resetbutton)

        resetClick.setOnClickListener {
            counter = 0
            tvCounter.text = counter.toString()
            tvCounter.setTextColor(android.graphics.Color.BLACK)
        }

        btnClick.setOnClickListener {
            counter ++
            tvCounter.text = counter.toString()
            soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
            vibrate()

            if (counter >= 10) {
                tvCounter.setTextColor(android.graphics.Color.RED)
            }
        }
    }
}