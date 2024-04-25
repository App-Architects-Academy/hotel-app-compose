package me.darthwithap.hotel_app.ui.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun String.loadRoundedBottomImage(description: String = "") {
  AsyncImage(
    modifier = Modifier
      .fillMaxSize()
      .clip(RoundedCornerShape(bottomStart = 60f, bottomEnd = 60f)),
    model = this,
    contentScale = ContentScale.Crop,
    contentDescription = description
  )
}

@Composable
fun String.loadCircleImage(description: String = "", modifier: Modifier = Modifier) {
  AsyncImage(
    modifier = modifier
      .clip(CircleShape),
    model = this,
    contentScale = ContentScale.Crop,
    contentDescription = description
  )
}