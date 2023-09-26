package ru.pvkovalev.swipingnotes.presentation.fragments.edit_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.FragmentEditBinding
import ru.pvkovalev.swipingnotes.domain.model.NoteModel
import ru.pvkovalev.swipingnotes.domain.utils.hideKeyboard
import ru.pvkovalev.swipingnotes.domain.utils.showKeyboard

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val editFragmentViewModel by lazy {
        ViewModelProvider(this)[EditFragmentViewModel::class.java]
    }

    private val editFragmentArgs: EditFragmentArgs by navArgs()
    private lateinit var currentNote: NoteModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentNote = editFragmentArgs.note!!
        binding.noteContentEditText.setText(currentNote.noteText)
        onBackPressed()
        binding.apply {
            buttonOkEditFragment.setOnClickListener {
                hideKeyboard(context, noteContentEditText)
                editNote()
            }
            buttonNotOkEditFragment.setOnClickListener {
                hideKeyboard(context, noteContentEditText)
                deleteNote()
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

    private fun editNote() {
        val noteContent = binding.noteContentEditText.text.toString()
        if (noteContent.isNotEmpty()) {
            val note = NoteModel(currentNote.id, noteContent)
            editFragmentViewModel.updateNote(note)
            findNavController().navigate(R.id.action_editFragment_to_mainFragment)
        } else {
            deleteNote()
        }
    }

    private fun deleteNote() {
        editFragmentViewModel.deleteNote(currentNote)
        findNavController().navigate(R.id.action_editFragment_to_mainFragment)
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