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
  val colors: AppColorScheme
    @Composable
    get() = LocalAppColorScheme.current
  val theme: DarkTheme
    @Composable
    get() = LocalAppTheme.current
  val isDark: Boolean
    @Composable
    get() = theme.isDark
  val surfaceColor: Color
    @Composable
    get() = if (isDark) colors.dark else colors.light
  val onSurfaceColor: Color
    @Composable
    get() = if (isDark) colors.light else colors.dark
  val surface30Color: Color
    @Composable
    get() = if (isDark) colors.black30 else colors.white30
  val onSurface30Color: Color
    @Composable
    get() = if (isDark) colors.white30 else colors.black30
  val surface40Color: Color
    @Composable
    get() = if (isDark) colors.black40 else colors.white40
  val onSurface40Color: Color
    @Composable
    get() = if (isDark) colors.white40 else colors.black40
  val primaryTextColor: Color
    @Composable
    get() = onSurfaceColor
  val secondaryTextColor: Color
    @Composable
    get() = onSurface40Color
}