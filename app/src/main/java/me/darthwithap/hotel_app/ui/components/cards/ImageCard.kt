package me.darthwithap.hotel_app.ui.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.darthwithap.hotel_app.ui.theme.AppTheme

@Composable
fun ImageCard(
  imageUrl: String,
  onClick: () -> Unit,
  selected: Boolean = false,
  selectable: Boolean = false,
  toggled: Boolean = false,
  @DrawableRes toggledIcon: Int? = null,
  @DrawableRes untoggledIcon: Int? = null,
  onIconToggle: (Boolean) -> Unit = {},
  modifier: Modifier = Modifier,
  overlayText: String? = null,
  title: String? = null,
  line2: (() -> String)? = null,
  line3: (() -> String)? = null,
  iconTint: Color = AppTheme.onSurfaceColor,
  aspectRatio: ImageCardAspectRatio = ImageCardAspectRatio.Square
) {

  Surface(
    modifier = modifier
      .width(aspectRatio.width)
      .clip(AppTheme.shapes.extraSmall),
    color = if (AppTheme.isDark) AppTheme.colors.dark2 else AppTheme.colors.light,
    onClick = onClick
  ) {
    Column(
      modifier = Modifier
        .width(IntrinsicSize.Min)
    ) {

      Surface(
        modifier = Modifier
          .size(width = aspectRatio.width, aspectRatio.height)
          .clip(AppTheme.shapes.extraSmall),
        shadowElevation = 2.dp,
      ) {
        val borderPadding = if (selected && selectable) 3.dp else 0.dp
        val borderColor = if (selected && selectable) AppTheme.colors.primary else AppTheme.surfaceColor
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(borderColor)
            .padding(borderPadding)
            .clip(AppTheme.shapes.extraSmall)
        ) {
          AsyncImage(
            modifier = Modifier
              .fillMaxWidth()
              .aspectRatio(aspectRatio.width / aspectRatio.height)
              .clip(AppTheme.shapes.extraSmall),
            model = imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
          )
          // Overlay for the image if exists
          Column(
            modifier = Modifier.fillMaxWidth()
          ) {
            val icon = when {
              toggled && toggledIcon != null -> toggledIcon
              !toggled && untoggledIcon != null -> untoggledIcon
              else -> null
            }
            icon?.let {
              IconButton(
                onClick = { onIconToggle(!toggled) },
                modifier = Modifier
                  .align(Alignment.End)
                  .padding(12.dp)
                  .size(28.dp)
                  .background(
                    color = if (AppTheme.isDark) AppTheme.colors.black70 else AppTheme.colors.white70,
                    shape = CircleShape
                  )
                  .padding(6.dp),
              ) {
                Icon(
                  painter = painterResource(id = it),
                  contentDescription = null,
                  tint = iconTint,
                  modifier = Modifier.size(16.dp)
                )
              }
            }
            Spacer(modifier = Modifier.weight(1f))
            overlayText?.let {
              Text(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(12.dp),
                text = it,
                style = AppTheme.typography.title14Regular
                  .copy(fontWeight = FontWeight.Medium),
                color = AppTheme.primaryTextColor
              )
            }
          }
        }
      }
      if (title != null || line2 != null || line3 != null) {
        CardDetails(
          name = title,
          line2 = line2,
          line3 = line3,
          selected = selected,
          selectable = selectable
        )
      }
    }
  }
}

@Composable
private fun CardDetails(
  name: String?,
  line2: (() -> String)?,
  line3: (() -> String)?,
  modifier: Modifier = Modifier,
  selected: Boolean = true,
  selectable: Boolean = false
) {
  Column(modifier = modifier) {
    Spacer(modifier = Modifier.height(8.dp))
    name?.let {
      Text(
        text = it,
        style = AppTheme.typography.title14Regular,
        color = if (!selected && selectable) AppTheme.onSurface40Color else AppTheme.primaryTextColor,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    }
    line2?.let {
      Text(
        text = it(),
        style = AppTheme.typography.caption12Regular,
        color = AppTheme.onSurface40Color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )
    }
    Spacer(modifier = Modifier.height(2.dp))
    line3?.let {
      Text(
        text = it(),
        style = AppTheme.typography.caption12Regular,
        color = AppTheme.onSurface40Color
      )
    }
  }
}

enum class ImageCardAspectRatio(val width: Dp, val height: Dp) {
  Square(150.dp, 140.dp),
  Landscape(165.dp, 140.dp)
}