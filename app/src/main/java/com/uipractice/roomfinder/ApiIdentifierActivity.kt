package com.uipractice.roomfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.authentication.view.LoginActivity
import com.uipractice.roomfinder.databinding.ActivityApiIdentifierBinding
import com.uipractice.roomfinder.webServices.ApiIdentity
import com.uipractice.roomfinder.webServices.IdentifyApiCall
import com.uipractice.roomfinder.webServices.apiIdentifier

class ApiIdentifierActivity : AppCompatActivity(), View.OnClickListener {

    // Variable
    private lateinit var binding: ActivityApiIdentifierBinding

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiIdentifierBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.onClicked = this
    }

    override fun onClick(view: View) {
        with(binding) {
            when (view.id) {
                btnUsingHttp.id -> apiIdentifier = IdentifyApiCall.UsingHttp
                btnUsingRetrofit.id -> apiIdentifier = IdentifyApiCall.UsingRetrofit
            }
        }
        Intent(this, LoginActivity::class.java).apply {
            putExtra(ApiIdentity, apiIdentifier)
            startActivity(this)
        }
    }

}