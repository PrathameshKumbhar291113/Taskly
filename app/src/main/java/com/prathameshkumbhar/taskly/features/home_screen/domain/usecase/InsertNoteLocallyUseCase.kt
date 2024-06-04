package com.prathameshkumbhar.taskly.features.home_screen.domain.usecase

import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import javax.inject.Inject

class InsertNoteLocallyUseCase @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository
) {
    /*operator fun invoke(note: Note): Flow<NetworkResult<Note>> = flow<NetworkResult<Note>>{
        emit(NetworkResult.Loading())
        if (note.toString().isNotEmpty()) {
            tasklyLocalStorageRepository.insertNote(note = Note().apply {
                noteTitle = note.noteTitle
                noteDescription = note.noteDescription
                noteCreatedOn = noteCreatedOn.toString()
            })
            emit(NetworkResult.Success(data = note ))
        } else {
            emit(NetworkResult.Error("No note found"))
        }
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)*/
}