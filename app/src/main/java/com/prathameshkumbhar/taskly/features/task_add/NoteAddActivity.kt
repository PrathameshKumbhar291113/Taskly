package com.prathameshkumbhar.taskly.features.task_add

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.taskly.common.helper.NetworkResult
import com.prathameshkumbhar.taskly.database.dto.NoteTodosParcelable
import com.prathameshkumbhar.taskly.databinding.ActivityNoteAddBinding
import com.prathameshkumbhar.taskly.features.task_add.viewmodel.NoteAddViewModel
import com.prathameshkumbhar.taskly.network.models.PostNoteToRemoteRequest
import com.prathameshkumbhar.taskly.network.models.PutNoteToRemoteRequest
import com.prathameshkumbhar.taskly.utils.TasklyConstants
import com.prathameshkumbhar.taskly.utils.isNetworkAvailable
import com.prathameshkumbhar.taskly.utils.statusBarColor
import com.prathameshkumbhar.taskly.utils.toNoteTodo
import com.prathameshkumbhar.taskly.utils.toParcelableNoteTodos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteAddBinding
    private val noteAddViewModel: NoteAddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupUi()

    }

    private fun setupObservers() {

        try {
            with(noteAddViewModel) {
                intent.getParcelableExtra<NoteTodosParcelable>(TasklyConstants.CURRENT_NOTE)
                    ?.toNoteTodo()?.let {
                        setNoteValues(it.toParcelableNoteTodos(), isUpdate = true)
                    }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        noteAddViewModel.todoValue.observe(this) {
            binding.noteDescriptionEditText.setText(it.toString())
        }
        noteAddViewModel.userIdValue.observe(this) {
            binding.noteTitleEditText.setText(it.toString())
        }

        noteAddViewModel.postNoteToRemoteResponse.observe(this) {
            when(it){
                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    if (it.data?.body()?.todo.toString().isNotEmpty()){
                        Toast.makeText(
                            this,
                            "Notes Was Successfully Inserted To Remote.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }else{
                        Toast.makeText(
                            this,
                            "Notes Was Not Successfully Inserted To Remote.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                is NetworkResult.Error -> {}

            }
        }

        noteAddViewModel.putNoteToRemoteResponse.observe(this) {
            when(it){
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> {

                    if (it.data?.body()?.todo.toString().isNotEmpty()){

                        Toast.makeText(
                            this,
                            "Notes Was Successfully Updated To Remote.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()

                    }else{

                        Toast.makeText(
                            this,
                            "Notes Was Not Successfully Updated To Remote.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
                is NetworkResult.Error -> {}

            }
        }

    }

    private fun setupUi() {

        statusBarColor(this@NoteAddActivity)

        if (noteAddViewModel.isUpdate.value == true){
            binding.title.text = "Update Task"
        }else{
            binding.title.text = "Add Task"
        }

        binding.submitButton.setOnClickListener {
            if (!binding.noteTitleEditText.text.isNullOrBlank() ||
                !binding.noteTitleEditText.text.isNullOrEmpty() ||
                !binding.noteDescriptionEditText.text.isNullOrBlank() ||
                !binding.noteDescriptionEditText.text.isNullOrEmpty()
            ) {

                if (isNetworkAvailable(this)) {

                    if (noteAddViewModel.isUpdate.value == true) {
                        noteAddViewModel.idValue.value?.let {
                            noteAddViewModel.updateNoteInRemote(it, PutNoteToRemoteRequest(completed = true))
                        }

                    } else {
                        noteAddViewModel.insertNoteToRemote(
                            postNoteToRemoteRequest = PostNoteToRemoteRequest(
                                todo = binding.noteDescriptionEditText.text.toString(),
                                completed = true,
                                userId = binding.noteTitleEditText.text.toString().toInt()
                            )
                        )
                    }

                } else {
                    if (noteAddViewModel.isUpdate.value == true) {
                        Toast.makeText(
                            this,
                            "Notes Was Successfully Updated To Local Db.",
                            Toast.LENGTH_SHORT
                        ).show()

                        noteAddViewModel.updateNotesLocally(
                            binding.noteTitleEditText.text.toString().toInt(),
                            binding.noteDescriptionEditText.text.toString()
                        )

                    } else {
                        Toast.makeText(
                            this,
                            "Notes Was Successfully Inserted To Local Db.",
                            Toast.LENGTH_SHORT
                        ).show()

                        noteAddViewModel.insertNotesLocally(
                            binding.noteTitleEditText.text.toString().toInt(),
                            binding.noteDescriptionEditText.text.toString()
                        )
                    }
                }
            } else {
                Toast.makeText(
                    this@NoteAddActivity,
                    "The Fields Cannot Be Empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}