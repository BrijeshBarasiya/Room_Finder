package com.uipractice.roomfinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uipractice.roomfinder.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    // Variable
    private lateinit var binding: ActivitySettingBinding

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loadData()
    }

    private fun loadData() {
        with(binding) {
            titleText = resources.getString(R.string.account)
            sloganText = ""
            imgProfile.setImageResource(R.drawable.image1)
            var text = "Courtney Henry"
            userName.text = text
            text = "10 Applied | Kathmandu"
            userLocation.text = text
            recylerSettings.adapter = SettingsAdapter(this@SettingActivity, settingsModelClass)
        }
    }

}