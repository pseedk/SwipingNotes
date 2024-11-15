package ru.pvkovalev.swipingnotes.domain.usecases

import ru.pvkovalev.swipingnotes.domain.repository.NotesRepository
import javax.inject.Inject

class GetMinNotesOrderUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend fun invoke() = notesRepository.getMinNotesOrder()
}