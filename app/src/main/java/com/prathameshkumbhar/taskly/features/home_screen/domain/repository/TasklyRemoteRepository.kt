package com.prathameshkumbhar.taskly.features.home_screen.domain.repository

import com.prathameshkumbhar.taskly.network.models.DeleteNoteFromRemoteResponse
import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteResponse
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteResponse
import retrofit2.Response

interface TasklyRemoteRepository {
    suspend fun getNotesFromRemote(limit: Int, skip: Int): Response<GetAllNotesFromRemoteResponse>
    suspend fun deleteNoteFromRemote(id: Int) : Response<DeleteNoteFromRemoteResponse>
    suspend fun postNoteFromRemote(postNoteToRemoteRequest: PostNoteToRemoteRequest): Response<PostNoteToRemoteResponse>
    suspend fun putNoteFromRemote(id: Int, putNoteToRemoteRequest: PutNoteToRemoteRequest): Response<PutNoteToRemoteResponse>
}