package ru.pvkovalev.swipingnotes.presentation.fragments.add_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.data.data_source.NoteDatabase
import ru.pvkovalev.swipingnotes.databinding.FragmentAddBinding
import ru.pvkovalev.swipingnotes.domain.model.NoteModel
import ru.pvkovalev.swipingnotes.domain.utils.hideKeyboard
import ru.pvkovalev.swipingnotes.domain.utils.showKeyboard
import ru.pvkovalev.swipingnotes.presentation.fragments.edit_fragment.EditFragmentViewModel

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val addFragmentViewModel by lazy {
        ViewModelProvider(this)[AddFragmentViewModel::class.java]
    }

    private lateinit var noteContent: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteContent = binding.noteContentAddEditText

        noteContent.requestFocus()

        showKeyboard(context, noteContent)
        onBackPressed()

        binding.apply {
            buttonOkAddLayout.setOnClickListener {
                hideKeyboard(context, noteContent)
                insertNote()
            }
            buttonNotOkAddLayout.setOnClickListener {
                hideKeyboard(context, noteContent)
                findNavController().navigate(R.id.action_addFragment_to_mainFragment)
            }
        }
    }

    private fun insertNote() {
        val noteContent = noteContent.text.toString()
        if (noteContent.isNotEmpty()) {
            val noteItem = NoteModel(null, noteContent)
            addFragmentViewModel.insertNote(noteItem)
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