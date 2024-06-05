package com.prathameshkumbhar.taskly.features.home_screen.data.repository

import android.util.Log
import com.prathameshkumbhar.taskly.database.models.NoteTodos
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasklyLocalStorageRepoImpl  (val realm: Realm) : TasklyLocalStorageRepository{
    override fun getAllNotes(): Flow<List<NoteTodos>> {
        return realm.query<NoteTodos>().asFlow().map { it.list.asReversed() }
    }

    override suspend fun insertNote(note: NoteTodos) {
        realm.write { copyToRealm(note) }
    }

    override suspend fun updateNote(note: NoteTodos) {
        realm.write {
            val queriedNote = query<NoteTodos>(query = "id == $0", note.id).first().find()
            if (queriedNote != null) {
                queriedNote.userId = note.userId
                queriedNote.todo = note.todo
                queriedNote.completed = note.completed
            }else{
                Log.e("pratham", "updateNote: qureid note is null", )
            }

        }
    }

    override suspend fun deleteNote(id: Int) {
        realm.write {
            val note = query<NoteTodos>("id == $0", id).first().find()
            try {
                note?.let { delete(it) }
            } catch (e: Exception) {
                Log.e("TasklyLocalStorageRepoImpl", "${e.message}")
            }
        }
    }

    override suspend fun insertOrUpdateNote(note: NoteTodos) {
        realm.write {
            val existingNote = query<NoteTodos>("id == $0", note.id).first().find()
            if (existingNote == null) {
                copyToRealm(note)
            } else {
                existingNote.apply {
                    completed = note.completed
                    todo = note.todo
                    userId = note.userId
                }
            }
        }
    }
}