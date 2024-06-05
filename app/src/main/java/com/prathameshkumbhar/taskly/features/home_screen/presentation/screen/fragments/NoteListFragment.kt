package com.prathameshkumbhar.taskly.features.home_screen.presentation.screen.fragments

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.prathameshkumbhar.taskly.database.models.NoteTodos
import com.prathameshkumbhar.taskly.databinding.FragmentNoteListBinding
import com.prathameshkumbhar.taskly.features.home_screen.presentation.adapter.NotesAdapter
import com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel.NoteListViewModel
import com.prathameshkumbhar.taskly.features.task_add.NoteAddActivity
import com.prathameshkumbhar.taskly.service.NetworkChangeReceiver
import com.prathameshkumbhar.taskly.utils.TasklyConstants
import com.prathameshkumbhar.taskly.utils.convertToNoteTodos
import com.prathameshkumbhar.taskly.utils.isNetworkAvailable
import com.prathameshkumbhar.taskly.utils.toParcelableNoteTodos
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NoteListFragment : Fragment(), NetworkChangeReceiver.NetworkStateListener  {
    private lateinit var binding: FragmentNoteListBinding
    private val noteListViewModel: NoteListViewModel by activityViewModels()
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkChangeReceiver = NetworkChangeReceiver(this)
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireContext().registerReceiver(networkChangeReceiver, intentFilter)
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

    private fun setupUi() {
        binding.noteAddButton.setOnClickListener {
            val intent: Intent = Intent(requireContext(), NoteAddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupObservers(){
        noteListViewModel.noteDeletionResponseFromRemote.observe(viewLifecycleOwner){
            it?.let {
                if (it) {
                    Toast.makeText(requireContext(), "Deletion Of Note Was Successfully From Remote.", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), "Deletion Of Note Was Unsuccessfully From Remote.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        noteListViewModel.isLoading.observe(viewLifecycleOwner){
            if (it){
                binding.progressBarContainer.isVisible = true
                binding.noteListContainer.isVisible = false
            }else{
                binding.progressBarContainer.isVisible = false
                binding.noteListContainer.isVisible = true
            }
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
            noteListViewModel.setDeleteNoteId(objectId)
        }

        if (isNetworkAvailable(requireContext())){
            if (noteListViewModel.deleteNote.value.toString().isNotEmpty()){
                noteListViewModel.deleteNote.value?.let {
                    noteListViewModel.deleteNotesFromRemote(it)
                    noteListViewModel.getAllNotesFromRemote()
                }
            }
        }else{
            if (noteListViewModel.deleteNote.value.toString().isNotEmpty()){
                noteListViewModel.deleteNote.value?.let {
                    noteListViewModel.deleteNotesLocally(it)
                    noteListViewModel.getAllNotesLocally()
                }
            }
        }
    }

    override fun onNetworkAvailable() {
        noteListViewModel.getAllNotesFromRemote()
        noteListViewModel.noteListFromResponse.observe(viewLifecycleOwner) { response ->
            response.forEach { data ->
                Toast.makeText(requireContext(), "Notes Was Successfully Fetched From Remote.", Toast.LENGTH_SHORT).show()
                setupRecyclerView(convertToNoteTodos(data))
            }
        }
    }

    override fun onNetworkUnavailable() {
        noteListViewModel.getAllNotesLocally()
        noteListViewModel.notesList.observe(viewLifecycleOwner) { localNotes ->
            Toast.makeText(requireContext(), "Notes Was Successfully Fetched From Local Db.", Toast.LENGTH_SHORT).show()
            setupRecyclerView(localNotes)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(networkChangeReceiver)
    }
}