package com.uipractice.roomfinder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.databinding.ActivitySplashBinding
import com.uipractice.roomfinder.webServices.isLOGIN

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
        val isLogin = SharedPreference.getSharedPreference(this, isLOGIN, false).toString().toBoolean()
        Handler(Looper.getMainLooper()).postDelayed({
            if (isLogin) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Intent(this, ApiIdentifierActivity::class.java).apply {
                    startActivity(this)
                }
            }
            finish()
        }, 2000)
    }

}