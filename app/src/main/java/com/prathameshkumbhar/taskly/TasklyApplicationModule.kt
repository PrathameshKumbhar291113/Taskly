package com.prathameshkumbhar.taskly

import com.prathameshkumbhar.taskly.utils.models.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
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

}