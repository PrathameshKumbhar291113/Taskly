package com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.database.models.NoteTodos
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.GetAllNotesRemotelyUseCase
import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository,
    private val getAllNotesRemotelyUseCase: GetAllNotesRemotelyUseCase
): ViewModel(){

    private val _notesList = MutableLiveData<List<NoteTodos>>()
    val notesList : LiveData<List<NoteTodos>> = _notesList

    private val _noteListFromRemote = MutableLiveData<List<GetAllNotesFromRemoteResponse>>()
    val noteListFromResponse: LiveData<List<GetAllNotesFromRemoteResponse>> = _noteListFromRemote

    fun getAllNotesLocally(){
        viewModelScope.launch {
            tasklyLocalStorageRepository.getAllNotes().collect{
                _notesList.value = it.toList()
            }
        }
    }

    fun getAllNotesFromRemote(){
        getAllNotesRemotelyUseCase().onEach {
            when(it){
                is NetworkResult.Loading ->{

                }

                is NetworkResult.Success ->{
                    it.data?.body()?.let { response ->
                        _noteListFromRemote.value = listOf(response)
                        storeNotesInRealm(response.todos ?: emptyList())

                    }
                }

                is NetworkResult.Error ->{

                }
            }
        }.launchIn(viewModelScope)
    }




    fun deleteNotesLocally(objectId: Int){
        viewModelScope.launch {
            tasklyLocalStorageRepository.deleteNote(
                id = objectId
            )
        }
    }

    private fun storeNotesInRealm(todos: List<GetAllNotesFromRemoteResponse.Todo>) {
        val noteTodosList = todos.map { todo ->
            NoteTodos().apply {
                id = todo.id ?: 0
                completed = todo.completed ?: false
                this.todo = todo.todo ?: ""
                userId = todo.userId ?: 0
            }
        }

        viewModelScope.launch {
            noteTodosList.forEach { tasks ->
                tasklyLocalStorageRepository.insertOrUpdateNote(note = NoteTodos().apply {
                    userId = tasks.userId
                    completed = tasks.completed
                    todo = tasks.todo
                    id = tasks.id
                })
            }
        }
    }

}