package com.prathameshkumbhar.taskly.utils.models

import android.os.Parcelable
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.mongodb.kbson.ObjectId

@Parcelize
class Note: RealmObject, Parcelable {
    @PrimaryKey var _id: ObjectId = ObjectId.invoke()
    var noteTitle: String = ""
    var noteDescription: String = ""
    var noteCreatedOn: String = ""
}