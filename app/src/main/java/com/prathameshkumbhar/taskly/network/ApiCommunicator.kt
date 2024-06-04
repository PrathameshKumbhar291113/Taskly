package com.prathameshkumbhar.taskly.network

import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
import com.prathameshkumbhar.taskly.utils.TasklyConstants
import retrofit2.Response
import retrofit2.http.GET

interface ApiCommunicator {

    @GET(TasklyConstants.GET_ALL_NOTES_FROM_REMOTE)
    suspend fun getNotesFromRemote(): Response<GetAllNotesFromRemoteResponse>
}