package ru.pvkovalev.swipingnotes.presentation.fragments.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.FragmentEditBinding
import ru.pvkovalev.swipingnotes.utils.hideKeyboard
import ru.pvkovalev.swipingnotes.utils.showKeyboard

@AndroidEntryPoint
class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val editFragmentViewModel: EditFragmentViewModel by viewModels()

    private val editFragmentArgs: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentNote = editFragmentArgs.note
        binding.noteContentEditText.setText(currentNote.content)
        onBackPressed()
        showKeyboard(context, binding.noteContentEditText)
        binding.apply {
            editLayout.setBackgroundResource(currentNote.imageBackResId)
            buttonOkEditFragment.setOnClickListener {
                hideKeyboard(context, noteContentEditText)
                val noteContent = binding.noteContentEditText.text.toString()
                if (noteContent.isNotEmpty()) {
                    val note = currentNote.copy(content = noteContent)
                    editFragmentViewModel.editNote(note)
                    findNavController().navigate(R.id.action_editFragment_to_mainFragment)
                }
            }
            buttonNotOkEditFragment.setOnClickListener {
                hideKeyboard(context, noteContentEditText)
                editFragmentViewModel.deleteNote(currentNote)
                findNavController().navigate(R.id.action_editFragment_to_mainFragment)
            }
            noteContentEditLayout.setOnClickListener {
                noteContentEditText.requestFocus()
                noteContentEditText.setSelection(noteContentEditText.length())
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
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

}