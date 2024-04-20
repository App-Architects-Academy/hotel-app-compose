package me.darthwithap.hotel_app.ui.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.ui.theme.AppTheme

@Composable
fun TitleCard(
  title: String,
  subtitle: String? = null,
  @DrawableRes icon: Int? = null,
  iconPosition: IconPosition = IconPosition.Left,
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {}
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .padding(4.dp)
      .background(AppTheme.surfaceColor)
      .clickable(onClick = onClick),
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
    ) {

      if (iconPosition == IconPosition.Left && icon != null) {
        Icon(
          painter = painterResource(id = icon),
          contentDescription = null,
          modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))
      }

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = title,
          style = AppTheme.typography.subtitle18Regular,
          color = AppTheme.primaryTextColor
        )

        subtitle?.let {
          Text(
            text = it,
            style = AppTheme.typography.caption12Regular,
            color = AppTheme.onSurface40Color
          )
        }

      }

      if (iconPosition == IconPosition.Right && icon != null) {
        Spacer(Modifier.width(16.dp))
        Icon(
          painter = painterResource(id = icon),
          contentDescription = null,
          modifier = Modifier.size(24.dp)
        )
      }
    }
  }
}

enum class IconPosition {
  Left, Right
}
