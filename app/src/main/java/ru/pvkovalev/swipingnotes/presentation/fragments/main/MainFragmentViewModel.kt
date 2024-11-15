package ru.pvkovalev.swipingnotes.presentation.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.pvkovalev.swipingnotes.domain.model.NotesItem
import ru.pvkovalev.swipingnotes.domain.usecases.DeleteNoteUseCase
import ru.pvkovalev.swipingnotes.domain.usecases.GetNotesUseCase
import ru.pvkovalev.swipingnotes.domain.usecases.UpdateNotesUseCase
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNotesUseCase: UpdateNotesUseCase
) : ViewModel() {

    val notes: Flow<List<NotesItem>> = getNotesUseCase.invoke()

    fun deleteNote(note: NotesItem) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(note)
        }
    }

    fun updateNotes(notes: List<NotesItem>) {
        for (i in notes.indices) {
            notes[i].notePosition = i
        }
        viewModelScope.launch {
            notes.forEach {
                updateNotesUseCase.invoke(it)
            }
        }
    }
}