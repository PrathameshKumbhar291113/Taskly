package com.prathameshkumbhar.taskly.features.home_screen.domain.usecase

import android.util.Log
import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyRemoteRepository
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class InsertNoteRemotelyUseCase @Inject constructor(
    private val tasklyRemoteRepository: TasklyRemoteRepository
) {
    operator fun invoke(noteBody: PostNoteToRemoteRequest): Flow<NetworkResult<Response<PostNoteToRemoteResponse>>> = flow<NetworkResult<Response<PostNoteToRemoteResponse>>>{
        emit(NetworkResult.Loading())
        if (noteBody.toString().isNotEmpty()) {
            emit(NetworkResult.Success(data =  tasklyRemoteRepository.postNoteFromRemote(noteBody)))
            Log.e("insert_note", "insertNoteToRemote USECASE SUCCESS: ${tasklyRemoteRepository.postNoteFromRemote(noteBody)} ---- ${noteBody.toString()}", )
        } else {
            emit(NetworkResult.Error("No note found"))
        }
    }.catch {
        Log.e("insert_note", "insertNoteToRemote  USECASE ERROR: ${it.message.toString()}", )
        emit(NetworkResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}