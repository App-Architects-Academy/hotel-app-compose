package me.darthwithap.hotel_app.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.darthwithap.hotel_app.R

private val BeVietnamProFontFamily = FontFamily(
  Font(R.font.bevietnam_pro_regular, weight = FontWeight.Normal),
  Font(R.font.bevietnam_pro_bold, weight = FontWeight.Bold)
)

object FontSize {
  val extraSmall = 10.sp
  val small = 12.sp
  val regular = 14.sp
  val medium = 16.sp
  val large = 18.sp
  val extraLarge = 24.sp
  val huge = 32.sp
}

data class AppTypography(
  val overline10: TextStyle,
  val caption12: TextStyle,
  val title14: TextStyle,
  val forms16: TextStyle,
  val subtitle18: TextStyle,
  val subForms21: TextStyle,
  val headlineSmall24: TextStyle,
  val headlineMedium26: TextStyle,
  val headlineLarge32: TextStyle
)

val appTypography = AppTypography(
  overline10 = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.extraSmall,
    lineHeight = 14.sp
  ),
  caption12 = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.small,
    lineHeight = 16.sp
  ),
  title14 = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.regular,
    lineHeight = 18.sp
  ),
  forms16 = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.medium,
    lineHeight = 22.sp
  ),
  subtitle18 = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.large,
    lineHeight = 20.sp
  ),
  subForms21 = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 21.sp,
    lineHeight = 30.sp
  ),
  headlineSmall24 = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.extraLarge,
    lineHeight = 32.sp
  ),
  headlineMedium26 = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 26.sp,
    lineHeight = 28.sp
  ),
  headlineLarge32 = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.huge,
    lineHeight = 34.sp
  )
)

val LocalAppTypography = staticCompositionLocalOf { appTypography }