package com.example.testebliss.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.testebliss.R
import com.example.testebliss.extensions.clearBackStackAndStartActivity
import com.example.testebliss.extensions.haveConnection
import com.example.testebliss.modules.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        setupMotionLayout()
    }


    private fun returnHaveConnection(): Boolean {
        return this.haveConnection()
    }


    private fun setupMotionLayout() {
        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                if (returnHaveConnection()) {
                    clearBackStackAndStartActivity<MainActivity>()
                } else {
                    Toast.makeText(applicationContext, "Ops! Verifique sua conexão e tente novamente", Toast.LENGTH_LONG)
                }
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) { }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) { }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) { }
        })
    }

}