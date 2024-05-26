package me.darthwithap.hotel_app.presentation.auth.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.darthwithap.hotel_app.data.remote.LoginRemoteDS
import me.darthwithap.hotel_app.presentation.base.HotelAppViewModel

class LoginViewModel(
    val loginRemoteDS: LoginRemoteDS
) : HotelAppViewModel<LoginEvents, LoginState>() {
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
            val response = loginRemoteDS.login(loginButtonClick.email, loginButtonClick.password)


            Log.d(this.javaClass.simpleName, "response ${response.isSuccess} ${response.getOrNull()}")

            if (response.isSuccess) {
                _state.update {
                    val successSate = LoginState.LoginSuccess(response.getOrThrow())
                    Log.d(this.javaClass.simpleName, "sending $successSate")
                    successSate
                }
            } else {
                _state.update {
                    LoginState.LoginError
                }
            }
        }
    }
}