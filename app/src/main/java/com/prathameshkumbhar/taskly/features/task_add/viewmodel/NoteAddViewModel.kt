package com.prathameshkumbhar.taskly.features.task_add.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.database.dto.NoteTodosParcelable
import com.prathameshkumbhar.taskly.database.models.NoteTodos
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.InsertNoteRemotelyUseCase
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.UpdateNoteRemotelyUseCase
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteResponse
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteResponse
import com.prathameshkumbhar.taskly.utils.generateRandomNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NoteAddViewModel @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository,
    private val insertNoteRemotelyUseCase: InsertNoteRemotelyUseCase,
    private val updateNoteRemotelyUseCase: UpdateNoteRemotelyUseCase
) : ViewModel() {

    private val _idValue = MutableLiveData<Int>()
    val idValue: LiveData<Int> get() = _idValue

    private val _userIdValue = MutableLiveData<Int>()
    val userIdValue: LiveData<Int> get() = _userIdValue

    private val _completedValue = MutableLiveData<Boolean>()
    val completedValue: LiveData<Boolean> get() = _completedValue

    private val _todoValue = MutableLiveData<String>()
    val todoValue: LiveData<String> get() = _todoValue

    private val _isUpdate = MutableLiveData<Boolean>()
    val isUpdate: LiveData<Boolean> get() = _isUpdate

    private val _postNoteToRemoteResponse = MutableLiveData<NetworkResult<Response<PostNoteToRemoteResponse>>>()
    val postNoteToRemoteResponse: LiveData<NetworkResult<Response<PostNoteToRemoteResponse>>> = _postNoteToRemoteResponse

    private val _putNoteToRemoteResponse = MutableLiveData<NetworkResult<Response<PutNoteToRemoteResponse>>>()
    val putNoteToRemoteResponse: LiveData<NetworkResult<Response<PutNoteToRemoteResponse>>> = _putNoteToRemoteResponse

    fun setNoteValues(note: NoteTodosParcelable, isUpdate: Boolean) {
        _idValue.value = note.id
        _userIdValue.value = note.userId
        _completedValue.value = note.completed
        _todoValue.value = note.todo
        _isUpdate.value = isUpdate
    }

    fun insertNotesLocally(userIdValue: Int, todoValue: String) {
        viewModelScope.launch {
            tasklyLocalStorageRepository.insertNote(note = NoteTodos().apply {
                id = generateRandomNumber()
                userId = userIdValue
                _completedValue.value?.let {
                    completed = it
                }
                todo = todoValue

            })
        }
    }

    fun insertNoteToRemote(postNoteToRemoteRequest: PostNoteToRemoteRequest){
        insertNoteRemotelyUseCase(postNoteToRemoteRequest).onEach {
            when(it){
                is NetworkResult.Loading ->{

                }

                is NetworkResult.Success ->{

                    _postNoteToRemoteResponse.postValue(it)
                    Log.e("insert_note", "insertNoteToRemote Success: ${it.data?.body().toString()} --- ${it.data?.code().toString()}", )
                }

                is NetworkResult.Error -> {
                    Log.e("insert_note", "insertNoteToRemote Error: ${it.data?.body().toString()} --- ${it.data?.code().toString()}", )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateNotesLocally(userIdValue: Int, todoValue: String) {
        viewModelScope.launch {
            tasklyLocalStorageRepository.updateNote(note = NoteTodos().apply {
                _idValue.value?.let {
                    id = it
                }
                userId = userIdValue
                _completedValue.value?.let {
                    completed = it
                }
                todo = todoValue
            })
        }
    }

    fun updateNoteInRemote(id: Int, putNoteToRemoteRequest: PutNoteToRemoteRequest){
        updateNoteRemotelyUseCase(id, putNoteToRemoteRequest).onEach {
            when(it){
                is NetworkResult.Loading ->{

                }

                is NetworkResult.Success ->{
                    _putNoteToRemoteResponse.postValue(it)
                    Log.e("update_note", "updateNoteToRemote: ${it.data?.body().toString()}", )
                }

                is NetworkResult.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }

}