package me.darthwithap.hotel_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
data class DarkTheme(val isDark: Boolean = false)

val LocalAppTheme = compositionLocalOf { DarkTheme() }

@Composable
fun AppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {

  CompositionLocalProvider(
    LocalAppColorScheme provides appColorScheme,
    LocalAppTypography provides appTypography,
    LocalAppShapes provides appShapes,
    LocalAppTheme provides DarkTheme(darkTheme)
  ) {
    MaterialTheme(
      content = content
    )
  }
}

object AppTheme {
  val typography: AppTypography
    @Composable
    get() = LocalAppTypography.current
  val shapes: AppShapes
    @Composable
    get() = LocalAppShapes.current
  val colorScheme: AppColorScheme
    @Composable
    get() = LocalAppColorScheme.current
  val theme: DarkTheme
    @Composable
    get() = LocalAppTheme.current
  val primaryTextColor: Color
    @Composable
    get() = if (theme.isDark) colorScheme.white100 else colorScheme.black100
  val secondaryTextColor: Color
    @Composable
    get() = if (theme.isDark) colorScheme.black40 else colorScheme.white40
}