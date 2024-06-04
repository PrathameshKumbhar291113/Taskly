package com.prathameshkumbhar.taskly.features.task_add.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.utils.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class NoteAddViewModel @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository
): ViewModel(){


    fun insertNotesLocally(noteTitleValue: String, noteDescValue: String, noteCreatedOnValue: String){
        viewModelScope.launch {
            tasklyLocalStorageRepository.insertNote(note = Note().apply {
                noteTitle = noteTitleValue
                noteDescription = noteDescValue
                noteCreatedOn = noteCreatedOnValue
            })
        }
    }

    fun updateNotesLocally(
        id: ObjectId,
        noteTitleValue: String,
        noteDescValue: String,
        noteCreatedOnValue: String
    ){
        viewModelScope.launch {
            tasklyLocalStorageRepository.updateNote(note = Note().apply {
                _id = id
                noteTitle = noteTitleValue
                noteDescription = noteDescValue
                noteCreatedOn = noteCreatedOnValue
            })
        }
    }

}