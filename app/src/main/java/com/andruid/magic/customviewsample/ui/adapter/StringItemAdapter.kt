package com.andruid.magic.customviewsample.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.andruid.magic.customviewsample.ui.viewholder.StringItemViewHolder

class StringItemAdapter : ListAdapter<String, StringItemViewHolder>(StringDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StringItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: StringItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

class StringDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String) =
        oldItem == newItem
}