package com.prathameshkumbhar.taskly.features.home_screen.domain.usecase

import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyRemoteRepository
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UpdateNoteRemotelyUseCase @Inject constructor(
    private val tasklyRemoteRepository: TasklyRemoteRepository
) {
    operator fun invoke(id: Int, putNoteToRemoteRequest: PutNoteToRemoteRequest): Flow<NetworkResult<Response<PutNoteToRemoteResponse>>> = flow<NetworkResult<Response<PutNoteToRemoteResponse>>>{
        emit(NetworkResult.Loading())
        if (id.toString().isNotEmpty()) {
            emit(NetworkResult.Success(data =  tasklyRemoteRepository.putNoteFromRemote(id, putNoteToRemoteRequest)))
        } else {
            emit(NetworkResult.Error("No note found"))
        }
    }.catch {
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}