package com.prathameshkumbhar.taskly.features.home_screen.domain.usecase

import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import javax.inject.Inject

class UpdateNoteLocallyUseCase @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository
) {
   /* operator fun invoke(note: Note): Flow<NetworkResult<Note>> = flow<NetworkResult<Note>>{
        emit(NetworkResult.Loading())
        if (note.toString().isNotEmpty()) {
            tasklyLocalStorageRepository.updateNote(note = Note().apply {
                _id = ObjectId(hexString = note._id.toHexString())
                noteTitle = note.noteTitle
            })
            emit(NetworkResult.Success(data = note ))
        } else {
            emit(NetworkResult.Error("No note found"))
        }
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)*/
}