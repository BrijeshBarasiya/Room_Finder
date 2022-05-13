package com.uipractice.roomfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.uipractice.roomfinder.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    // Variable
    private lateinit var binding: ActivityHomeBinding

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.onClicked = this
        binding.sloganText = ""
        loadData()
    }

    private fun loadData() {
        with(binding) {
            recyclerLocations.layoutManager = GridLayoutManager(this@HomeActivity, 2, GridLayoutManager.VERTICAL, false)
            recyclerLocations.adapter = LocationsAdapter( citiesAndProperties, 4)
            val menus = listOf("Flat", "Rooms", "Hall", "Rent", "House")
            recylerMenus.adapter = ChipsMenuAdapter(this@HomeActivity, menus)
            recyclerProperties.adapter = RecentPropertiesAdapter(propertyDescriptions)
            recyclerUpdates.adapter = RecentUpdateAdapter( propertyDescriptions, 2)
        }
    }

    override fun onClick(view: View) {
        when(view.id) {
            binding.btnSearch.id -> {
                Intent(this, DescriptionActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

}