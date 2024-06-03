package com.prathameshkumbhar.taskly.features.home_screen.domain.usecase

import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.utils.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class GetAllNotesLocallyUseCase @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository
){
    operator fun invoke() : Flow<NetworkResult<Flow<List<Note>>>>  = flow<NetworkResult<Flow<List<Note>>>>{
        emit(NetworkResult.Loading())
        val noteList = tasklyLocalStorageRepository.getAllNotes()
        if (noteList.toList().isNotEmpty()) {
            emit(NetworkResult.Success(data = noteList ))
        } else {
            emit(NetworkResult.Error("No notes found"))
        }
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}