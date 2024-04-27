package me.darthwithap.hotel_app.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

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

@SuppressLint("SimpleDateFormat")
fun Long.toSimpleDate(): String {
  val date = Date(this)
  val format = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
  return format.format(date)
}

fun LocalDate.toDateString(): String {
  val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
  return this.format(formatter)
}

fun Context.showToast(message: String) {
  Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}