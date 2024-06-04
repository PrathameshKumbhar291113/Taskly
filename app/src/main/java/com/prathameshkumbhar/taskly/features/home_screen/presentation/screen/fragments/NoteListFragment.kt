package com.prathameshkumbhar.taskly.features.home_screen.presentation.screen.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.prathameshkumbhar.taskly.database.models.Note
import com.prathameshkumbhar.taskly.databinding.FragmentNoteListBinding
import com.prathameshkumbhar.taskly.features.home_screen.presentation.adapter.NotesAdapter
import com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel.NoteListViewModel
import com.prathameshkumbhar.taskly.features.task_add.NoteAddActivity
import com.prathameshkumbhar.taskly.utils.TasklyConstants
import com.prathameshkumbhar.taskly.utils.toParcelable
import dagger.hilt.android.AndroidEntryPoint
import org.mongodb.kbson.ObjectId
import splitties.fragments.start


@AndroidEntryPoint
class NoteListFragment : Fragment(){
    private lateinit var binding: FragmentNoteListBinding
    private val noteListViewModel: NoteListViewModel by activityViewModels()
    private lateinit var notesAdapter: NotesAdapter

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

        noteListViewModel.noteListFromResponse.observe(viewLifecycleOwner){
//            setupRecyclerView(it)
        }

    }

    private fun passDataToAddNoteActivity(getContent: ActivityResultLauncher<Intent>) {
        binding.noteAddButton.setOnClickListener {
            val intent: Intent = Intent(requireContext(),NoteAddActivity::class.java)
            getContent.launch(intent)
        }
    }

    private fun setupUi() {
        binding.noteAddButton.setOnClickListener {
            start<NoteAddActivity>()
        }
    }

    private fun setupRecyclerView(notes: List<Note>) {
        notesAdapter = NotesAdapter(requireContext(), this::onClickUpdate, this::onClickDelete)
        binding.notesRecyclerView.setHasFixedSize(true)
        binding.notesRecyclerView.adapter = notesAdapter
        notesAdapter.updateList(notes)
    }

//    private fun setupRecyclerViewForRemote(notes: List<GetAllNotesFromRemoteResponse.Todo>) {
//        notesAdapter = NotesAdapter(requireContext(), this::onClickUpdate, this::onClickDelete)
//        binding.notesRecyclerView.setHasFixedSize(true)
//        binding.notesRecyclerView.adapter = notesAdapter
//        notesAdapter.updateList(notes)
//    }

    private fun onClickUpdate(note: Note) {
        val intent: Intent = Intent(requireContext(), NoteAddActivity::class.java).apply {
            putExtra(TasklyConstants.CURRENT_NOTE,note.toParcelable())
        }
        startActivity(intent)
    }

    private fun onClickDelete(objectId: ObjectId){
        if (objectId.toString().isNotEmpty()){
            noteListViewModel.deleteNotesLocally(objectId)
        }
    }
}