package com.prathameshkumbhar.taskly.features.task_add

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.taskly.database.NoteTodosParcelable
import com.prathameshkumbhar.taskly.databinding.ActivityNoteAddBinding
import com.prathameshkumbhar.taskly.features.task_add.viewmodel.NoteAddViewModel
import com.prathameshkumbhar.taskly.utils.TasklyConstants
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

        setupUi()
        setupObservers()

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

        noteAddViewModel.todoValue.observe(this){
            binding.noteDescriptionEditText.setText(it.toString())
        }
        noteAddViewModel.userIdValue.observe(this){
            binding.noteTitleEditText.setText(it.toString())
        }
    }

    private fun setupUi() {

        statusBarColor(this@NoteAddActivity)

        binding.submitButton.setOnClickListener {
            if (!binding.noteTitleEditText.text.isNullOrBlank() || !binding.noteTitleEditText.text.isNullOrEmpty() ||
                !binding.noteDescriptionEditText.text.isNullOrBlank() || !binding.noteDescriptionEditText.text.isNullOrEmpty()
            ) {
                if (noteAddViewModel.isUpdate.value == true) {
                    noteAddViewModel.updateNotesLocally(
                        binding.noteTitleEditText.text.toString().toInt(),
                        binding.noteDescriptionEditText.text.toString()
                    )
                } else {
                    noteAddViewModel.insertNotesLocally(
                        binding.noteTitleEditText.text.toString().toInt(),
                        binding.noteDescriptionEditText.text.toString()
                    )
                }
                finish()

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