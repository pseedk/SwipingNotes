package ru.pvkovalev.swipingnotes.presentation.fragments.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.pvkovalev.swipingnotes.domain.model.NotesItem
import ru.pvkovalev.swipingnotes.domain.usecases.DeleteNoteUseCase
import ru.pvkovalev.swipingnotes.domain.usecases.EditNoteUseCase
import javax.inject.Inject

@HiltViewModel
class EditFragmentViewModel @Inject constructor(
    private val editNoteUseCase: EditNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    fun editNote(notesItem: NotesItem) {
        viewModelScope.launch {
            editNoteUseCase.invoke(notesItem)
        }
    }

    fun deleteNote(notesItem: NotesItem) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(notesItem)
        }
    }
}