package com.uipractice.roomfinder

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.uipractice.roomfinder.databinding.DescriptionScreenBinding

class DescriptionScreen : AppCompatActivity() {

    // Variable
    private lateinit var binding: DescriptionScreenBinding

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DescriptionScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        loadData()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    // Function
    private fun loadData() {
        with(binding) {
            lblPropertyName.text = propertyDescription.propertyName
            val price = "Rs. ${propertyDescription.propertyPrice} /"
            lblPropertyPrice.text = price
            userProfile.setImageResource(R.drawable.profile_photo)
            userName.text = propertyDescription.userName
            userLocation.text = propertyDescription.userLocation
            lblPropertyAddress.text = propertyDescription.propertyAddress
            lblPropertyArea.text = propertyDescription.propertyArea
            val applied ="${propertyDescription.propertyApplied} Applied | ${propertyDescription.propertyView} Views"
            lblPropertyApplied.text = applied
            lblIsAvailable.text = propertyDescription.isAvailable
            lblPropertyBy.text = propertyDescription.propertyOwned
            lblDescriptionText.text = propertyDescription.description
            val adapter = FacilitiesAdapter(propertyDescription.facilities)
            recyclerFacilities.layoutManager = GridLayoutManager(this@DescriptionScreen, 2, GridLayoutManager.VERTICAL, false)
            recyclerFacilities.adapter = adapter
            val imageViews = listOf<ImageView>(binding.galleryImage1, binding.galleryImage2, binding.galleryImage3, binding.galleryImage4)
            for (image in 0 until propertyDescription.imageList.size) {
                if (image != 4) {
                    imageViews[image].setImageResource(R.drawable.image)
                } else {
                    val remainingImage = "+${propertyDescription.imageList.size - 3}"
                    moreImages.text = remainingImage
                    break
                }
            }
        }
    }

}