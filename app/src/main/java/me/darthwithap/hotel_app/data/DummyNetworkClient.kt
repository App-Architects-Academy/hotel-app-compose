package me.darthwithap.hotel_app.data

import android.content.Context
import android.content.res.AssetManager
import kotlinx.coroutines.delay

class DummyNetworkClient(
    context: Context,
    private val assets: AssetManager = context.assets
) : NetworkClient {

    override suspend fun fetchUrl(url: String): Result<String> {
        try {
            delay(500)
            val json_string = assets.open(url).bufferedReader().use{
                it.readText()
            }
            return Result.success(json_string)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}

interface NetworkClient {
    suspend fun fetchUrl(url: String): Result<String>
}