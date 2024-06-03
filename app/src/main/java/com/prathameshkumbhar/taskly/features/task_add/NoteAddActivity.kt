package com.prathameshkumbhar.taskly.features.task_add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.taskly.databinding.ActivityNoteAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        setupObservers()

    }

    private fun setupObservers() {

    }

    private fun setupUi() {

    }
}