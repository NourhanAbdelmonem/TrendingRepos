package com.example.aqwastask.framework.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.aqwastask.business.entities.Item
import javax.inject.Inject

class RepoAdapter @Inject constructor() :
    ListAdapter<Item, RepoHolder>(RepoDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RepoHolder.from(parent)

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RepoDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            oldItem.repoLink == newItem.repoLink

        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }
}