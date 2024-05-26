package me.darthwithap.hotel_app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import me.darthwithap.hotel_app.presentation.hoteldetails.HotelDetailsScreen
import me.darthwithap.hotel_app.ui.components.ButtonSize
import me.darthwithap.hotel_app.ui.components.PrimaryButton

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
        ) {

            PrimaryButton(
                text = "Hotel Details Screen",
                onClick = { navigator?.push(HotelDetailsScreen) },
                buttonSize = ButtonSize.Large,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}