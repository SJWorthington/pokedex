package com.example.anotherpokedex.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.datastore.snippets.proto.Settings

private const val DATA_STORE_FILE_NAME = "settings.pb"

val Context.settingsDataStore: DataStore<Settings> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = DataStoreSerializer
)