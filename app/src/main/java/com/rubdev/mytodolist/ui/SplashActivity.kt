package com.rubdev.mytodolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.rubdev.mytodolist.R
import com.rubdev.mytodolist.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            initMainActivity()
        }, 3000)
    }

    private fun initMainActivity(){
        val intent = Intent(
            this@SplashActivity, MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}