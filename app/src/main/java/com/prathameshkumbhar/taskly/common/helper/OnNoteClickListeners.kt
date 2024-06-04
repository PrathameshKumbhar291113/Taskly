package com.prathameshkumbhar.taskly.common.helper

import com.prathameshkumbhar.taskly.database.models.Note

interface OnNoteClickListeners {
   fun onNoteClicked(note: Note)
}