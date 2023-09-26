package ru.pvkovalev.swipingnotes.presentation.fragments.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.FragmentMainBinding
import ru.pvkovalev.swipingnotes.presentation.NotesRecyclerViewAdapter
import java.util.Collections

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesRecyclerViewAdapter: NotesRecyclerViewAdapter

    private val mainFragmentViewModelFactory by lazy {
        MainFragmentViewModelFactory(requireActivity().application)
    }

    private val mainFragmentViewModel by lazy {
        ViewModelProvider(
            this,
            mainFragmentViewModelFactory
        )[MainFragmentViewModel::class.java]
    }

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
        mainFragmentViewModel.initDatabase()
        notesRecyclerViewAdapter = NotesRecyclerViewAdapter()
        binding.notesRecyclerView.apply {
            adapter = notesRecyclerViewAdapter
            mainFragmentViewModel
                .getAllNotes()
                .observe(viewLifecycleOwner) {
                    notesRecyclerViewAdapter.submitList(it.asReversed())
                }
        }
        setupOnNotesDragAndDrop(binding.notesRecyclerView)
    }

    private fun setupOnNotesDragAndDrop(notesRecyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or
                    ItemTouchHelper.DOWN or
                    ItemTouchHelper.START or
                    ItemTouchHelper.END,
            ItemTouchHelper.LEFT or
                    ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val list = notesRecyclerViewAdapter.currentList.toMutableList()
                Collections.swap(list, viewHolder.adapterPosition, target.adapterPosition)
                notesRecyclerViewAdapter.submitList(list)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = notesRecyclerViewAdapter.currentList[viewHolder.adapterPosition]
                mainFragmentViewModel.deleteNote(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(notesRecyclerView)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}