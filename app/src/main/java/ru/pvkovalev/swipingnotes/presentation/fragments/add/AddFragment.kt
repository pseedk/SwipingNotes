package ru.pvkovalev.swipingnotes.presentation.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.FragmentAddBinding
import ru.pvkovalev.swipingnotes.utils.hideKeyboard
import ru.pvkovalev.swipingnotes.utils.showKeyboard

@AndroidEntryPoint
class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val addFragmentViewModel: AddFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteEditText = binding.noteContentEditText
        addFragmentViewModel.backgroundImage.observe(viewLifecycleOwner) {
            binding.addLayout.setBackgroundResource(it)
        }
        noteEditText.requestFocus()
        showKeyboard(context, noteEditText)
        onBackPressed()

        binding.apply {
            buttonOkAddFragment.setOnClickListener {
                hideKeyboard(context, noteEditText)
                addNote(noteEditText)
            }
            buttonNotOkAddFragment.setOnClickListener {
                hideKeyboard(context, noteEditText)
                findNavController().navigate(R.id.action_addFragment_to_mainFragment)
            }
        }
    }

    private fun addNote(noteEditText: TextInputEditText) {
        val noteContent = noteEditText.text.toString()
        if (noteContent.isNotEmpty()) {
            addFragmentViewModel.addNote(
                noteContent = noteContent
            )
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        } else {
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = AddFragment()

    }

    private fun onBackPressed() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_addFragment_to_mainFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

}