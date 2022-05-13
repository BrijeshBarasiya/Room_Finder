package com.uipractice.roomfinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uipractice.roomfinder.databinding.RecyclerRecentPropertiesBinding

class RecentPropertiesAdapter(
    private val elements: List<PropertyDescription>,
    private val countElement: Int = elements.size
): RecyclerView.Adapter<RecentPropertiesAdapter.RecentPropertiesViewHolder>() {

    inner class RecentPropertiesViewHolder(val binding: RecyclerRecentPropertiesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPropertiesViewHolder {
        val binding = RecyclerRecentPropertiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentPropertiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentPropertiesViewHolder, position: Int) {
        val element = elements[position]
        with(holder.binding) {
            lblPropertyName.text = element.propertyName
            val price = "Rs. ${element.propertyPrice} /"
            lblPropertyPrice.text = price
            lblPropertyLocation.text = element.propertyArea
            propertyImage.setImageResource(R.drawable.image)
            lblStatus.text = element.isAvailable
        }
    }

    override fun getItemCount(): Int {
        return countElement
    }

}