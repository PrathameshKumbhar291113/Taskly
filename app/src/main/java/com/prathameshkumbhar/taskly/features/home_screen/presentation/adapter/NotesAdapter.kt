package com.prathameshkumbhar.taskly.features.home_screen.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prathameshkumbhar.taskly.common.helper.OnNoteClickListeners
import com.prathameshkumbhar.taskly.databinding.ItemNoteBinding
import com.prathameshkumbhar.taskly.utils.models.Note
import com.prathameshkumbhar.taskly.utils.randomColors

class NotesAdapter(
    private val context: Context,
    val listeners: OnNoteClickListeners
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var noteList =  ArrayList<Note>()
    private var fullNoteList =  ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) =
        holder.bindNote(noteList[position], holder)

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(searchQuery: String){
        noteList.clear()
        fullNoteList.forEach{noteItem ->
            if (noteItem.noteTitle.lowercase().contains(searchQuery.lowercase()) || noteItem.noteDescription.lowercase().contains(searchQuery.lowercase())){
                noteList.add(noteItem)
            }
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newNoteList: List<Note>){
        noteList.clear()
        noteList.addAll(newNoteList)

        fullNoteList.clear()
        fullNoteList.addAll(newNoteList)
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(
        private val binding: ItemNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindNote(note: Note, holder: NotesViewHolder) {
            binding.noteTitle.text = note.noteTitle
            binding.noteDetail.text = note.noteDescription
            binding.noteSavedDate.text = note.noteCreatedOn
            binding.noteTitle.isSelected = true
            binding.noteSavedDate.isSelected = true
            binding.noteCard.setCardBackgroundColor(context.resources.getColor(randomColors(),null))

            binding.noteCard.setOnClickListener {
                listeners.onNoteClicked(noteList[holder.adapterPosition])
            }

        }
    }
}