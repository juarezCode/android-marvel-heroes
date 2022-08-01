package com.juarez.marvelheroes.character_detail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juarez.marvelheroes.characters.domain.SerieItem
import com.juarez.marvelheroes.databinding.ItemSerieBinding

class SeriesAdapter : ListAdapter<SerieItem, SeriesAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: ItemSerieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSerieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val serie = getItem(position)
        with(holder) {
            binding.txtSerieName.text = "${position + 1} - ${serie.name}"
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<SerieItem>() {
        override fun areItemsTheSame(oldItem: SerieItem, newItem: SerieItem) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: SerieItem, newItem: SerieItem) =
            oldItem == newItem
    }
}