package com.prathameshkumbhar.taskly.utils.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteParcelable(
    val _id: String,
    val noteTitle: String,
    val noteDescription: String,
    val noteCreatedOn: String
) : Parcelable