package com.uipractice.roomfinder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uipractice.roomfinder.databinding.RecyclerChipMenuBinding

class ChipsMenuAdapter(
    private val context: Context,
    private val elements: List<String>
): RecyclerView.Adapter<ChipsMenuAdapter.ChipsMenuViewHolder>() {

    inner class ChipsMenuViewHolder(val binding: RecyclerChipMenuBinding): RecyclerView.ViewHolder(binding.root)
    private lateinit var previousCell: RecyclerChipMenuBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipsMenuViewHolder {
        val binding = RecyclerChipMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChipsMenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChipsMenuViewHolder, position: Int) {
        with(holder.binding) {
            previousCell = this
            lblMenu.text = elements[position]
            screenChips.setOnClickListener {
                previousCell.lblMenu.backgroundTintList = ContextCompat.getColorStateList(context, R.color.gray_5)
                previousCell = this
                lblMenu.backgroundTintList = ContextCompat.getColorStateList(context, R.color.light_primary_color)
            }
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

}