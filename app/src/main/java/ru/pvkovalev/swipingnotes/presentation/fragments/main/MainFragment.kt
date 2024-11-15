package ru.pvkovalev.swipingnotes.presentation.fragments.main

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.launch
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.FragmentMainBinding
import ru.pvkovalev.swipingnotes.presentation.adapters.NotesRecyclerViewAdapter
import java.util.Collections

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val notesRecyclerViewAdapter: NotesRecyclerViewAdapter by lazy {
        NotesRecyclerViewAdapter()
    }

    private val mainFragmentViewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewLifecycleOwner.lifecycleScope.launch {
            mainFragmentViewModel.notes.collect {
                if (it.isNotEmpty()) {
                    binding.notesRecyclerView.smoothScrollToPosition(it.last().id)
                }
                notesRecyclerViewAdapter.submitList(it)
            }
        }
        binding.buttonAddNewNote.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.notesRecyclerView.apply {
            adapter = notesRecyclerViewAdapter
        }
        notesRecyclerViewAdapter.onNotesItemClick = {
            val direction = MainFragmentDirections.actionMainFragmentToEditFragment(it)
            findNavController().navigate(direction)
        }
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or
                            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val list = notesRecyclerViewAdapter.currentList.toMutableList()
                    Collections.swap(
                        list,
                        viewHolder.absoluteAdapterPosition,
                        target.absoluteAdapterPosition
                    )
                    mainFragmentViewModel.updateNotes(list)
                    notesRecyclerViewAdapter.submitList(list)
                    return false
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )

                    if (isCurrentlyActive) {
                        RecyclerViewSwipeDecorator.Builder(
                            c,
                            recyclerView,
                            viewHolder,
                            dX,
                            dY,
                            actionState,
                            isCurrentlyActive
                        )
                            .addBackgroundColor(
                                ContextCompat.getColor(
                                    requireActivity(),
                                    R.color.red
                                )
                            )
                            .addActionIcon(R.drawable.ic_delete)
                            .create()
                            .decorate()

                    }
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val list = notesRecyclerViewAdapter.currentList
                    val position = viewHolder.absoluteAdapterPosition
                    mainFragmentViewModel.deleteNote(list[position])
                    notesRecyclerViewAdapter.submitList(list)

                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.notesRecyclerView)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}