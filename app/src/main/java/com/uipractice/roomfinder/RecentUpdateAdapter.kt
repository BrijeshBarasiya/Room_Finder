package com.uipractice.roomfinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uipractice.roomfinder.databinding.RecyclerRecentUpdateBinding

class RecentUpdateAdapter (
    private val elements: List<PropertyDescription>,
    private val elementCount: Int = elements.size
): RecyclerView.Adapter<RecentUpdateAdapter.RecentUpdateViewHolder>() {

    inner class RecentUpdateViewHolder(val binding: RecyclerRecentUpdateBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentUpdateViewHolder {
        val binding = RecyclerRecentUpdateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentUpdateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentUpdateViewHolder, position: Int) {
        val element = elements[position]
        with(holder.binding) {
            lblPropertyName.text = element.propertyName
            val price = "Rs. ${element.propertyPrice} /"
            lblPropertyPrice.text = price
            lblPropertyLocation.text = element.propertyArea
            propertyImage.setImageResource(R.drawable.image)
            lblStatus.text = element.isAvailable
            val text = "10 Applied | Kathmandu"
            lblPropertyApplied.text = text
        }
    }

    override fun getItemCount(): Int {
        return elementCount
    }

}