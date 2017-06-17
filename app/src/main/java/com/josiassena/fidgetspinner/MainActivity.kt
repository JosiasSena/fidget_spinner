package com.josiassena.fidgetspinner

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        ivFidget.setOnTouchListener { v, event ->
            when (event.action) {
                KeyEvent.ACTION_DOWN -> {
                    ivFidget.startAnimation(getSpinAnimation())
                    startVibration()
                }
                KeyEvent.ACTION_UP -> {
                    ivFidget.clearAnimation()
                    stopVibration()
                }
            }

            true
        }
    }

    private fun getSpinAnimation(): RotateAnimation {
        val spin = RotateAnimation(0f, 1000000f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)

        spin.duration = Math.abs(Animation.INFINITE.toLong())
        spin.repeatCount = Animation.INFINITE
        spin.interpolator = LinearOutSlowInInterpolator()
        spin.fillAfter = false

        return spin
    }

    private fun startVibration() {
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(500000)
        }
    }

    private fun stopVibration() {
        if (vibrator.hasVibrator()) {
            vibrator.cancel()
        }
    }
}
