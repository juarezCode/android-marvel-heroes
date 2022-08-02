package com.juarez.marvelheroes.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.juarez.marvelheroes.databinding.FragmentFavoritesBinding
import com.juarez.marvelheroes.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    private val viewModel: FavoritesViewModel by viewModels()
    private val favoritesAdapter = FavoritesAdapter { onFavoriteCharacterClick(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFavorites.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = favoritesAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteCharacters
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collectLatest { favoritesAdapter.submitList(it) }
        }
    }

    private fun onFavoriteCharacterClick(characterId: Int) {
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToCharacterDetailFragment(
            characterId = characterId
        )
        findNavController().navigate(action)
    }
}