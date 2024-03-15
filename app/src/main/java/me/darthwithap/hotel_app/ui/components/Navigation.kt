package me.darthwithap.hotel_app.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.ui.theme.AppTheme

@Composable
fun NavAppBar(
  onNavigateClick: () -> Unit,
  modifier: Modifier = Modifier,
  title: String = "",
  actions: @Composable RowScope.() -> Unit
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    IconButton(onClick = onNavigateClick) {
      Icon(
        modifier = Modifier
          .size(24.dp)
          .padding(end = 16.dp),
        painter = painterResource(R.drawable.nav_appbar_back_chevron_black),
        contentDescription = stringResource(R.string.navigate_up),
        tint = if (AppTheme.isDark) AppTheme.colors.light else AppTheme.colors.dark,
      )
    }
    Text(
      text = title,
      style = AppTheme.typography.subtitle18Regular,
      color = AppTheme.onSurfaceColor,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.width(12.dp))
    Row(
      modifier = Modifier.weight(1f),
      horizontalArrangement = Arrangement.End
    ) {
      actions()
    }
  }
}

@Composable
fun NavAppBarActions(
  @DrawableRes iconResId: Int,
  onClick: () -> Unit,
  contentDescription: String,
  modifier: Modifier = Modifier,
  tint: Color = AppTheme.onSurfaceColor
) {
  IconButton(
    modifier = modifier,
    onClick = onClick
  ) {
    Icon(
      painter = painterResource(iconResId),
      contentDescription = contentDescription,
      tint = tint
    )
  }
}