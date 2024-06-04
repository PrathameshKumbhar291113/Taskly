package com.prathameshkumbhar.taskly.features.home_screen.domain.repository

import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
import retrofit2.Response

interface TasklyRemoteRepository {
    suspend fun getNotesFromRemote(): Response<GetAllNotesFromRemoteResponse>
}