package com.prathameshkumbhar.taskly.features.home_screen.domain.usecase

import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyRemoteRepository
import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class GetAllNotesRemotelyUseCase @Inject constructor(
    private val tasklyRemoteRepository: TasklyRemoteRepository
) {
    operator fun invoke(limit: Int, skip: Int): Flow<NetworkResult<Response<GetAllNotesFromRemoteResponse>>> =
        flow<NetworkResult<Response<GetAllNotesFromRemoteResponse>>> {
            emit(NetworkResult.Loading())

            emit(NetworkResult.Success(data = tasklyRemoteRepository.getNotesFromRemote(limit,skip)))

        }.catch {
            emit(NetworkResult.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)
}