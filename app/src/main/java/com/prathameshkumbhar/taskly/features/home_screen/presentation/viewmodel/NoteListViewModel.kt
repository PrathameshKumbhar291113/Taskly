package com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.GetAllNotesLocallyUseCase
import com.prathameshkumbhar.taskly.utils.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getAllNotesLocallyUseCase: GetAllNotesLocallyUseCase
): ViewModel(){

    private val _notesList = MutableLiveData<NetworkResult<Flow<List<Note>>>>()
    val notesList : LiveData<NetworkResult<Flow<List<Note>>>> = _notesList

    init {
        getAllNotesLocally()
    }

    private fun getAllNotesLocally(){

        getAllNotesLocallyUseCase().onEach{
            _notesList.value = it
        }

    }

}