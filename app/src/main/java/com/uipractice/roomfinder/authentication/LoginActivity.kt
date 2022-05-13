package com.uipractice.roomfinder.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.DescriptionActivity
import com.uipractice.roomfinder.R
import com.uipractice.roomfinder.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        binding.onClicked = this
        binding.edtEmail.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if(Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {
                    binding.edtEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_verified, 0)
                } else {
                    binding.edtEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0)
                }
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id) {
            binding.btnSignIn.id -> {
                Intent(this, DescriptionActivity::class.java).apply {
                    startActivity(this)
                }
            }
            binding.lblNewMember.id -> {
                Intent(this, SignUpActivity::class.java).apply {
                    startActivity(this)
                }
            }
            binding.btnGoogleLogin.id -> Toast.makeText(this, resources.getString(R.string.continue_with_google), Toast.LENGTH_SHORT).show()
            binding.btnFacebookLogin.id -> Toast.makeText(this, resources.getString(R.string.continue_with_facebook), Toast.LENGTH_SHORT).show()
        }
    }

}