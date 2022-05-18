package com.uipractice.roomfinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uipractice.roomfinder.databinding.RecyclerLocationsBinding

class LocationsAdapter(
    private val elements: List<CitiesAndProperties>,
    private val elementCount: Int = elements.size,
): RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder>() {

    inner class LocationsViewHolder(val binding: RecyclerLocationsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val binding = RecyclerLocationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val element = elements[position]
        with(holder.binding) {
            lblAreaName.text = element.cityName
            val totalProperties = "${element.totalProperties} Found"
            lblTotalProperty.text = totalProperties
        }
    }

    override fun getItemCount(): Int {
        return elementCount
    }

}