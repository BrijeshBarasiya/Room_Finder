package com.uipractice.roomfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.uipractice.roomfinder.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity(), View.OnClickListener {

    // Variable
    private lateinit var binding: ActivityDescriptionBinding

    // Override Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        binding.onClicked = this
        loadData()
//        window.statusBarColor = ContextCompat.getColor(this, R.color.s)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    // Function
    private fun loadData() {
        loadUserData()
        loadPropertyData()
        loadRecyclerData()
    }

    private fun loadUserData() {
        with(binding) {
            customToolbar.title = propertyDescription.propertyName
            val price = "Rs. ${propertyDescription.propertyPrice} /"
            customToolbar.subtitle = price
            userProfile.setImageResource(R.drawable.profile_photo)
            userName.text = propertyDescription.userName
            userLocation.text = propertyDescription.userLocation
        }
    }

    private fun loadPropertyData() {
        with(binding) {
            lblPropertyAddress.text = propertyDescription.propertyAddress
            lblPropertyArea.text = propertyDescription.propertyArea
            val applied ="${propertyDescription.propertyApplied} Applied | ${propertyDescription.propertyView} Views"
            lblPropertyApplied.text = applied
            lblIsAvailable.text = propertyDescription.isAvailable
            lblPropertyBy.text = propertyDescription.propertyOwned
            lblDescriptionText.text = propertyDescription.description
        }
    }

    private fun loadRecyclerData() {
        with(binding) {
            recyclerFacilities.layoutManager = GridLayoutManager(this@DescriptionActivity, 2, GridLayoutManager.VERTICAL, false)
            recyclerFacilities.adapter = FacilitiesAdapter(propertyDescription.facilities)
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

    override fun onClick(view: View) {
        when(view.id)  {
            binding.btnBookNow.id -> {
                Intent(this, SearchActivity::class.java).apply {
                    startActivity(this)
                }
            }
            binding.btnEmail.id -> resources.getString(R.string.message).createToast(this)
            binding.btnPhone.id -> resources.getString(R.string.phone).createToast(this)
        }
    }

}