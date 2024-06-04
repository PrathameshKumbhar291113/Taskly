package com.prathameshkumbhar.taskly.utils.models


import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Note: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId.invoke()
    var noteTitle: String = ""
    var noteDescription: String = ""
    var noteCreatedOn: String = ""
}