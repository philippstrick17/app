package com.example.myapplication


import android.content.Intent
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
    private lateinit var soundPool: SoundPool //SoundPool
    private var soundId: Int = 0 //SoundId
    private var counter = 0 //Counter
    private var counterpressed = 0 //How often the Counter is pressed
    private var resetpressed = 0 //How often Reset is pressed

    //PopAnimation
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

    //Function vor Vibration on Phone
    private fun vibrate() {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(50)
        }
    }

    //Destroy SoundPool Builder
    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //UI Config
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //SoundPool Builer
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .build()

        //Load SoundPool
        soundId = soundPool.load(this, R.raw.ping, 1)

        //Find all Objects in UI
        val tvCounter = findViewById<TextView>(R.id.counterText)
        val btnClick = findViewById<Button>(R.id.meinErsterButton)
        val resetClick = findViewById<Button>(R.id.resetbutton)
        val btnStats = findViewById<Button>(R.id.statsbutton)

        //Get Counter Value from Shared Prefs
        val sharedPref = getSharedPreferences("CounterValue", MODE_PRIVATE)
        counter = sharedPref.getInt("counter_key", 0)

        val sharedPref1 = getSharedPreferences("counterpressed", MODE_PRIVATE)
        counterpressed = sharedPref1.getInt("counterpressed_key", 0)

        val sharedPref2 = getSharedPreferences("resetpressed", MODE_PRIVATE)
        resetpressed = sharedPref2.getInt("resetpressed_key", 0)


        //Check for Counter Value
        if (counter >= 10) {
            tvCounter.setTextColor(android.graphics.Color.RED)
        }

        tvCounter.text = counter.toString() //Set Value of Text to Counter

        //OnReset Listener
        resetClick.setOnClickListener {
            resetpressed++ //Raise resetpressed
            counter = 0 //Reset Counter
            tvCounter.text = counter.toString() //Convert Counter to String
            tvCounter.setTextColor(android.graphics.Color.BLACK) //Set Text Color Black

            //Save Value of Resetpressed in SharedPrefs
            val sharedPref = getSharedPreferences("resetpressed", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putInt("resetpressed_key" , resetpressed)
            editor.apply()
        }

        //OnButton Listener
        btnClick.setOnClickListener {
            popAnimation(tvCounter) //Play Animation
            counter ++ //Raise Counter
            tvCounter.text = counter.toString() //Convert Counter to String
            soundPool.play(soundId, 1f, 1f, 0, 0, 1f) //Play Sound
            vibrate() //Vibrate

            //Save Value of Counter in SharedPrefs
            val sharedPref = getSharedPreferences("CounterValue", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putInt("counter_key", counter)
            editor.apply()

            //Save Value of Counterpressed in SharedPrefs
            val sharedPref1 = getSharedPreferences("counterpressed", MODE_PRIVATE)
            val editor1 = sharedPref.edit()
            editor1.putInt("counterpressed_key", counterpressed)
            editor1.apply()


            //Check for Value fo Counter
            if (counter >= 10) {
                tvCounter.setTextColor(android.graphics.Color.RED)
            }
        }

        //OnStats Button Listener
        btnStats.setOnClickListener {
            //Intent for Stats Activity
            val intent = Intent(this, StatsActivity::class.java)
            intent.putExtra("counter_value", counter)
            startActivity(intent)
        }
    }
}