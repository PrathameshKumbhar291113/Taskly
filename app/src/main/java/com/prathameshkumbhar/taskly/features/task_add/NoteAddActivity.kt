package com.prathameshkumbhar.taskly.features.task_add

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.taskly.database.NoteParcelable
import com.prathameshkumbhar.taskly.database.models.Note
import com.prathameshkumbhar.taskly.databinding.ActivityNoteAddBinding
import com.prathameshkumbhar.taskly.features.task_add.viewmodel.NoteAddViewModel
import com.prathameshkumbhar.taskly.utils.TasklyConstants
import com.prathameshkumbhar.taskly.utils.statusBarColor
import com.prathameshkumbhar.taskly.utils.toNote
import dagger.hilt.android.AndroidEntryPoint
import org.mongodb.kbson.ObjectId
import java.text.SimpleDateFormat
import java.util.Date

@AndroidEntryPoint
class NoteAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteAddBinding
    private val noteAddViewModel: NoteAddViewModel by viewModels()
    private lateinit var note: Note
    private  lateinit var oldId: ObjectId
    private var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        setupObservers()

    }

    private fun setupObservers() {

        try {
            intent.getParcelableExtra<NoteParcelable>(TasklyConstants.CURRENT_NOTE)?.toNote()?.apply {
                oldId = _id
                binding.noteTitleEditText.setText(noteTitle)
                binding.noteDescriptionEditText.setText(noteDescription)
                isUpdate = true
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun setupUi() {

        statusBarColor(this@NoteAddActivity)

        binding.submitButton.setOnClickListener {
            if (!binding.noteTitleEditText.text.isNullOrBlank() || !binding.noteTitleEditText.text.isNullOrEmpty() ||
                !binding.noteDescriptionEditText.text.isNullOrBlank() || !binding.noteDescriptionEditText.text.isNullOrEmpty()
            ) {
                if (isUpdate) {
                    noteAddViewModel.updateNotesLocally(
                        oldId,
                        binding.noteTitleEditText.text.toString(),
                        binding.noteDescriptionEditText.text.toString(),
                        SimpleDateFormat("dd MM yyyy HH:mm a").format(Date())
                    )
                } else {
                    noteAddViewModel.insertNotesLocally(
                        binding.noteTitleEditText.text.toString(),
                        binding.noteDescriptionEditText.text.toString(),
                        SimpleDateFormat("dd MM yyyy HH:mm a").format(Date())
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