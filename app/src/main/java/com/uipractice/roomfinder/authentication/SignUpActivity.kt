package com.uipractice.roomfinder.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.R
import com.uipractice.roomfinder.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    // Variables
    private lateinit var binding: ActivitySignUpBinding

    // Override Function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.onClicked = this
        supportActionBar?.hide()
        binding.edtEmail.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {
                    binding.edtEmail.error = resources.getString(R.string.enter_valid_address)
                }
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id) {
            binding.lblSignin.id -> {
                Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                }
            }
            binding.btnCreateAccount.id -> Toast.makeText(this, resources.getString(R.string.create_account), Toast.LENGTH_SHORT).show()
            binding.btnGoogleLogin.id -> Toast.makeText(this, resources.getString(R.string.continue_with_google), Toast.LENGTH_SHORT).show()
            binding.btnFacebookLogin.id -> Toast.makeText(this, resources.getString(R.string.continue_with_facebook), Toast.LENGTH_SHORT).show()
        }
    }

}