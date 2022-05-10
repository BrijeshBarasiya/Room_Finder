package com.uipractice.roomfinder.authentication

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.R
import com.uipractice.roomfinder.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    // Variables
    private lateinit var binding: ActivitySignUpBinding

    // Override Function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        binding.edtEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {
                    binding.edtEmail.error = resources.getString(R.string.enter_valid_address)
                }
            }
        }
    }

}