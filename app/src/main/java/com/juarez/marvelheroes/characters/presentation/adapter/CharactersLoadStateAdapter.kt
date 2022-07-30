package com.juarez.marvelheroes.characters.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.juarez.marvelheroes.databinding.CharactersLoadStateBinding

class CharactersLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CharactersLoadStateAdapter.ViewHolder>() {

    class ViewHolder(val binding: CharactersLoadStateBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        with(holder) {
            if (loadState is LoadState.Error) {
                binding.txtPagError.text = loadState.error.localizedMessage
            }
            binding.btnPagRetry.isVisible = (loadState is LoadState.Error)
            binding.txtPagError.isVisible = (loadState is LoadState.Error)
            binding.btnPagRetry.setOnClickListener { retry() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            CharactersLoadStateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}