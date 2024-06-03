package com.prathameshkumbhar.taskly.common.helper

import com.prathameshkumbhar.taskly.utils.models.Note

interface OnNoteClickListeners {
   fun onNoteClicked(note: Note)
}