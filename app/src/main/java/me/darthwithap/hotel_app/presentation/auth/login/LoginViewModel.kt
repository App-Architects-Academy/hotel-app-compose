package me.darthwithap.hotel_app.presentation.auth.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.darthwithap.hotel_app.presentation.base.HotelAppViewModel

class LoginViewModel : HotelAppViewModel<LoginEvents, LoginState>() {
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Init)
    override val state: StateFlow<LoginState> = _state

    override fun emitEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.LoginButtonClick -> {
                login(event)
            }
            else -> {}
        }
    }

    private fun login(loginButtonClick: LoginEvents.LoginButtonClick) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            val email = loginButtonClick.email
            val pass = loginButtonClick.password

            if (email.equals(
                    "info@apparchitects.academy",
                    ignoreCase = true
                ) && pass.equals("aBcd123@#", ignoreCase = false)
            ) {
                withContext(Dispatchers.Main) {
                    _state.update {
                        LoginState.LoginSuccess
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    _state.update {
                        LoginState.LoginError
                    }
                }
            }
        }
    }
}