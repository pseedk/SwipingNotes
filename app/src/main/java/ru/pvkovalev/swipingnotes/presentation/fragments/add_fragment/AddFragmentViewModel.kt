package ru.pvkovalev.swipingnotes.presentation.fragments.add_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pvkovalev.swipingnotes.domain.model.NoteModel
import ru.pvkovalev.swipingnotes.domain.utils.ROOM_REPOSITORY

class AddFragmentViewModel : ViewModel() {

    fun insertNote(noteModel: NoteModel) {
        viewModelScope.launch {
            ROOM_REPOSITORY.insertNote(noteModel)
        }
    }

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch {
            ROOM_REPOSITORY.deleteNote(noteModel)
        }
    }
}