package com.satispay.pokedex.data.datasource.local

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreRepository(context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pokedex_preferences")
        val DARK_THEME_KEY = booleanPreferencesKey(name = "dark_theme_key")
        val DYNAMIC_COLOR_KEY = booleanPreferencesKey(name = "dynamic_color_key")
    }

    private val dataStore = context.dataStore

    suspend fun setDarkTheme(theme: Boolean) {
        dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = theme
        }
    }

    @Composable
    fun isDarkTheme(): Flow<Boolean> {
        val isDarkTheme: Boolean = isSystemInDarkTheme()
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { preferences ->
                val savedTheme: Boolean = preferences[DARK_THEME_KEY] ?: isDarkTheme
                savedTheme
            }
    }

    suspend fun setDynamicColor(color: Boolean) {
        dataStore.edit { preferences ->
            preferences[DYNAMIC_COLOR_KEY] = color
        }
    }

    fun isDynamicColor(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) emit(emptyPreferences())
                else throw exception
            }
            .map { preferences ->
                val savedColor: Boolean = preferences[DYNAMIC_COLOR_KEY] != false
                savedColor
            }
    }

    suspend fun resetTheme() {
        dataStore.edit { preferences ->
            preferences.remove(DARK_THEME_KEY)
            preferences.remove(DYNAMIC_COLOR_KEY)
        }
    }
}