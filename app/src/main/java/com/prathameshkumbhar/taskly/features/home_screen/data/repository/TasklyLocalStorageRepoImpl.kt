package com.prathameshkumbhar.taskly.features.home_screen.data.repository

import android.util.Log
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.utils.models.Note
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class TasklyLocalStorageRepoImpl  (val realm: Realm) : TasklyLocalStorageRepository{
    override fun getAllNotes(): Flow<List<Note>> {
        return realm.query<Note>().asFlow().map { it.list }
    }

    override suspend fun insertNote(note: Note) {
        realm.write { copyToRealm(note) }
    }

    override suspend fun updateNote(note: Note) {
        realm.write {
            val queriedNote = query<Note>(query = "_id == $0", note._id).first().find()
            queriedNote?.noteTitle = note.noteTitle
        }
    }

    override suspend fun deleteNote(id: ObjectId) {
        realm.write {
            val note = query<Note>(query = "_id == $0", id).first().find()
            try {
                note?.let { delete(it) }
            } catch (e: Exception) {
                Log.e("MongoRepositoryImpl", "${e.message}")
            }
        }
    }
}