package ru.pvkovalev.swipingnotes.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.pvkovalev.swipingnotes.data.data_source.NoteDao
import ru.pvkovalev.swipingnotes.domain.model.NoteModel
import ru.pvkovalev.swipingnotes.domain.repository.NotesRoomDatabaseRepository

class NotesRoomDatabaseRepositoryImpl(private val noteDao: NoteDao) : NotesRoomDatabaseRepository {

    override fun getAllNotes(): LiveData<List<NoteModel>> = noteDao.getAllNotes()

    override suspend fun insertNote(noteModel: NoteModel) {
        noteDao.insertNote(noteModel)
    }

    override suspend fun updateNote(noteModel: NoteModel) {
        noteDao.updateNote(noteModel)
    }

    override suspend fun deleteNote(noteModel: NoteModel) {
        noteDao.deleteNote(noteModel)
    }

}