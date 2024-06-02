package com.prathameshkumbhar.taskly.features.home_screen.domain.usecase

import com.prathameshkumbhar.taskly.common.NetworkResult
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.utils.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertNoteLocallyUseCase @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository
) {
    operator fun invoke(note: Note): Flow<NetworkResult<Note>> = flow<NetworkResult<Note>>{
        emit(NetworkResult.Loading())
        if (note.toString().isNotEmpty()) {
            tasklyLocalStorageRepository.insertNote(note = note)
            emit(NetworkResult.Success(data = note ))
        } else {
            emit(NetworkResult.Error("No note found"))
        }
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}