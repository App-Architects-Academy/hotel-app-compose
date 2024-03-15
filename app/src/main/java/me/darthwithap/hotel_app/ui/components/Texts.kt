package me.darthwithap.hotel_app.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import me.darthwithap.hotel_app.ui.theme.AppTheme
import me.darthwithap.hotel_app.ui.theme.BeVietnamProFontFamily

@Composable
fun PrivacyTermsText(
  onPrivacyPolicyClick: () -> Unit,
  onTermsAndConditionsClick: () -> Unit
) {

  val annotatedText = buildAnnotatedString {
    append("By tapping ")
    withStyle(
      style = SpanStyle(
        color = AppTheme.onSurfaceColor,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = BeVietnamProFontFamily
      )
    ) {
      append("Register & Accept")
    }
    append(" you confirm that you have read our ")

    pushStringAnnotation(tag = "privacy_policy", annotation = "privacy_policy")
    withStyle(
      style = SpanStyle(
        color = AppTheme.colors.primary,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = BeVietnamProFontFamily
      )
    ) {
      append("Privacy Policy")
    }
    pop() // This call removes the last pushed annotation, so we can add more annotations later
    append(" and agree to our ")
    pushStringAnnotation(tag = "terms_and_conditions", annotation = "terms_and_conditions")
    withStyle(
      style = SpanStyle(
        color = AppTheme.colors.primary,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = BeVietnamProFontFamily
      )
    ) {
      append("Terms and Conditions")
    }
    pop()
  }

  ClickableText(
    text = annotatedText,
    onClick = { offset ->
      // We check if there's an annotation attached to the text at the clicked position
      annotatedText.getStringAnnotations(tag = "privacy_policy", start = offset, end = offset)
        .firstOrNull()?.let {
          onPrivacyPolicyClick()
        }
      annotatedText.getStringAnnotations(tag = "terms_and_conditions", start = offset, end = offset)
        .firstOrNull()?.let {
          onTermsAndConditionsClick()
        }
    },
    style = AppTheme.typography.caption12Regular.copy(color = AppTheme.onSurface40Color)
  )
}