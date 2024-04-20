package me.darthwithap.hotel_app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColorScheme(

  // Shades
  val black100: Color = Color(0xFF000000),
  val black70: Color = Color(0xB3000000),
  val black40: Color = Color(0x66000000),
  val black30: Color = Color(0x4D000000),
  val black20: Color = Color(0x33000000),
  val black10: Color = Color(0x1A000000),
  val black05: Color = Color(0x0D000000),
  val white100: Color = Color(0xFFFFFFFF),
  val white70: Color = Color(0xB3FFFFFF),
  val white40: Color = Color(0x66FFFFFF),
  val white30: Color = Color(0x4DFFFFFF),
  val white20: Color = Color(0x33FFFFFF),
  val white10: Color = Color(0x1AFFFFFF),
  val white05: Color = Color(0x0DFFFFFF),

  val greyscale900: Color = Color(0xFF212121),
  val dark2: Color = Color(0xFF1F222A),

  // Solid
  val primary: Color = Color(0xFF2664ED),
  val light: Color = white100,
  val dark: Color = black100,
  val red: Color = Color(0xFFBA1735),
  val green: Color = Color(0xFF1ACC72),
  val iconLight: Color = black20,
  val iconDark: Color = white20,
  val borderLight: Color = black05,

  // Surface
  val lightCard: Color = Color(0xFFEFEFF4),
  val transparent: Color = Color(0x00000000),

  // Buttons
  val primaryLight: Color = black100,
  val interactiveLight: Color = black10,
  val disableLight: Color = black30,
  val primaryDark: Color = white100,
  val interactiveDark: Color = white10,
  val disableDark: Color = white30
)

val appColorScheme = AppColorScheme()

val LocalAppColorScheme = staticCompositionLocalOf { appColorScheme }
