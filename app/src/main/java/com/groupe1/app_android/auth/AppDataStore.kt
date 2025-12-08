package com.groupe1.app_android.auth

import android.content.Context
import androidx.datastore.dataStore

val Context.userPreferencesDataStore by dataStore(
    fileName = "user-preferences",
    serializer = UserPreferencesSerializer
)