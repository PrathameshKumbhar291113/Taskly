package com.prathameshkumbhar.taskly.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostNoteToRemoteResponse(
    @SerializedName("completed")
    var completed: Boolean?, // false
    @SerializedName("id")
    var id: Int?, // 151
    @SerializedName("todo")
    var todo: String?, // Use DummyJSON in the project
    @SerializedName("userId")
    var userId: Int? // 5
) : Parcelable
