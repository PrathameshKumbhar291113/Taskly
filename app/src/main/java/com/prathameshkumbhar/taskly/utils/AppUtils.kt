package com.prathameshkumbhar.taskly.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.prathameshkumbhar.taskly.R
import com.prathameshkumbhar.taskly.database.NoteTodosParcelable
import com.prathameshkumbhar.taskly.database.models.NoteTodos
import com.prathameshkumbhar.taskly.network.models.GetAllNotesFromRemoteResponse
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

fun NoteTodos.toParcelableNoteTodos(): NoteTodosParcelable {
    return NoteTodosParcelable(
        id = this@toParcelableNoteTodos.id,
        completed = this@toParcelableNoteTodos.completed,
        todo = this@toParcelableNoteTodos.todo,
        userId = this@toParcelableNoteTodos.userId
    )
}

fun NoteTodosParcelable.toNoteTodo(): NoteTodos {
    return NoteTodos().apply {
            id = this@toNoteTodo.id
            completed = this@toNoteTodo.completed
            todo = this@toNoteTodo.todo
            userId = this@toNoteTodo.userId
    }
}

fun convertToNoteTodos(response: GetAllNotesFromRemoteResponse): List<NoteTodos> {
    return response.todos?.map { todo ->
        NoteTodos().apply {
            id = todo.id ?: 0
            completed = todo.completed ?: false
            this.todo = todo.todo ?: ""
            userId = todo.userId ?: 0
        }
    } ?: emptyList()
}


fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

fun generateRandomNumber(): Int {
    return Random.nextInt(1, 101)
}