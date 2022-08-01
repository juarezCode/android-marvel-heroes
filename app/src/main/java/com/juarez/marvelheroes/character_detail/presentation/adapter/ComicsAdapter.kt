package com.juarez.marvelheroes.character_detail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juarez.marvelheroes.characters.domain.ComicItem
import com.juarez.marvelheroes.databinding.ItemComicBinding

class ComicsAdapter : ListAdapter<ComicItem, ComicsAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = getItem(position)
        with(holder) {
            binding.txtComicName.text = "${position + 1} - ${comic.name}"
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<ComicItem>() {
        override fun areItemsTheSame(oldItem: ComicItem, newItem: ComicItem) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: ComicItem, newItem: ComicItem) =
            oldItem == newItem
    }
}