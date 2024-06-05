package com.prathameshkumbhar.taskly.utils

import com.prathameshkumbhar.taskly.BuildConfig


object TasklyConstants {
    const val CURRENT_NOTE = "current_note"
    const val INSERT_NOTE = "insert_note"
    const val BASE_URL = BuildConfig.TASKLY_BASE_URL
    const val GET_ALL_NOTES_FROM_REMOTE = "/todos"
    const val POST_NOTE_TO_REMOTE = "/todos/add"
    const val PUT_NOTE_TO_REMOTE = "/todos/{id}"
    const val DELETE_NOTE_FROM_REMOTE = "/todos/{id}"
    const val LIMIT_FOR_GET_DATA = 300
    const val SKIP_FOR_GET_DATA = 0
}