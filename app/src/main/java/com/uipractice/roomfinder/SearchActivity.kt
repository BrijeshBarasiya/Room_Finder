package com.uipractice.roomfinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.uipractice.roomfinder.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    // Variable
    private lateinit var binding: ActivitySearchBinding

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        loadData()
    }

    private fun loadData() {
        with(binding) {
            recylerLocations.layoutManager = GridLayoutManager(this@SearchActivity, 2, GridLayoutManager.VERTICAL, false)
            recylerLocations.adapter = LocationsAdapter(citiesAndProperties)
        }
    }

}