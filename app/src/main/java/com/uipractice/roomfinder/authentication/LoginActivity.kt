package com.uipractice.roomfinder.authentication

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.util.Patterns
import android.view.View
import android.view.View.OnFocusChangeListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.uipractice.roomfinder.HomeActivity
import com.uipractice.roomfinder.R
import com.uipractice.roomfinder.boldSpan
import com.uipractice.roomfinder.clickableForegroundColorSpan
import com.uipractice.roomfinder.createToast
import com.uipractice.roomfinder.databinding.ActivityLoginBinding
import com.uipractice.roomfinder.webServices.ApiIdentity
import com.uipractice.roomfinder.webServices.IdentifyApiCall
import com.uipractice.roomfinder.webServices.apiIdentifier
import org.json.JSONObject

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        loadData()
        binding.onClicked = this
        binding.edtEmail.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if(Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches()) {
                    binding.edtEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_verified, 0)
                    binding.lblEmailError.visibility = View.INVISIBLE
                } else {
                    binding.edtEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0)
                    binding.lblEmailError.visibility = View.VISIBLE
                }
            }
        }
        viewModel.checkUser.observe(this) {
            it.token.createToast(this)
            Intent(this, HomeActivity::class.java).apply {
                startActivity(this)
            }
        }
        viewModel.failureMessage.observe(this) {
            it.createToast(this)
        }
        viewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }
    }

    private fun loadData() {
        val signUpText = SpannableString(resources.getString(R.string.sign_up_here)).apply {
            clickableForegroundColorSpan("#2C56C0", this@LoginActivity, SignUpActivity::class.java)
            boldSpan()
        }
        binding.lblNewMember.text = SpannableString(TextUtils.concat(
            binding.lblNewMember.text.toString(),
            " ",
            signUpText
        ))
        binding.lblNewMember.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onClick(view: View) {
        when(view.id) {
            binding.btnSignIn.id -> {
                when (apiIdentifier) {
                    IdentifyApiCall.UsingHttp -> viewModel.checkUser(binding.edtEmail.text.toString(), binding.edtPassword.text.toString())
                    IdentifyApiCall.UsingRetrofit -> viewModel.checkUserRetrofit(binding.edtEmail.text.toString(), binding.edtPassword.text.toString())
                }
            }
            binding.btnGoogleLogin.id -> resources.getString(R.string.continue_with_google).createToast(this)
            binding.btnFacebookLogin.id -> resources.getString(R.string.continue_with_facebook).createToast(this)
        }
    }

}