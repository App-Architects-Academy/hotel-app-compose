package me.darthwithap.hotel_app.presentation.auth.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.R

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
      Image(
        painter = painterResource(id = R.drawable.app_logo_horizontal),
        contentDescription = stringResource(
          id = R.string.app_logo_small_white
        )
      )
      Text(
        text = stringResource(id = R.string.auth_subtitle),
        style = MaterialTheme.typography.displaySmall,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
      )
    }

    Spacer(modifier = Modifier.weight(1f))

    Column(modifier = Modifier.padding(bottom = 48.dp)) {
      Button(
        onClick = onLoginClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
      ) {
        Text(text = stringResource(id = R.string.login), color = Color.Black)
      }
      OutlinedButton(
        onClick = onRegisterClick,
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 8.dp),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent)
      ) {
        Text(text = stringResource(id = R.string.register), color = Color.White)
      }
    }
  }
}