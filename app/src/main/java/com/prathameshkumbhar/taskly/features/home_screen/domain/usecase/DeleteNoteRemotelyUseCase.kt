package com.prathameshkumbhar.taskly.features.home_screen.domain.usecase

import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyRemoteRepository
import com.prathameshkumbhar.taskly.network.models.DeleteNoteFromRemoteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class DeleteNoteRemotelyUseCase @Inject constructor(
    private val tasklyRemoteRepository: TasklyRemoteRepository
) {
    operator fun invoke(id: Int): Flow<NetworkResult<Response<DeleteNoteFromRemoteResponse>>> = flow<NetworkResult<Response<DeleteNoteFromRemoteResponse>>>{
        emit(NetworkResult.Loading())
        if (id.toString().isNotEmpty()) {
            emit(NetworkResult.Success(data =  tasklyRemoteRepository.deleteNoteFromRemote(id)))
        } else {
            emit(NetworkResult.Error("No note found"))
        }
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}