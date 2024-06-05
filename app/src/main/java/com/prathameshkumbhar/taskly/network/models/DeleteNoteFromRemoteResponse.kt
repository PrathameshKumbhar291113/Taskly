package com.prathameshkumbhar.taskly.network.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DeleteNoteFromRemoteResponse(
    @SerializedName("completed")
    var completed: Boolean?, // true
    @SerializedName("deletedOn")
    var deletedOn: String?,
    @SerializedName("id")
    var id: Int?, // 1
    @SerializedName("isDeleted")
    var isDeleted: Boolean?, // true
    @SerializedName("todo")
    var todo: String?, // Do something nice for someone I care about
    @SerializedName("userId")
    var userId: Int? // 26
) : Parcelable