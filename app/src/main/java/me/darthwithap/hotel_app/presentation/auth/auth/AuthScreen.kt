package me.darthwithap.hotel_app.presentation.auth.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.ui.components.ButtonSize
import me.darthwithap.hotel_app.ui.components.PrimaryButton
import me.darthwithap.hotel_app.ui.components.SecondaryButton
import me.darthwithap.hotel_app.ui.theme.AppTheme

@Composable
fun AuthScreen(
  onLoginClick: () -> Unit,
  onRegisterClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(modifier = modifier.fillMaxSize()) {
    Image(
      painter = painterResource(id = R.drawable.auth_background),
      contentDescription = stringResource(R.string.auth_background),
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop
    )
    AuthScreenContent(
      onLoginClick = onLoginClick,
      onRegisterClick = onRegisterClick,
      modifier = modifier.navigationBarsPadding()
    )

  }
}

@Composable
fun AuthScreenContent(
  onLoginClick: () -> Unit,
  onRegisterClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(24.dp),
    horizontalAlignment = Alignment.Start
  ) {
    Column(modifier = Modifier.padding(top = 40.dp)) {
      val drawable =
        if (AppTheme.isDark) R.drawable.app_logo_horizontal_night else R.drawable.app_logo_horizontal
      Image(
        painter = painterResource(id = drawable),
        contentDescription = stringResource(
          id = R.string.app_logo_small
        )
      )
      Text(
        text = stringResource(id = R.string.auth_subtitle),
        style = AppTheme.typography.headlineMedium26Regular,
        color = if (AppTheme.isDark) AppTheme.colors.black70 else AppTheme.colors.white70,
        modifier = Modifier.padding(top = 24.dp, bottom = 32.dp)
      )
    }
    Spacer(modifier = Modifier.weight(1f))

    Column(modifier = Modifier.padding(bottom = 48.dp)) {
      PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.login),
        onClick = onLoginClick,
        buttonSize = ButtonSize.Large,
        isAnti = true
      )
      Spacer(modifier = Modifier.height(8.dp))
      SecondaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.register),
        onClick = onRegisterClick,
        buttonSize = ButtonSize.Large,
        isAnti = true
      )
    }
  }
}