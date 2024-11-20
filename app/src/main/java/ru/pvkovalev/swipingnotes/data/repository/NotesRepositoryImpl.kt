package ru.pvkovalev.swipingnotes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.pvkovalev.swipingnotes.data.db.NotesDao
import ru.pvkovalev.swipingnotes.data.mapper.NotesMapper
import ru.pvkovalev.swipingnotes.domain.model.NotesItem
import ru.pvkovalev.swipingnotes.domain.repository.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao,
    private val mapper: NotesMapper
) : NotesRepository {

    override fun getNotes(): Flow<List<NotesItem>> =
        notesDao.getNotes().map {
            mapper.mapListDbModelToListEntity(it)
        }

    override suspend fun addNote(notesItem: NotesItem) {
        notesDao.addNote(mapper.mapEntityToDbModel(notesItem))
    }

    override suspend fun editNote(notesItem: NotesItem) {
        notesDao.editNote(mapper.mapEntityToDbModel(notesItem))
    }

    override suspend fun deleteNote(notesItem: NotesItem) {
        notesDao.deleteNote(mapper.mapEntityToDbModel(notesItem))
    }

    override suspend fun updateNotes(notesItem: NotesItem) {
        notesDao.updateNotes(mapper.mapEntityToDbModel(notesItem))
    }

    override suspend fun getMinNotesOrder() =
        notesDao.getMinNoteOrder()

}