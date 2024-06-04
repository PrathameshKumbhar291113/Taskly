package com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.database.models.Note
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.GetAllNotesRemotelyUseCase
import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository,
    private val getAllNotesRemotelyUseCase: GetAllNotesRemotelyUseCase
): ViewModel(){

    private val _notesList = MutableLiveData<List<Note>>()
    val notesList : LiveData<List<Note>> = _notesList

    private val _noteListFromRemote = MutableLiveData<List<GetAllNotesFromRemoteResponse.Todo>>()
    val noteListFromResponse: LiveData<List<GetAllNotesFromRemoteResponse.Todo>> = _noteListFromRemote

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

    fun getAllNotesFromRemote(){
        getAllNotesRemotelyUseCase().onEach {
            when(it){
                is NetworkResult.Loading ->{

                }

                is NetworkResult.Success ->{
                    it.data?.body()?.todos?.let { list ->
                        _noteListFromRemote.value = list

                    }
                }

                is NetworkResult.Error ->{

                }
            }
        }.launchIn(viewModelScope)
    }

}