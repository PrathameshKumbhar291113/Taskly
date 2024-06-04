package com.prathameshkumbhar.taskly.database.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class NoteTodos: RealmObject {
    @PrimaryKey  var id: Int = 0
    var completed: Boolean = false
    var todo: String = ""
    var userId: Int = 0
}