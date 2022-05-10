package com.uipractice.roomfinder.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uipractice.roomfinder.databinding.AuthenticationLoginScreenBinding
import com.uipractice.roomfinder.databinding.SplashScreenBinding

class LoginScreen : AppCompatActivity() {

    private lateinit var binding: AuthenticationLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthenticationLoginScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}