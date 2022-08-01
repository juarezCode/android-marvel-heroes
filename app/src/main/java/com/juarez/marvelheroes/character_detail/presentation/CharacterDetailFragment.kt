package com.juarez.marvelheroes.character_detail.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.juarez.marvelheroes.character_detail.presentation.adapter.ComicsAdapter
import com.juarez.marvelheroes.character_detail.presentation.adapter.SeriesAdapter
import com.juarez.marvelheroes.databinding.FragmentCharacterDetailBinding
import com.juarez.marvelheroes.utils.BaseFragment
import com.juarez.marvelheroes.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding>(FragmentCharacterDetailBinding::inflate) {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailFragmentArgs by navArgs()
    private val comicsAdapter = ComicsAdapter()
    private val seriesAdapter = SeriesAdapter()
    private var characterId = 0

    override fun onCreateViewReady() {
        super.onCreateViewReady()
        characterId = args.characterId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterDetail(characterId)

        binding.recyclerComics.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = comicsAdapter
        }

        binding.recyclerSeries.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = seriesAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.characterState.collectLatest {
                    when (it) {
                        is CharacterState.Error -> {
                            binding.progressCharDetail.isVisible = false
                        }
                        is CharacterState.Loading -> {
                            binding.progressCharDetail.isVisible = true
                        }
                        is CharacterState.Success -> {
                            it.data.let { character ->
                                binding.progressCharDetail.isVisible = false
                                binding.imgCharPhoto.loadImage(
                                    "${character.thumbnail.path}.${it.data.thumbnail.extension}"
                                )
                                binding.txtCharName.text = character.name
                                comicsAdapter.submitList(character.comics.items)
                                seriesAdapter.submitList(character.series.items)
                            }
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}