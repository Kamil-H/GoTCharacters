package com.kamilh.gotcharacters.custom_views

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ItemViewAdapter constructor(
    private val onClick: (ItemView.Configuration) -> (Unit)
) : ListAdapter<ItemView.Configuration, ItemViewAdapter.ItemViewViewHolder>(
    object : DiffUtil.ItemCallback<ItemView.Configuration>() {
        override fun areItemsTheSame(oldItem: ItemView.Configuration, newItem: ItemView.Configuration): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemView.Configuration, newItem: ItemView.Configuration): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewViewHolder {
        return ItemViewViewHolder(ItemView(parent.context)).apply {
            itemView.setOnClickListener { onClick(getItem(adapterPosition)) }
        }
    }

    override fun onBindViewHolder(holder: ItemViewViewHolder, position: Int) {
        holder.view.setUp(getItem(position))
    }

    class ItemViewViewHolder(val view: ItemView) : RecyclerView.ViewHolder(view)
}
