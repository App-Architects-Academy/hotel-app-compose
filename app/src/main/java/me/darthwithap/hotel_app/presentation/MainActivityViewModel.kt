package me.darthwithap.hotel_app.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.darthwithap.hotel_app.PREF_AUTHTOKEN_KEY

class MainActivityViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainActivityUiState())
    val state = _state.asStateFlow()

    fun checkLogin(authDataStore: DataStore<Preferences>) {
        viewModelScope.launch {
            delay(500) // Simulate background tasks in splash screen
            authDataStore.data.map { preferences ->
                val authToken = preferences[PREF_AUTHTOKEN_KEY]
                authToken != null
            }.take(1)
                .collect { isLoggedIn ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isLoggedIn = isLoggedIn
                        )
                    }
                }
        }
    }
}