package com.example.myapplication


import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Display
import android.view.View
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

    private fun popAnimation(view: android.view.View) {
        view.animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(100)
            .withEndAction {
                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .start()
            }
            .start()
    }

    private fun vibrate() {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(50)
        }
    }

    private fun saveint(value: Int) {
        val sharedPref = getSharedPreferences("CounterValue", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("counter_key", value)
        editor.apply()
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
        val sharedPref = getSharedPreferences("CounterValue", MODE_PRIVATE)
        counter = sharedPref.getInt("counter_key", 0)
        if (counter >= 10) {
            tvCounter.setTextColor(android.graphics.Color.RED)
        }

        tvCounter.text = counter.toString()

        resetClick.setOnClickListener {
            counter = 0
            tvCounter.text = counter.toString()
            tvCounter.setTextColor(android.graphics.Color.BLACK)
        }

        btnClick.setOnClickListener {
            popAnimation(tvCounter)
            counter ++
            tvCounter.text = counter.toString()
            soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
            vibrate()
            saveint(counter)

            if (counter >= 10) {
                tvCounter.setTextColor(android.graphics.Color.RED)
            }
        }
    }
}