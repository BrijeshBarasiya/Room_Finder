package com.uipractice.roomfinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uipractice.roomfinder.databinding.RecylerFacilitiesCellBinding

class FacilitiesAdapter(
    private val elements: List<String>
): RecyclerView.Adapter<FacilitiesAdapter.FacilitiesHolder>() {

    inner class FacilitiesHolder(val binding: RecylerFacilitiesCellBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacilitiesHolder {
        val binding = RecylerFacilitiesCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FacilitiesHolder(binding)
    }

    override fun onBindViewHolder(holder: FacilitiesHolder, position: Int) {
        with(holder.binding) {
            lblFacility.text = elements[position]
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

}