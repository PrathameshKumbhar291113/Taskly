package com.prathameshkumbhar.taskly.features.home_screen.domain.repository

import com.prathameshkumbhar.taskly.database.models.Note
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface TasklyLocalStorageRepository {

    fun getAllNotes(): Flow<List<Note>>
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(id: ObjectId)

}