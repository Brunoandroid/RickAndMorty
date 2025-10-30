package com.example.rickandmorty.screen.characterDetails

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.BottomSheetEpisodeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EpisodeBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetEpisodeBinding? = null
    private val binding get() = _binding!!

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
        val episodeNumber = requireArguments().getInt(ARG_EPISODE_NUMBER)
        binding.tvEpisodeNumber.text = getString(R.string.episode_number, episodeNumber)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_EPISODE_NUMBER = "arg_episode_number"

        fun newInstance(episodeNumber: Int): EpisodeBottomSheetDialogFragment {
            val fragment = EpisodeBottomSheetDialogFragment()
            fragment.arguments = Bundle().apply { putInt(ARG_EPISODE_NUMBER, episodeNumber) }
            return fragment
        }
    }
}
