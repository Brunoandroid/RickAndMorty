package com.example.rickandmorty.screen.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.character.Character
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.screen.characters.adapter.CharacterPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val charactersViewModel: CharactersViewModel by viewModels()

    private lateinit var _bindingCharacters: FragmentCharactersBinding
    private val bindingCharacters: FragmentCharactersBinding get() = _bindingCharacters

    private val adapter = CharacterPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _bindingCharacters = FragmentCharactersBinding.inflate(inflater, container, false)

        bindingCharacters.recyclerView.adapter = adapter

        adapter.listener = object : CharacterPagingAdapter.Listener {
            override fun onCardClicked(character: Character, position: Int) {
                val action = CharactersFragmentDirections
                    .actionCharactersFragmentToCharacterDetailsFragment(character)
                findNavController().navigate(action)
            }
        }

        bindingCharacters.btnRetry.setOnClickListener {
            adapter.retry()
        }

        return bindingCharacters.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingCharacters.motionLayout.transitionToStart()

        ViewCompat.setOnApplyWindowInsetsListener(bindingCharacters.layoutInit) { view, insets ->
            val statusBar = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            view.updatePadding(top = statusBar.top)

            val minCollapsedHeight = statusBar.top + (48 * resources.displayMetrics.density).toInt()
            bindingCharacters.motionLayout.getConstraintSet(R.id.end)?.let { endSet ->
                endSet.constrainHeight(R.id.layoutInit, minCollapsedHeight)
                bindingCharacters.motionLayout.updateState(R.id.end, endSet)
            }
            insets
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    charactersViewModel.characters.collectLatest { pagingData ->
                        adapter.submitData(pagingData)
                    }
                }
                launch {
                    adapter.loadStateFlow.collectLatest { loadState ->
                        handleLoadState(loadState)
                    }
                }
            }
        }
    }

    private fun handleLoadState(loadState: CombinedLoadStates) {
        val isError = loadState.refresh is LoadState.Error
        val isEmpty = loadState.refresh is LoadState.NotLoading
                && loadState.append.endOfPaginationReached
                && adapter.itemCount == 0

        bindingCharacters.layoutError.isVisible = isError
        bindingCharacters.layoutEmpty.isVisible = isEmpty

        if (isError) {
            val error = (loadState.refresh as LoadState.Error).error
            bindingCharacters.tvErrorMessage.text = error.localizedMessage
        }
    }
}
