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
  val overline10 = 10.sp
  val caption12 = 12.sp
  val title14 = 14.sp
  val forms16 = 16.sp
  val subtitle18 = 18.sp
  val subForms21 = 21.sp
  val headlineSmall24 = 24.sp
  val headlineMedium26 = 26.sp
  val headlineLarge32 = 32.sp
}

data class AppTypography(
  val overline10Regular: TextStyle,
  val overline10Bold: TextStyle,
  val caption12Regular: TextStyle,
  val caption12Bold: TextStyle,
  val title14Regular: TextStyle,
  val title14Bold: TextStyle,
  val forms16Regular: TextStyle,
  val forms16Bold: TextStyle,
  val subtitle18Regular: TextStyle,
  val subtitle18Bold: TextStyle,
  val subForms21Regular: TextStyle,
  val subForms21Bold: TextStyle,
  val headlineSmall24Regular: TextStyle,
  val headlineSmall24Bold: TextStyle,
  val headlineMedium26Regular: TextStyle,
  val headlineMedium26Bold: TextStyle,
  val headlineLarge32Regular: TextStyle,
  val headlineLarge32Bold: TextStyle
)

val appTypography = AppTypography(
  overline10Regular = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.overline10,
    lineHeight = 14.sp
  ),
  overline10Bold = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = FontSize.overline10,
    lineHeight = 14.sp
  ),
  caption12Regular = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.caption12,
    lineHeight = 16.sp
  ),
  caption12Bold = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = FontSize.caption12,
    lineHeight = 16.sp
  ),
  title14Regular = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.title14,
    lineHeight = 18.sp
  ),
  title14Bold = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = FontSize.title14,
    lineHeight = 18.sp
  ),
  forms16Regular = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.forms16,
    lineHeight = 22.sp
  ),
  forms16Bold = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = FontSize.forms16,
    lineHeight = 22.sp
  ),
  subtitle18Regular = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.subtitle18,
    lineHeight = 20.sp
  ),
  subtitle18Bold = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = FontSize.subtitle18,
    lineHeight = 20.sp
  ),
  subForms21Regular = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.subForms21,
    lineHeight = 30.sp
  ),
  subForms21Bold = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = FontSize.subForms21,
    lineHeight = 30.sp
  ),
  headlineSmall24Regular = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.headlineSmall24,
    lineHeight = 32.sp
  ),
  headlineSmall24Bold = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = FontSize.headlineSmall24,
    lineHeight = 32.sp
  ),
  headlineMedium26Regular = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.headlineMedium26,
    lineHeight = 28.sp
  ),
  headlineMedium26Bold = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = FontSize.headlineMedium26,
    lineHeight = 28.sp
  ),
  headlineLarge32Regular = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = FontSize.headlineLarge32,
    lineHeight = 34.sp
  ),
  headlineLarge32Bold = TextStyle(
    fontFamily = BeVietnamProFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = FontSize.headlineLarge32,
    lineHeight = 34.sp
  )
)

val LocalAppTypography = staticCompositionLocalOf { appTypography }