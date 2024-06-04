package com.prathameshkumbhar.taskly

import com.prathameshkumbhar.taskly.database.models.Note
import com.prathameshkumbhar.taskly.network.ApiCommunicator
import com.prathameshkumbhar.taskly.utils.TasklyConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TasklyApplicationModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Note::class
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Provides
    @Singleton
    fun provideTasklyApiCommunicator() : ApiCommunicator {
        return Retrofit.Builder()
            .baseUrl(TasklyConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiCommunicator::class.java)
    }

}