package me.darthwithap.hotel_app

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import me.darthwithap.hotel_app.data.NetworkClient
import me.darthwithap.hotel_app.data.api.HotelAppApi


val Context.authDataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_datastrore")
val PREF_AUTHTOKEN_KEY = stringPreferencesKey("authToken")

object DISingletons {
    var hotelAppApi: HotelAppApi? = null
    var networkClient: NetworkClient? = null
}