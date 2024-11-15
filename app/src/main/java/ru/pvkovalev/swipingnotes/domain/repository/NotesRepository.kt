package ru.pvkovalev.swipingnotes.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.pvkovalev.swipingnotes.domain.model.NotesItem

interface NotesRepository {

    fun getNotes(): Flow<List<NotesItem>>
    suspend fun addNote(notesItem: NotesItem)
    suspend fun editNote(notesItem: NotesItem)
    suspend fun deleteNote(notesItem: NotesItem)
    suspend fun updateNotes(notesItem: NotesItem)
    suspend fun getMinNotesOrder(): Int?
}