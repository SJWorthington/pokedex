package com.example.anotherpokedex.core.di

import androidx.datastore.core.DataStore
import com.example.anotherpokedex.data.repository.FilterPreferencesRepositoryImpl
import com.example.anotherpokedex.domain.repository.FilterPreferencesRepository
import com.example.datastore.snippets.proto.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesRepositoryModule {

    @Provides
    @Singleton
    fun provideFilterPreferencesRepository(
        dataStore: DataStore<Settings>
    ): FilterPreferencesRepository {
        return FilterPreferencesRepositoryImpl(dataStore)
    }
}