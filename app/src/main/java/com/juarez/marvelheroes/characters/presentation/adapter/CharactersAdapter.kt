package com.juarez.marvelheroes.characters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.juarez.marvelheroes.characters.domain.Character
import com.juarez.marvelheroes.databinding.ItemCharacterBinding
import com.juarez.marvelheroes.utils.loadImage

class CharactersAdapter : PagingDataAdapter<Character, CharactersAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        with(holder.binding) {
            imgCharThumbnail.loadImage("${character?.thumbnail?.path}.${character?.thumbnail?.extension}")
            txtCharName.text = character?.name ?: "unknown"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    object DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}