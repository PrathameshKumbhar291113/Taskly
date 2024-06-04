package com.prathameshkumbhar.taskly.features.home_screen.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prathameshkumbhar.taskly.database.models.NoteTodos
import com.prathameshkumbhar.taskly.databinding.ItemNoteBinding
import com.prathameshkumbhar.taskly.utils.randomColors

class NotesAdapter(
    private val context: Context,
    val onClickUpdate: (NoteTodos) -> Unit,
    val onClickDelete: (Int) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var noteList =  ArrayList<NoteTodos>()
    private var fullNoteList =  ArrayList<NoteTodos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) =
        holder.bindNote(noteList[position], holder)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newNoteList: List<NoteTodos>){
        noteList.clear()
        noteList.addAll(newNoteList)

        fullNoteList.clear()
        fullNoteList.addAll(newNoteList)
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(
        private val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindNote(note: NoteTodos, holder: NotesViewHolder) {
            binding.noteTitle.text = note.userId.toString()
            binding.noteDetail.text = note.todo
            binding.noteSavedDate.text = note.id.toString()
            binding.noteTitle.isSelected = true
            binding.noteSavedDate.isSelected = true
            binding.noteCard.setCardBackgroundColor(context.resources.getColor(randomColors(),null))

            binding.editNote.setOnClickListener {
                onClickUpdate(note)
            }

            binding.deleteNote.setOnClickListener {
                onClickDelete(note.id)
            }

        }
    }
}