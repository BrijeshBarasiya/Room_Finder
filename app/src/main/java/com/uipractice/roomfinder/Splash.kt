package com.uipractice.roomfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.uipractice.roomfinder.authentication.LoginScreen
import com.uipractice.roomfinder.databinding.SplashScreenBinding

class Splash : AppCompatActivity() {

    // Variable
    private lateinit var binding: SplashScreenBinding

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, DescriptionScreen::class.java).apply {
                startActivity(this)
            }
            finish()
        }, 2000)
    }

}