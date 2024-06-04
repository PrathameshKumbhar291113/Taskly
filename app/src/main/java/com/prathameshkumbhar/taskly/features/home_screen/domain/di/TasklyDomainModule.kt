package com.prathameshkumbhar.taskly.features.home_screen.domain.di

import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyRemoteRepository
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.GetAllNotesRemotelyUseCase
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.InsertNoteLocallyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TasklyDomainModule {

    @Singleton
    @Provides
    fun provideGetAllNotesRemotely(tasklyRemoteRepository: TasklyRemoteRepository): GetAllNotesRemotelyUseCase {
        return GetAllNotesRemotelyUseCase(tasklyRemoteRepository)
    }

    @Singleton
    @Provides
    fun provideInsertNoteLocally(tasklyLocalStorageRepository: TasklyLocalStorageRepository): InsertNoteLocallyUseCase {
        return InsertNoteLocallyUseCase(tasklyLocalStorageRepository)
    }

}