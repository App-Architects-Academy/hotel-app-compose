package me.darthwithap.hotel_app.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
  visible: Boolean = true,
  actions: @Composable RowScope.() -> Unit
) {
  val tint = if (visible && !AppTheme.isDark) AppTheme.colors.dark else AppTheme.colors.light

  Box(
    modifier = modifier
      .then(if (visible) Modifier.background(AppTheme.surfaceColor) else Modifier)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(top = 14.dp, start = 16.dp, end = 16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(
        modifier = Modifier.padding(end = 16.dp),
        onClick = onNavigateClick
      ) {
        Icon(
          modifier = Modifier
            .size(24.dp),
          painter = painterResource(R.drawable.nav_appbar_back_chevron_black),
          contentDescription = stringResource(R.string.navigate_up),
          tint = tint,
        )
      }
      Text(
        text = if (visible) title else "",
        style = AppTheme.typography.subtitle18Regular,
        color = tint,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
      Spacer(modifier = Modifier.width(8.dp))
      Row(
        modifier = Modifier.weight(1f),
        horizontalArrangement = Arrangement.End
      ) {
        actions()
      }
    }
  }
}

@Composable
fun NavAppBarAction(
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