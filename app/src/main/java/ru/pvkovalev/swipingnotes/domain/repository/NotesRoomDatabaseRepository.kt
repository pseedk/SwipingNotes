package ru.pvkovalev.swipingnotes.domain.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.pvkovalev.swipingnotes.domain.model.NoteModel

interface NotesRoomDatabaseRepository {

    fun getAllNotes(): LiveData<List<NoteModel>>
    suspend fun insertNote(noteModel: NoteModel)
    suspend fun updateNote(noteModel: NoteModel)
    suspend fun deleteNote(noteModel: NoteModel)
}