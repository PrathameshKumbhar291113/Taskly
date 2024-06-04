package com.prathameshkumbhar.taskly.features.task_add.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.taskly.database.NoteTodosParcelable
import com.prathameshkumbhar.taskly.database.models.NoteTodos
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.utils.generateRandomNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteAddViewModel @Inject constructor(
    private val tasklyLocalStorageRepository: TasklyLocalStorageRepository
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

    fun setNoteValues(note: NoteTodosParcelable, isUpdate: Boolean) {
        _idValue.value = note.id
        _userIdValue.value = note.userId
        _completedValue.value = note.completed
        _todoValue.value = note.todo
        _isUpdate.value = isUpdate
    }

    fun setUserIdAndUserTodo(userId: Int, todo: String) {
        _todoValue.value = todo
        _userIdValue.value = userId
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

}