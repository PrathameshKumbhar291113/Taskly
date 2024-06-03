package com.prathameshkumbhar.taskly.features.home_screen.presentation.screen.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.prathameshkumbhar.taskly.common.helper.OnNoteClickListeners
import com.prathameshkumbhar.taskly.databinding.FragmentNoteListBinding
import com.prathameshkumbhar.taskly.features.home_screen.presentation.adapter.NotesAdapter
import com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel.NoteListViewModel
import com.prathameshkumbhar.taskly.features.task_add.NoteAddActivity
import com.prathameshkumbhar.taskly.utils.models.Note
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NoteListFragment : Fragment(), OnNoteClickListeners{
    private lateinit var binding: FragmentNoteListBinding
    private val noteListViewModel: NoteListViewModel by activityViewModels()
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var selectedNote: Note

    private val updateNote = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            val noteData = result.data?.getParcelableExtra("note") as? Note
            if (noteData != null){
                noteListViewModel.updateNotesLocally(noteData)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObservers()

    }

    private fun setupObservers() {

        noteListViewModel.notesList.observe(viewLifecycleOwner){
            setupRecyclerView(it)
        }

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == Activity.RESULT_OK){
                val noteData = result.data?.getParcelableExtra("note") as? Note
                if (noteData != null){
                    noteListViewModel.insertNotesLocally(noteData)
                }
            }
        }

        passDataToAddNoteActivity(getContent)

    }

    private fun passDataToAddNoteActivity(getContent: ActivityResultLauncher<Intent>) {
        binding.noteAddButton.setOnClickListener {
            val intent: Intent = Intent(requireContext(),NoteAddActivity::class.java)
            getContent.launch(intent)
        }
    }

    private fun setupUi() {

    }

    private fun setupRecyclerView(notes: List<Note>) {
        notesAdapter = NotesAdapter(requireContext(), this)
        binding.notesRecyclerView.adapter = notesAdapter
    }

    override fun onNoteClicked(note: Note) {
        val intent: Intent = Intent(requireContext(), NoteAddActivity::class.java).apply {
            putExtra("current_note",note)
        }
        updateNote.launch(intent)
    }

}