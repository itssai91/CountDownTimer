package com.infranetstudio.com.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.infranetstudio.com.countdowntimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null
    private var currentTime: Long = 0
    private var totalTimer: Long = 60000
//    private var onTickTime: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnPause.isEnabled = false

        binding.btnStart.setOnClickListener {
            countDownTime()
        }
        binding.btnPause.setOnClickListener {
            onPauseTimer()
        }
        binding.btnReset.setOnClickListener {
            onResetTimer()
        }

    }

    private fun countDownTime() {
        timer = object : CountDownTimer(totalTimer - currentTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (binding.btnStart.text == "Resume") {
                    binding.btnStart.text = "Start"
                    binding.btnPause.isEnabled = false
                }
                binding.btnPause.isEnabled = true
                currentTime = totalTimer - millisUntilFinished
                binding.tvTimer.text = (millisUntilFinished / 1000).toString()
                binding.btnStart.isEnabled = false
            }

            override fun onFinish() {
                Toast.makeText(
                    this@MainActivity,
                    "Your CountDown Stopped...",
                    Toast.LENGTH_SHORT
                )
                    .show()
                binding.btnStart.isEnabled = true
                timer = null
                currentTime = 0
            }

        }.start()

    }

    private fun onPauseTimer() {

        timer?.cancel()
        if (binding.btnStart.text == "Start") {
            binding.btnStart.text = "Resume"
            binding.btnStart.isEnabled = true
            binding.btnPause.isEnabled = false
        }

    }

    private fun onResetTimer() {
        if (timer != null) {
            timer?.cancel()
            timer = null
            currentTime = 0
            binding.tvTimer.text = (totalTimer / 1000).toString()
            binding.btnStart.isEnabled = true
            binding.btnPause.isEnabled = false
            binding.btnStart.text = "Start"
        }

    }

}