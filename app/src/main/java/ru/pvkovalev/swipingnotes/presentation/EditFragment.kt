package ru.pvkovalev.swipingnotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.FragmentEditBinding
import ru.pvkovalev.swipingnotes.utils.APP_ACTIVITY
import ru.pvkovalev.swipingnotes.utils.hideKeyboard
import ru.pvkovalev.swipingnotes.utils.showKeyboard

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        binding.apply {
            buttonOkEditFragment.setOnClickListener {
                hideKeyboard(context, noteContentEditText)
                findNavController().navigate(R.id.action_editFragment_to_mainFragment)

            }
            buttonNotOkEditFragment.setOnClickListener {
                hideKeyboard(context, noteContentEditText)
                findNavController().navigate(R.id.action_editFragment_to_mainFragment)
            }
            noteContentEditLayout.setOnClickListener {
                noteContentEditText.requestFocus()
                showKeyboard(context, noteContentEditText)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = EditFragment()

    }

    private fun onBackPressed() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_editFragment_to_mainFragment)
            }
        }
        APP_ACTIVITY.onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

}