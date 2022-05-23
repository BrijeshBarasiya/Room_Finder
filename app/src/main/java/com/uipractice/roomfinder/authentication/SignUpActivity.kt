package com.uipractice.roomfinder.authentication

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.uipractice.roomfinder.R
import com.uipractice.roomfinder.boldSpan
import com.uipractice.roomfinder.clickableForegroundColorSpan
import com.uipractice.roomfinder.createToast
import com.uipractice.roomfinder.databinding.ActivitySignUpBinding
import com.uipractice.roomfinder.isError
import com.uipractice.roomfinder.isValidPassword
import org.json.JSONObject

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    // Variables
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    // Override Function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loadData()
        binding.onClicked = this
        supportActionBar?.hide()
        viewModel.createUserObserver.observe(this) {
            if (it.isSuccess) {
                it.message.createToast(this)
                Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                }
            } else {
                it.message.createToast(this)
            }
        }
        viewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
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
            binding.btnCreateAccount.id -> {

                with(binding) {
                    if (edtFullName.isError(lblErrorFullName) && edtEmail.isError(lblErrorEmail) && edtPassword.isValidPassword(lblErrorPassword)) {
                        if (edtPassword.text.toString() == edtConfirmPassword.text.toString()) {
                            val body = JSONObject()
                            body.put("email",edtEmail.text.toString())
                            body.put("password",edtPassword.text.toString())
                            viewModel.createUser(body)
                        } else {
                            resources.getString(R.string.password_does_not_match).createToast(this@SignUpActivity)
                        }
                    } else {
                        resources.getString(R.string.fill_all_required_details).createToast(this@SignUpActivity)
                    }
                }
            }
            binding.btnGoogleLogin.id -> resources.getString(R.string.continue_with_google).createToast(this)
            binding.btnFacebookLogin.id -> resources.getString(R.string.continue_with_facebook).createToast(this)
        }
    }

}

