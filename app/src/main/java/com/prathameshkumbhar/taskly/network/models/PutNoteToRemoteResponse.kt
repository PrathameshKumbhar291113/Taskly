package com.prathameshkumbhar.taskly.network.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PutNoteToRemoteResponse(
    @SerializedName("completed")
    var completed: Boolean?, // false
    @SerializedName("id")
    var id: String?, // 1
    @SerializedName("todo")
    var todo: String?, // Do something nice for someone I care about
    @SerializedName("userId")
    var userId: Int? // 26
) : Parcelable