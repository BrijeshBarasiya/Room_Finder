package com.uipractice.roomfinder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.authentication.LoginActivity
import com.uipractice.roomfinder.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    // Variable
    private lateinit var binding: ActivitySplashBinding

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, ApiIdentifierActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }, 2000)
    }

}