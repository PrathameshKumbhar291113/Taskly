package com.prathameshkumbhar.taskly.database.models

import io.realm.kotlin.types.RealmObject

class NoteTodos: RealmObject {
    var id: Int = 0
    var completed: Boolean = false
    var todo: String = ""
    var userId: Int = 0
}