package com.prathameshkumbhar.taskly.features.home_screen.domain.repository

import com.prathameshkumbhar.taskly.database.models.NoteTodos
import kotlinx.coroutines.flow.Flow

interface TasklyLocalStorageRepository {

    fun getAllNotes(): Flow<List<NoteTodos>>
    suspend fun insertNote(note: NoteTodos)
    suspend fun updateNote(note: NoteTodos)
    suspend fun deleteNote(id: Int)
    suspend fun insertOrUpdateNote(note: NoteTodos)

}