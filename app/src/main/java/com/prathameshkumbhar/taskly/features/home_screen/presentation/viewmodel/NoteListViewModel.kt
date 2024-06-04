package com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.utils.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository
): ViewModel(){

    private val _notesList = MutableLiveData<List<Note>>()
    val notesList : LiveData<List<Note>> = _notesList

    init {
        getAllNotesLocally()
    }

    private fun getAllNotesLocally(){
        viewModelScope.launch {
            tasklyLocalStorageRepository.getAllNotes().collect{
                _notesList.value = it.toList()
            }
        }

    }

    fun deleteNotesLocally(objectId: ObjectId){
        viewModelScope.launch {
            tasklyLocalStorageRepository.deleteNote(
                id = objectId
            )
        }
    }

}