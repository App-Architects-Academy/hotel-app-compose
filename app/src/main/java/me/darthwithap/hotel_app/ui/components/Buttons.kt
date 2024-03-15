package me.darthwithap.hotel_app.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.ui.theme.AppTheme

private class PrimaryButtonDefaults(val isAnti: Boolean = false) {
  @Composable
  fun containerColor() = if (isAnti) AppTheme.surfaceColor else AppTheme.onSurfaceColor

  @Composable
  fun contentColor() = if (isAnti) AppTheme.onSurfaceColor else AppTheme.surfaceColor

  @Composable
  fun disabledContainerColor() = if (isAnti) AppTheme.surface30Color else AppTheme.onSurface30Color
}

private class SecondaryButtonDefaults(val isAnti: Boolean = false) {
  @Composable
  fun containerColor() = AppTheme.colorScheme.transparent

  @Composable
  fun contentColor() = if (isAnti) AppTheme.surfaceColor else AppTheme.primaryTextColor

  @Composable
  fun disabledContentColor() = if (isAnti) AppTheme.surface40Color else AppTheme.onSurface40Color
}

@Composable
fun PrimaryButton(
  text: String,
  onClick: () -> Unit,
  buttonSize: ButtonSize,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  shape: Shape = AppTheme.shapes.extraSmall,
  elevation: Dp = 2.dp,
  buttonColors: ButtonColors? = null,
  @DrawableRes leadingIconResId: Int? = null,
  @DrawableRes trailingIconResId: Int? = null,
  isAnti: Boolean = false
) {
  val buttonDefaults = PrimaryButtonDefaults(isAnti)
  val colors = ButtonDefaults.buttonColors(
    containerColor = buttonDefaults.containerColor(),
    contentColor = buttonDefaults.contentColor(),
    disabledContainerColor = buttonDefaults.disabledContainerColor(),
    disabledContentColor = buttonDefaults.contentColor(),
  )
  val elevation = ButtonDefaults.elevatedButtonElevation(
    defaultElevation = elevation
  )
  val contentPadding: PaddingValues = when (buttonSize) {
    ButtonSize.Small -> PaddingValues(start = 24.dp, top = 4.dp, end = 24.dp, bottom = 6.dp)
    ButtonSize.Medium -> PaddingValues(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 10.dp)
    ButtonSize.Large -> PaddingValues(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 14.dp)
  }

  val textStyle: TextStyle = when (buttonSize) {
    ButtonSize.Small -> AppTheme.typography.overline10
    ButtonSize.Medium -> AppTheme.typography.title14
    ButtonSize.Large -> AppTheme.typography.forms16
  }

  val iconSize: Dp = when (buttonSize) {
    ButtonSize.Small -> 12.dp
    ButtonSize.Medium -> 16.dp
    ButtonSize.Large -> 20.dp
  }

  ElevatedButton(
    modifier = modifier,
    shape = shape,
    colors = buttonColors ?: colors,
    enabled = enabled,
    onClick = onClick,
    elevation = elevation,
    contentPadding = contentPadding
  ) {
    leadingIconResId?.let { id ->
      Icon(
        modifier = Modifier.size(iconSize),
        painter = painterResource(id),
        contentDescription = null
      )
      Spacer(modifier = Modifier.width(4.dp))
    }
    Text(
      text = text,
      style = textStyle,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )
    trailingIconResId?.let { id ->
      Spacer(modifier = Modifier.width(4.dp))
      Icon(
        modifier = Modifier.size(iconSize),
        painter = painterResource(id),
        contentDescription = null
      )
    }
  }

}

@Composable
fun SecondaryButton(
  text: String,
  onClick: () -> Unit,
  buttonSize: ButtonSize,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  shape: Shape = AppTheme.shapes.extraSmall,
  buttonColors: ButtonColors? = null,
  borderColor: BorderColor? = null,
  @DrawableRes leadingIconResId: Int? = null,
  @DrawableRes trailingIconResId: Int? = null,
  isAnti: Boolean = false
) {
  val buttonDefaults = SecondaryButtonDefaults(isAnti)
  val colors = ButtonDefaults.outlinedButtonColors(
    containerColor = buttonDefaults.containerColor(),
    contentColor = buttonDefaults.contentColor(),
    disabledContainerColor = buttonDefaults.containerColor(),
    disabledContentColor = buttonDefaults.disabledContentColor(),
  )

  val contentPadding: PaddingValues = when (buttonSize) {
    ButtonSize.Small -> PaddingValues(start = 24.dp, top = 4.dp, end = 24.dp, bottom = 6.dp)
    ButtonSize.Medium -> PaddingValues(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 10.dp)
    ButtonSize.Large -> PaddingValues(start = 24.dp, top = 12.dp, end = 24.dp, bottom = 14.dp)
  }

  val textStyle: TextStyle = when (buttonSize) {
    ButtonSize.Small -> AppTheme.typography.overline10
    ButtonSize.Medium -> AppTheme.typography.title14
    ButtonSize.Large -> AppTheme.typography.forms16
  }

  val iconSize: Dp = when (buttonSize) {
    ButtonSize.Small -> 12.dp
    ButtonSize.Medium -> 16.dp
    ButtonSize.Large -> 20.dp
  }

  val border: BorderColor = borderColor ?: BorderColor(
    buttonDefaults.contentColor(),
    buttonDefaults.disabledContentColor()
  )

  OutlinedButton(
    modifier = modifier,
    shape = shape,
    colors = buttonColors ?: colors,
    enabled = enabled,
    onClick = onClick,
    border = BorderStroke(2.dp, if (enabled) border.enabled else border.disabled),
    contentPadding = contentPadding
  ) {
    leadingIconResId?.let { id ->
      Icon(
        modifier = Modifier.size(iconSize),
        painter = painterResource(id),
        contentDescription = null
      )
      Spacer(modifier = Modifier.width(4.dp))
    }
    Text(
      text = text,
      style = textStyle,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis
    )
    trailingIconResId?.let { id ->
      Spacer(modifier = Modifier.width(4.dp))
      Icon(
        modifier = Modifier.size(iconSize),
        painter = painterResource(id),
        contentDescription = null
      )
    }
  }

}

data class BorderColor(val enabled: Color, val disabled: Color)

enum class ButtonSize { Small, Medium, Large }
