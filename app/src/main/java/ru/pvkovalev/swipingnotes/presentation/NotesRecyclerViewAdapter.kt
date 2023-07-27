package ru.pvkovalev.swipingnotes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.NoteItemBinding
import ru.pvkovalev.swipingnotes.domain.NoteModel

class NotesRecyclerViewAdapter :
    ListAdapter<NoteModel, NotesRecyclerViewAdapter.NotesViewHolder>(NotesDiff) {

    class NotesViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    object NotesDiff : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = getItem(position)
        holder.binding.apply {
            tvNoteText.text = currentNote.noteText
        }
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_editFragment)
        }
    }
}