package com.prathameshkumbhar.taskly.features.home_screen.data.repository

import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyRemoteRepository
import com.prathameshkumbhar.taskly.network.ApiCommunicator
import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
import retrofit2.Response
import javax.inject.Inject

class TasklyRemoteRepoImpl @Inject constructor(
    private val apiCommunicator: ApiCommunicator
) : TasklyRemoteRepository{
    override suspend fun getNotesFromRemote(): Response<GetAllNotesFromRemoteResponse> {
        return apiCommunicator.getNotesFromRemote()
    }
}