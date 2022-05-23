package com.uipractice.roomfinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    private val dataItems: List<T>,
    private val count: Int
): RecyclerView.Adapter<BaseAdapter<T>.BaseAdapterViewHolder>() {

    inner class BaseAdapterViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapterViewHolder {
        val bindingA: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return BaseAdapterViewHolder(bindingA)
    }

    override fun onBindViewHolder(holder: BaseAdapterViewHolder, position: Int) {
        val element = dataItems[position]
        holder.binding.setVariable(BR.data, element)
    }

    override fun getItemCount(): Int {
        return count
    }

}