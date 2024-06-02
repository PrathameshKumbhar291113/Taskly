package com.prathameshkumbhar.taskly.features.home_screen.domain.repository

import com.prathameshkumbhar.taskly.utils.models.Note
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface TasklyLocalStorageRepository {

    fun getData(): Flow<List<Note>>
    suspend fun insertNote(person: Note)
    suspend fun updateNote(person: Note)
    suspend fun deleteNote(id: ObjectId)

}