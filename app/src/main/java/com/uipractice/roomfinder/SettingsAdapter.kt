package com.uipractice.roomfinder

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.uipractice.roomfinder.databinding.RecyclerSettingBinding

class SettingsAdapter(
    private val context: Context,
    private val elements: MutableList<SettingsModelClass>,
): RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    inner class SettingsViewHolder(val binding: RecyclerSettingBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val binding = RecyclerSettingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val element = elements[position]
        with(holder.binding) {
            imgSettingIcon.setImageResource(element.icon)
            lblSettingName.text = element.settingName
            lblSettingDescription.text = element.settingDescription
            lblSettingDescription.isVisible = element.isExpanded
            if (element.isExpanded) {
                layoutSingleCell.backgroundTintList = ContextCompat.getColorStateList(context, R.color.light_primary_color)
            } else {
                layoutSingleCell.backgroundTintList = ContextCompat.getColorStateList(context, R.color.white)
            }
            holder.itemView.setOnClickListener {
                if (position == elements.size-1) {
                    SharedPreference.removeAllSharedPreference(context)
                    Intent(context, ApiIdentifierActivity::class.java).apply {
                        context.startActivity(this)
                    }
                } else {
                    for (index in elements.indices) {
                        elements[index].isExpanded = false
                    }
                    element.isExpanded = true
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

}