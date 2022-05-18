package com.uipractice.roomfinder.authentication

import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.R
import com.uipractice.roomfinder.boldSpan
import com.uipractice.roomfinder.clickableForegroundColorSpan
import com.uipractice.roomfinder.createToast
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
        loadData()
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

    private fun loadData() {
        val signUpText = SpannableString(resources.getString(R.string.sign_in_here)).apply {
            clickableForegroundColorSpan("#2C56C0", this@SignUpActivity, LoginActivity::class.java)
            boldSpan()
        }
        binding.lblSignIn.text = SpannableString(
            TextUtils.concat(
            binding.lblSignIn.text.toString(),
            " ",
            signUpText
        ))
        binding.lblSignIn.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onClick(view: View) {
        when(view.id) {
            binding.btnCreateAccount.id -> resources.getString(R.string.create_account).createToast(this)
            binding.btnGoogleLogin.id -> resources.getString(R.string.continue_with_google).createToast(this)
            binding.btnFacebookLogin.id -> resources.getString(R.string.continue_with_facebook).createToast(this)
        }
    }

}