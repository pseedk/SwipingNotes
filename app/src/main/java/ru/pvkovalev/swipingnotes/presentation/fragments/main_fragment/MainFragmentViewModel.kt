package ru.pvkovalev.swipingnotes.presentation.fragments.main_fragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pvkovalev.swipingnotes.data.data_source.NoteDatabase
import ru.pvkovalev.swipingnotes.data.repository.NotesRoomDatabaseRepositoryImpl
import ru.pvkovalev.swipingnotes.domain.model.NoteModel
import ru.pvkovalev.swipingnotes.domain.utils.ROOM_REPOSITORY

class MainFragmentViewModel(private val application: Application) : ViewModel() {

    fun initDatabase() {
        val daoNote = NoteDatabase.getDatabase(application).noteDao()
        ROOM_REPOSITORY = NotesRoomDatabaseRepositoryImpl(daoNote)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> =
         NoteDatabase.getDatabase(application).noteDao().getAllNotes()

    fun deleteNote(noteModel: NoteModel) {
        viewModelScope.launch {
            ROOM_REPOSITORY.deleteNote(noteModel)
        }
    }

}