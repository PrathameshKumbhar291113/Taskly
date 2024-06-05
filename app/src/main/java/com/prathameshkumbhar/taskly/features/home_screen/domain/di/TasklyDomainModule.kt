package com.prathameshkumbhar.taskly.features.home_screen.domain.di

import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyRemoteRepository
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.DeleteNoteRemotelyUseCase
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.GetAllNotesRemotelyUseCase
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.InsertNoteRemotelyUseCase
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.UpdateNoteRemotelyUseCase
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
    fun provideInsertNoteRemotely(tasklyRemoteRepository: TasklyRemoteRepository): InsertNoteRemotelyUseCase {
        return InsertNoteRemotelyUseCase(tasklyRemoteRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateNoteRemotely(tasklyRemoteRepository: TasklyRemoteRepository): UpdateNoteRemotelyUseCase {
        return UpdateNoteRemotelyUseCase(tasklyRemoteRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteNoteRemotely(tasklyRemoteRepository: TasklyRemoteRepository): DeleteNoteRemotelyUseCase {
        return DeleteNoteRemotelyUseCase(tasklyRemoteRepository)
    }

}