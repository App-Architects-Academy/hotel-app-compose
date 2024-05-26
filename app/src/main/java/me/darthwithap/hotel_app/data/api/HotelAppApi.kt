package me.darthwithap.hotel_app.data.api

import me.darthwithap.hotel_app.data.NetworkClient
import me.darthwithap.hotel_app.domain.models.Hotel
import kotlin.random.Random

class HotelAppApi(
    private val networkClient: NetworkClient
) {

    suspend fun fetchHotels(): Result<List<Hotel>> {
        try {
            val shouldSucceed = Random(100).nextBoolean()
            val url = if (shouldSucceed) "login_success.json" else "login_failure.json"
            val string = networkClient.fetchUrl(url)



            return Result.failure(NotImplementedError("Not yet implemented"))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun login(
        email: String,
        password: String,
    ): Result<String> {
        try {
            val shouldSucceed = Random(100).nextBoolean()
            val url = if (shouldSucceed) "login_success.json" else "login_failure.json"
            val string = networkClient.fetchUrl(url)



            return string
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}