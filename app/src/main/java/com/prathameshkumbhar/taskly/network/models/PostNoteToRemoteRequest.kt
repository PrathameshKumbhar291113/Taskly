package com.prathameshkumbhar.taskly.network.models


data class PostNoteToRemoteRequest(
    val todo: String,
    val completed: Boolean,
    val userId: Int
)