package com.prathameshkumbhar.taskly.features.home_screen.presentation.screen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.prathameshkumbhar.taskly.databinding.FragmentNoteListBinding
import com.prathameshkumbhar.taskly.features.home_screen.presentation.viewmodel.NoteListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NoteListFragment : Fragment() {
    private lateinit var binding: FragmentNoteListBinding
    private val noteListViewModel: NoteListViewModel by activityViewModels()

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

        setupRecyclerView()
        setupUi()
        setupObservers()

    }

    private fun setupObservers() {

    }

    private fun setupUi() {

    }

    private fun setupRecyclerView() {

    }

}