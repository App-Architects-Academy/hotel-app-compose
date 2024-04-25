package me.darthwithap.hotel_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.ui.theme.AppTheme

@Composable
fun HorizontalDivider(
  modifier: Modifier = Modifier
) {
  Divider(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp),
    color = AppTheme.onSurface05Color //if (AppTheme.isDark) AppTheme.colors.dark3 else AppTheme.colors.white40
  )
}

