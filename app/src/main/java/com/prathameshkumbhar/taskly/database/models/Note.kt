package com.prathameshkumbhar.taskly.database.models


import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject


class Note: RealmObject {
    var limit: Int = 0
    var skip: Int = 0
    var total: Int = 0
    var todoList: RealmList<NoteTodos> = realmListOf()
}