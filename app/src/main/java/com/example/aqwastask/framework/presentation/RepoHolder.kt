package com.example.aqwastask.framework.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aqwastask.business.entities.Item
import com.example.aqwastask.databinding.ItemRepoBinding

class RepoHolder(private val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Item) {
        binding.item = item
        binding.itemRepoLayout.setOnClickListener {
            binding.isExpanded = !item.isExpanded
            item.isExpanded = !item.isExpanded
        }
    }

    companion object {
        fun from(
            parent: ViewGroup
        ): RepoHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRepoBinding.inflate(layoutInflater, parent, false)
            return RepoHolder(binding)
        }
    }
}