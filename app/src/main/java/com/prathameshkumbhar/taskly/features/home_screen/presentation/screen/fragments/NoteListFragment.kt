package com.prathameshkumbhar.taskly.features.home_screen.presentation.screen.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.prathameshkumbhar.taskly.database.models.NoteTodos
import com.prathameshkumbhar.taskly.databinding.FragmentNoteListBinding
import com.prathameshkumbhar.taskly.features.home_screen.presentation.adapter.NotesAdapter
import com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel.NoteListViewModel
import com.prathameshkumbhar.taskly.features.task_add.NoteAddActivity
import com.prathameshkumbhar.taskly.utils.TasklyConstants
import com.prathameshkumbhar.taskly.utils.convertToNoteTodos
import com.prathameshkumbhar.taskly.utils.isNetworkAvailable
import com.prathameshkumbhar.taskly.utils.toParcelableNoteTodos
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private lateinit var binding: FragmentNoteListBinding
    private val noteListViewModel: NoteListViewModel by activityViewModels()
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObservers()

    }

    private fun setupObservers() {
        if (isNetworkAvailable(requireContext())){
            noteListViewModel.getAllNotesFromRemote()
            noteListViewModel.noteListFromResponse.observe(viewLifecycleOwner) {
                it.forEach { data ->
                    setupRecyclerView(convertToNoteTodos(data))
                }
            }
        }else{
            noteListViewModel.getAllNotesLocally()
            noteListViewModel.notesList.observe(viewLifecycleOwner) {
                setupRecyclerView(it)
            }
        }
    }

    private fun setupUi() {
        binding.noteAddButton.setOnClickListener {
            val intent: Intent = Intent(requireContext(), NoteAddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView(notes: List<NoteTodos>) {
        notesAdapter = NotesAdapter(requireContext(), this::onClickUpdate, this::onClickDelete)
        binding.notesRecyclerView.setHasFixedSize(true)
        binding.notesRecyclerView.adapter = notesAdapter
        notesAdapter.updateList(notes)
    }

    private fun onClickUpdate(note: NoteTodos) {
        val intent: Intent = Intent(requireContext(), NoteAddActivity::class.java).apply {
            putExtra(TasklyConstants.CURRENT_NOTE, note.toParcelableNoteTodos())
        }
        startActivity(intent)
    }

    private fun onClickDelete(objectId: Int) {
        if (objectId.toString().isNotEmpty()) {
            noteListViewModel.deleteNotesLocally(objectId)
        }
    }
}