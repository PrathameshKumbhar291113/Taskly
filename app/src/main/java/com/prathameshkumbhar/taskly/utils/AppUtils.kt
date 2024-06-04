package com.prathameshkumbhar.taskly.utils

import android.app.Activity
import com.prathameshkumbhar.taskly.R
import com.prathameshkumbhar.taskly.utils.models.Note
import com.prathameshkumbhar.taskly.utils.models.NoteParcelable
import org.mongodb.kbson.ObjectId
import kotlin.random.Random

fun randomColors(): Int{

    var colorList = ArrayList<Int>()
    colorList.add(R.color.primary_pink)
    colorList.add(R.color.primary_blue)
    colorList.add(R.color.primary_yellow)
    colorList.add(R.color.primary_orange)
    colorList.add(R.color.primary_red)
    colorList.add(R.color.primary_purple)
    colorList.add(R.color.primary_midnight)
    colorList.add(R.color.primary_violet)

    val seed = System.currentTimeMillis().toInt()
    val randomColor = Random(seed).nextInt(colorList.size)
    return colorList[randomColor]

}

fun statusBarColor(activity: Activity){
    activity.window.statusBarColor = activity.getColor(R.color.primary_pink)
}

fun Note.toParcelable(): NoteParcelable {
    return NoteParcelable(
        _id = this._id.toHexString(),
        noteTitle = this.noteTitle,
        noteDescription = this.noteDescription,
        noteCreatedOn = this.noteCreatedOn
    )
}

fun NoteParcelable.toNote(): Note {
    return Note().apply {
        _id = ObjectId(this@toNote._id)
        noteTitle = this@toNote.noteTitle
        noteDescription = this@toNote.noteDescription
        noteCreatedOn = this@toNote.noteCreatedOn
    }
}
