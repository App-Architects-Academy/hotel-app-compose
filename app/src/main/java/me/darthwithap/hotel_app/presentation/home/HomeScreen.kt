package me.darthwithap.hotel_app.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import me.darthwithap.hotel_app.presentation.hoteldetails.HotelDetailsScreen

object HomeScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Column {
            Button(onClick = {
                navigator?.push(HotelDetailsScreen)
            }) {
                Text(text = "Hotel Details Screen")
            }
        }
    }
}