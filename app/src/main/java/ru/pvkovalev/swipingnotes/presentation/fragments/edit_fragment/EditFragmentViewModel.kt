package ru.pvkovalev.swipingnotes.presentation.fragments.edit_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pvkovalev.swipingnotes.data.data_source.NoteDatabase
import ru.pvkovalev.swipingnotes.data.repository.NotesRoomDatabaseRepositoryImpl
import ru.pvkovalev.swipingnotes.domain.model.NoteModel
import ru.pvkovalev.swipingnotes.domain.utils.ROOM_REPOSITORY

class EditFragmentViewModel() : ViewModel() {

    fun updateNote(noteModel: NoteModel) {
        viewModelScope.launch {
            ROOM_REPOSITORY.updateNote(noteModel)
        }
    }

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch {
            ROOM_REPOSITORY.deleteNote(noteModel)
        }
    }
}