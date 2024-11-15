package ru.pvkovalev.swipingnotes.domain.usecases

import ru.pvkovalev.swipingnotes.domain.model.NotesItem
import ru.pvkovalev.swipingnotes.domain.repository.NotesRepository
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend fun invoke(notesItem: NotesItem) = notesRepository.editNote(notesItem)
}