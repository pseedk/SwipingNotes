package ru.pvkovalev.swipingnotes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.pvkovalev.swipingnotes.R
import ru.pvkovalev.swipingnotes.databinding.NoteItemBinding
import ru.pvkovalev.swipingnotes.domain.model.NotesItem

class NotesRecyclerViewAdapter :
    ListAdapter<NotesItem, NotesRecyclerViewAdapter.NotesViewHolder>(NotesDiff) {

    var onNotesItemClick: ((NotesItem) -> Unit)? = null

    class NotesViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    object NotesDiff : DiffUtil.ItemCallback<NotesItem>() {
        override fun areItemsTheSame(oldItem: NotesItem, newItem: NotesItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NotesItem, newItem: NotesItem) =
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
            tvNoteText.text = currentNote.content
            Picasso.get().load(currentNote.imageResId).fit()
                .placeholder(R.drawable.background)
                .into(ivNote)
        }
        holder.binding.root.setOnClickListener {
            onNotesItemClick?.invoke(currentNote)
        }
    }
}