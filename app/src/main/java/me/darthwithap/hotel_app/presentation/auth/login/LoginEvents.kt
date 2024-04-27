package me.darthwithap.hotel_app.presentation.auth.login

import me.darthwithap.hotel_app.presentation.base.Event

sealed class LoginEvents: Event {
    data class LoginButtonClick(
        val email: String,
        val password: String
    ): LoginEvents()
}