package com.prathameshkumbhar.taskly.network

import com.prathameshkumbhar.taskly.network.models.DeleteNoteFromRemoteResponse
import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteResponse
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteResponse
import com.prathameshkumbhar.taskly.utils.TasklyConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCommunicator {

    @GET(TasklyConstants.GET_ALL_NOTES_FROM_REMOTE)
    suspend fun getNotesFromRemote(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int
    ): Response<GetAllNotesFromRemoteResponse>

    @DELETE(TasklyConstants.DELETE_NOTE_FROM_REMOTE)
    suspend fun deleteNoteFromRemote(@Path("id") id: Int): Response<DeleteNoteFromRemoteResponse>

    @Headers("Content-Type: application/json")
    @POST(TasklyConstants.POST_NOTE_TO_REMOTE)
    suspend fun postNoteToRemote(
        @Body postNoteToRemoteRequest: PostNoteToRemoteRequest
    ): Response<PostNoteToRemoteResponse>

    @Headers("Content-Type: application/json")
    @PUT(TasklyConstants.PUT_NOTE_TO_REMOTE)
    suspend fun putNoteToRemote(
        @Path("id") id: Int,
        @Body putNoteToRemoteRequest: PutNoteToRemoteRequest
    ): Response<PutNoteToRemoteResponse>
}