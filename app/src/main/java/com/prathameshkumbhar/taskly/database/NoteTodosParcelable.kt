package com.prathameshkumbhar.taskly.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteTodosParcelable(
    var id: Int,
    var completed: Boolean,
    var todo: String,
    var userId: Int
) : Parcelable