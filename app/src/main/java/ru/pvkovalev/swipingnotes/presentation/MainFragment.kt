package ru.pvkovalev.swipingnotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.FragmentMainBinding
import ru.pvkovalev.swipingnotes.domain.NoteModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    var testList = listOf(
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck"),
        NoteModel(0, "fuck")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNoteRecyclerView()
        binding.buttonAddNewNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initNoteRecyclerView() {
        val notesRecyclerViewAdapter = NotesRecyclerViewAdapter()
        binding.notesRecyclerView.apply {
            adapter = notesRecyclerViewAdapter
            layoutManager = GridLayoutManager(context, 3)
            notesRecyclerViewAdapter.submitList(testList)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()

    }
}