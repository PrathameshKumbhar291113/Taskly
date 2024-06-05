package com.prathameshkumbhar.taskly.features.home_screen.data.repository

import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyRemoteRepository
import com.prathameshkumbhar.taskly.network.ApiCommunicator
import com.prathameshkumbhar.taskly.network.models.DeleteNoteFromRemoteResponse
import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteResponse
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteResponse
import retrofit2.Response
import javax.inject.Inject

class TasklyRemoteRepoImpl @Inject constructor(
    private val apiCommunicator: ApiCommunicator
) : TasklyRemoteRepository{
    override suspend fun getNotesFromRemote(limit: Int, skip: Int): Response<GetAllNotesFromRemoteResponse> {
        return apiCommunicator.getNotesFromRemote(limit, skip)
    }

    override suspend fun deleteNoteFromRemote(id: Int): Response<DeleteNoteFromRemoteResponse> {
        return apiCommunicator.deleteNoteFromRemote(id)
    }

    override suspend fun postNoteFromRemote(postNoteToRemoteRequest: PostNoteToRemoteRequest): Response<PostNoteToRemoteResponse> {
        return apiCommunicator.postNoteToRemote(postNoteToRemoteRequest)
    }

    override suspend fun putNoteFromRemote(id: Int, putNoteToRemoteRequest: PutNoteToRemoteRequest): Response<PutNoteToRemoteResponse> {
        return apiCommunicator.putNoteToRemote(id, putNoteToRemoteRequest)
    }
}