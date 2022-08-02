package com.juarez.marvelheroes.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juarez.marvelheroes.databinding.ItemFavoriteBinding
import com.juarez.marvelheroes.utils.loadImage

class FavoritesAdapter(val onClickCharacter: (characterId: Int) -> Unit) :
    ListAdapter<CharacterEntity, FavoritesAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        with(holder) {
            binding.imgFavPhoto.loadImage(character.thumbnail)
            itemView.setOnClickListener {
                onClickCharacter(character.id)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<CharacterEntity>() {
        override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity) =
            oldItem == newItem
    }
}