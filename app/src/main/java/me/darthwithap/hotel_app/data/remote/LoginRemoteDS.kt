package me.darthwithap.hotel_app.data.remote

import me.darthwithap.hotel_app.data.api.HotelAppApi

class LoginRemoteDS(
    private val hotelAppApi: HotelAppApi
) {
    suspend fun login(email: String, pass: String): Result<String> {
        return hotelAppApi.login(email = email, password = pass)
    }
}