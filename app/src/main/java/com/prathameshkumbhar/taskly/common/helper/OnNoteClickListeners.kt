package com.prathameshkumbhar.taskly.common.helper

import androidx.cardview.widget.CardView
import com.prathameshkumbhar.taskly.utils.models.Note

interface OnNoteClickListeners {
   fun onNoteClicked(note: Note)
   fun onNoteLongClicked(note: Note, cardView: CardView)
}