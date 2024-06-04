package com.prathameshkumbhar.taskly.network.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetAllNotesFromRemoteResponse(
    @SerializedName("limit")
    var limit: Int?, // 30
    @SerializedName("skip")
    var skip: Int?, // 0
    @SerializedName("todos")
    var todos: List<Todo>?,
    @SerializedName("total")
    var total: Int? // 150
) : Parcelable {
    @Parcelize
    data class Todo(
        @SerializedName("completed")
        var completed: Boolean?, // true
        @SerializedName("id")
        var id: Int?, // 1
        @SerializedName("todo")
        var todo: String?, // Do something nice for someone I care about
        @SerializedName("userId")
        var userId: Int? // 26
    ) : Parcelable
}