package com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.GetAllNotesLocallyUseCase
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.InsertNoteLocallyUseCase
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.UpdateNoteLocallyUseCase
import com.prathameshkumbhar.taskly.utils.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getAllNotesLocallyUseCase: GetAllNotesLocallyUseCase,
    private val insertNoteLocallyUseCase: InsertNoteLocallyUseCase,
    private val updateNoteLocallyUseCase: UpdateNoteLocallyUseCase
): ViewModel(){

    private val _notesList = MutableLiveData<List<Note>>()
    val notesList : LiveData<List<Note>> = _notesList

    init {
        getAllNotesLocally()
    }

    private fun getAllNotesLocally(){
        viewModelScope.launch {
            getAllNotesLocallyUseCase().collect{
              when(it){
                  is NetworkResult.Loading ->{

                  }

                  is NetworkResult.Success ->{
                      it.data?.collect{notes ->
                          _notesList.value = notes
                      }
                  }

                  is NetworkResult.Error ->{

                  }
              }
            }
        }

    }

    fun insertNotesLocally(note: Note){
        viewModelScope.launch {
            insertNoteLocallyUseCase(note)
        }
    }

    fun updateNotesLocally(note: Note){
        viewModelScope.launch {
            if (note._id.toHexString().isNotEmpty()) {
                updateNoteLocallyUseCase(note = note)
            }else{
                Log.e("Object Id is empty", "updateNotesLocally: ")
            }
        }
    }

    fun deleteNotesLocally(objectId: ObjectId){

    }

}