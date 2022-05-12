package com.uipractice.roomfinder.authentication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View.OnFocusChangeListener
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.DescriptionActivity
import com.uipractice.roomfinder.R
import com.uipractice.roomfinder.databinding.AuthenticationLoginScreenBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: AuthenticationLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthenticationLoginScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.edtEmail.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if(Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {
                    binding.edtEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.verified, 0)
                } else {
                    binding.edtEmail.error = resources.getString(R.string.enter_valid_address)
                }
            }
        }

        binding.btnSignIn.setOnClickListener{
            Intent(this, DescriptionActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

}