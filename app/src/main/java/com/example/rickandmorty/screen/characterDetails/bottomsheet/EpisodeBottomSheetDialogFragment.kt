package com.example.rickandmorty.screen.characterDetails.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.BottomSheetEpisodeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetEpisodeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EpisodeViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(
            requireContext(),
            R.style.ThemeOverlay_RickAndMorty_BottomSheet
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val characterName = requireArguments().getString(ARG_CHARACTER_NAME)
        val episodeNumber = requireArguments().getInt(ARG_EPISODE_NUMBER)
        binding.tvEpisodeNumber.text = getString(R.string.episode_number, episodeNumber)

        viewModel.fetchEpisodeSummary(
            context?.getString(
                R.string.gemini_prompt,
                characterName,
                episodeNumber
            )
        )

        initObservers()
    }

    private fun initObservers() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading == true
            binding.tvSummary.isGone = isLoading == true
        }
        viewModel.summary.observe(viewLifecycleOwner) { text ->
            binding.tvSummary.text = text
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                binding.tvSummary.text = getString(R.string.error_loading_summary)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_CHARACTER_NAME = "arg_character_name"
        private const val ARG_EPISODE_NUMBER = "arg_episode_number"

        fun newInstance(characterName: String, episode: Int): EpisodeBottomSheetDialogFragment {
            val fragment = EpisodeBottomSheetDialogFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_CHARACTER_NAME, characterName)
                putInt(ARG_EPISODE_NUMBER, episode)
            }
            return fragment
        }
    }
}
