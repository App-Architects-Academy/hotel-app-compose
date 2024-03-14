package me.darthwithap.hotel_app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class AppShapes(
  val extraSmall: Shape,
  val small: Shape,
  val medium: Shape,
  val large: Shape,
  val extraLarge: Shape,
  val big: Shape,
)

val appShapes = AppShapes(
  extraSmall = RoundedCornerShape(4.dp),
  small = RoundedCornerShape(6.dp),
  medium = RoundedCornerShape(12.dp),
  large = RoundedCornerShape(28.dp),
  extraLarge = RoundedCornerShape(34.dp),
  big = RoundedCornerShape(40.dp),
)

val LocalAppShapes = staticCompositionLocalOf { appShapes }