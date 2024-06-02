package com.prathameshkumbhar.taskly.features.home_screen.domain.di

import com.prathameshkumbhar.taskly.features.home_screen.domain.repository.TasklyLocalStorageRepository
import com.prathameshkumbhar.taskly.features.home_screen.domain.usecase.GetAllNotesLocallyUseCase
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
    fun provideGetAllNotesLocally(tasklyLocalStorageRepository: TasklyLocalStorageRepository): GetAllNotesLocallyUseCase {
        return GetAllNotesLocallyUseCase(tasklyLocalStorageRepository)
    }

    @Singleton
    @Provides
    fun provideInsertNoteLocally(tasklyLocalStorageRepository: TasklyLocalStorageRepository): GetAllNotesLocallyUseCase {
        return GetAllNotesLocallyUseCase(tasklyLocalStorageRepository)
    }

}