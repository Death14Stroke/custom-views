package com.andruid.magic.customviewsample.ui.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.andruid.magic.customviewsample.R
import com.andruid.magic.customviewsample.databinding.LayoutItemBinding

class StringItemViewHolder(private val binding: LayoutItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup): StringItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<LayoutItemBinding>(
                inflater,
                R.layout.layout_item,
                parent,
                false
            )
            return StringItemViewHolder(binding)
        }
    }

    fun bind(item: String) {
        binding.item = item
    }
}