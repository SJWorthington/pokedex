package com.example.anotherpokedex.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.example.anotherpokedex.data.local.datastore.settingsDataStore
import com.example.datastore.snippets.proto.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    @Provides
    @Singleton
    fun provideSettingsDataStore(
        @ApplicationContext context: Context
    ): DataStore<Settings> {
        return context.settingsDataStore
    }
}