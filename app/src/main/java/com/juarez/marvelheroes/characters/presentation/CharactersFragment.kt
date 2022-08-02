package com.juarez.marvelheroes.characters.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.juarez.marvelheroes.characters.domain.Character
import com.juarez.marvelheroes.characters.presentation.adapter.CharactersAdapter
import com.juarez.marvelheroes.characters.presentation.adapter.CharactersLoadStateAdapter
import com.juarez.marvelheroes.databinding.FragmentCharactersBinding
import com.juarez.marvelheroes.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {

    private val viewModel: CharactersViewModel by viewModels()
    private val charactersAdapter = CharactersAdapter { onItemClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabFavorites.isVisible = false

        binding.recyclerCharacters.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = charactersAdapter.withLoadStateFooter(
                footer = CharactersLoadStateAdapter(charactersAdapter::retry)
            )
        }

        binding.fabFavorites.setOnClickListener {
            onFabFavoritesClick()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.characters
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collectLatest { data -> charactersAdapter.submitData(data) }
        }

        charactersAdapter.addLoadStateListener {
            binding.shimmerContainer.isVisible = it.refresh is LoadState.Loading
            binding.fabFavorites.isVisible = it.refresh !is LoadState.Loading

//            if (it.refresh is LoadState.Error) {
//                binding.containerHeroesError.isVisible = true
//                val error = (it.refresh as LoadState.Error).error.localizedMessage
//                binding.txtHeroesError.text = error
//            }
        }
    }

    private fun onFabFavoritesClick() {
        val action = CharactersFragmentDirections.actionCharactersFragmentToFavoritesFragment()
        findNavController().navigate(action)
    }

    private fun onItemClicked(character: Character) {
        val action =
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(
                characterId = character.id
            )
        findNavController().navigate(action)
    }
}