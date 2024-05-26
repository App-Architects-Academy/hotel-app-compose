package me.darthwithap.hotel_app.presentation.auth.login

import me.darthwithap.hotel_app.presentation.base.State

sealed class LoginState : State {
    object Init: LoginState()
    data class LoginSuccess(
        val authToken: String
    ): LoginState()
    object LoginError: LoginState()
}