package ru.pvkovalev.swipingnotes.domain.usecases

import ru.pvkovalev.swipingnotes.domain.repository.NotesRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    fun invoke() = notesRepository.getNotes()
}