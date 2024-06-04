package com.prathameshkumbhar.taskly.features.home_screen.data.di

import com.prathameshkumbhar.taskly.features.home_screen.data.repository.TasklyLocalStorageRepoImpl
import com.prathameshkumbhar.taskly.features.home_screen.data.repository.TasklyRemoteRepoImpl
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyRemoteRepository
import com.prathameshkumbhar.taskly.network.ApiCommunicator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TasklyDataModule {

    @Singleton
    @Provides
    fun provideTasklyLocalStorageRepository(realm: Realm): TasklyLocalStorageRepository{
        return TasklyLocalStorageRepoImpl(realm = realm)
    }

    @Singleton
    @Provides
    fun providesTasklyRemoteRepository(apiCommunicator: ApiCommunicator): TasklyRemoteRepository{
        return TasklyRemoteRepoImpl(apiCommunicator)
    }

}