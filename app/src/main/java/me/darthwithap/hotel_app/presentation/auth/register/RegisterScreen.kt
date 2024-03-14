package me.darthwithap.hotel_app.presentation.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.R

@Composable
fun RegisterScreen(
  onNavigateBackClick: () -> Unit,
  onRegisterAndAcceptClick: () -> Unit,
  onEmailValueChange: (String) -> Unit,
  onPasswordValueChange: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  RegisterScreenContent(
    onNavigateBackClick,
    onEmailValueChange,
    onPasswordValueChange,
    onRegisterAndAcceptClick,
    modifier
      .navigationBarsPadding()
      .systemBarsPadding(),
  )
}

@Composable
fun RegisterScreenContent(
  onNavigateBackClick: () -> Unit,
  onEmailValueChange: (String) -> Unit,
  onPasswordValueChange: (String) -> Unit,
  onRegisterAndAcceptClick: () -> Unit,
  modifier: Modifier
) {
  // Todo: Add Loading Widget
  Column(modifier = modifier.padding(16.dp)) {
    // Navigation bar with back icon
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(onClick = onNavigateBackClick) {
        Icon(
          painter = painterResource(R.drawable.nav_appbar_back_chevron_black),
          contentDescription = stringResource(R.string.navigate_up),
        )
      }
    }
    Text(
      modifier = modifier,
      text = stringResource(id = R.string.register_screen_heading),
      style = MaterialTheme.typography.bodyMedium,
      color = Color.Black,
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      modifier = modifier,
      text = stringResource(id = R.string.register_screen_subheading),
      style = MaterialTheme.typography.bodySmall,
      color = Color.Gray
    )

    Spacer(modifier = Modifier.height(26.dp))
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp),
      horizontalAlignment = Alignment.Start
    ) {
      Text(text = "Email Address")


      var emailInteractionSource = remember { MutableInteractionSource() }
      val emailFocused by emailInteractionSource.collectIsFocusedAsState()
      OutlinedTextField(
        modifier = Modifier
          .fillMaxWidth()
          .then(
            if (!emailFocused) Modifier.background(
              color = Color.Gray,
              shape = RoundedCornerShape(4.dp)
            ) else Modifier
          ),
        value = "", // Todo: Add state.email value here
        onValueChange = onEmailValueChange,
        textStyle = TextStyle(),
        singleLine = true,
        placeholder = {
          Text(
            text = stringResource(R.string.enter_email_address),
          )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        interactionSource = emailInteractionSource
      )

      Spacer(modifier = Modifier.height(16.dp))
      Text(text = "Set Password")

      var passwordInteractionSource = remember { MutableInteractionSource() }
      val passwordFocused by emailInteractionSource.collectIsFocusedAsState()
      OutlinedTextField(
        modifier = Modifier
          .fillMaxWidth()
          .then(
            if (!passwordFocused) Modifier.background(
              color = Color.Gray, // add unfocusedColor container color here
              shape = RoundedCornerShape(4.dp)
            ) else Modifier
          ),
        value = "", // Todo: Add state.password value here
        onValueChange = onPasswordValueChange,
        textStyle = TextStyle(),
        singleLine = true,
        placeholder = {
          Text(
            text = stringResource(R.string.password_placeholder_text),
          )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        interactionSource = passwordInteractionSource
      )
    }

    Spacer(modifier = Modifier.height(26.dp))
    PrivacyTermsText(onPrivacyPolicyClick = {}, onTermsAndConditionsClick = {})
    Spacer(modifier = Modifier.weight(1f))
    var registerButtonEnabled = true
    ElevatedButton(
      modifier = modifier,
      shape = RoundedCornerShape(4.dp),
      colors = ButtonDefaults.buttonColors(
        containerColor = Color.Black, // Todo : Add logic to see if enabled or disabled
        contentColor = Color.White,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.White,
      ),
      enabled = registerButtonEnabled,
      onClick = onRegisterAndAcceptClick,
      elevation = ButtonDefaults.elevatedButtonElevation(
        defaultElevation = 4.dp
      )
    ) {
      Text(
        text = stringResource(id = R.string.register_and_accept),
        style = MaterialTheme.typography.displaySmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    }

  }
}

@Composable
private fun PrivacyTermsText(
  onPrivacyPolicyClick: () -> Unit,
  onTermsAndConditionsClick: () -> Unit
) {

  val annotatedText = buildAnnotatedString {
    append("By tapping ")
    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
      append("Register & Accept")
    }
    append(" that you have read our ")

    pushStringAnnotation(tag = "privacy_policy", annotation = "privacy_policy")
    withStyle(style = SpanStyle(color = Color.Blue)) {
      append("Privacy Policy")
    }
    pop() // This call removes the last pushed annotation, so we can add more annotations later
    append(" and agree our ")
    pushStringAnnotation(tag = "terms_and_conditions", annotation = "terms_and_conditions")
    withStyle(style = SpanStyle(color = Color.Blue)) {
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
    }
  )
}