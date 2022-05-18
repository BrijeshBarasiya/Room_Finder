package com.uipractice.roomfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.uipractice.roomfinder.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    // Variable
    private lateinit var binding: ActivitySearchBinding

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.onClicked = this
        loadData()
    }

    private fun loadData() {
        with(binding) {
            recylerLocations.layoutManager = GridLayoutManager(this@SearchActivity, 2, GridLayoutManager.VERTICAL, false)
            recylerLocations.adapter = LocationsAdapter(citiesAndProperties)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            binding.btnSearch.id -> {
                Intent(this, SettingActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

}