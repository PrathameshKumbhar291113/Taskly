package com.prathameshkumbhar.taskly

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TasklyApplication: Application(){

    override fun onCreate() {
        super.onCreate()
    }

}