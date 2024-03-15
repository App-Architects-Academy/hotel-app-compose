package me.darthwithap.hotel_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.ui.theme.AppTheme

object InputFieldDefaults {
  val iconSize: Dp = 24.dp
  val shape: Shape
    @Composable get() = AppTheme.shapes.extraSmall

  @Composable
  fun textColor() = AppTheme.primaryTextColor

  @Composable
  fun disabledTextColor() =
    if (AppTheme.isDark) AppTheme.colors.white70 else AppTheme.colors.black70

  @Composable
  fun placeholderColor() = AppTheme.secondaryTextColor

  @Composable
  fun disabledPlaceholderColor() = AppTheme.onSurface30Color

  @Composable
  fun focusedBorderColor() = AppTheme.onSurfaceColor

  @Composable
  fun disabledBorderColor() =
    if (AppTheme.isDark) AppTheme.colors.white05 else AppTheme.colors.black05

  @Composable
  fun unfocusedBorderColor() =
    if (AppTheme.isDark) AppTheme.colors.white10 else AppTheme.colors.black10

  @Composable
  fun containerColor() =
    if (AppTheme.isDark) AppTheme.colors.black70 else AppTheme.colors.white70

  @Composable
  fun unfocusedIconColor(customColor: Color, isPlaceholder: Boolean) = customColor.takeOrElse {
    return if (isPlaceholder) {
      AppTheme.secondaryTextColor
    } else {
      AppTheme.onSurfaceColor
    }
  }

  @Composable
  fun focusedIconColor() = AppTheme.onSurfaceColor

  @Composable
  fun cursorColor() = AppTheme.onSurfaceColor

  @Composable
  fun selectionColors() = TextSelectionColors(
    handleColor = AppTheme.onSurfaceColor,
    backgroundColor = AppTheme.onSurface40Color
  )

  @Composable
  fun errorColor() = AppTheme.colors.red
}

@Composable
fun InputField(
  value: String,
  onValueChange: (String) -> Unit,
  placeholderText: String,
  modifier: Modifier = Modifier,
  leadingIcon: @Composable (() -> Unit)? = null,
  trailingIcon: @Composable (() -> Unit)? = null,
  enabled: Boolean = true,
  isError: Boolean = false,
  supportingText: String? = null,
  textStyle: TextStyle = AppTheme.typography.title14Regular,
  unfocusedLeadingIconColor: Color = Color.Unspecified,
  unfocusedTrailingIconColor: Color = Color.Unspecified,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
  val isPlaceholder by remember(value.isEmpty()) { mutableStateOf(value.isEmpty()) }

  val colors = OutlinedTextFieldDefaults.colors(
    focusedTextColor = InputFieldDefaults.textColor(),
    unfocusedTextColor = InputFieldDefaults.textColor(),
    unfocusedPlaceholderColor = InputFieldDefaults.placeholderColor(),
    focusedPlaceholderColor = InputFieldDefaults.placeholderColor(),
    focusedBorderColor = InputFieldDefaults.focusedBorderColor(),
    unfocusedBorderColor = InputFieldDefaults.unfocusedBorderColor(),
    focusedContainerColor = InputFieldDefaults.containerColor(),
    // Passing unfocusedContainer color this way causes some blinking in the transition
    // between focused and unfocused states.
    // unfocusedContainerColor = InputFieldDefaults.unfocusedContainerColor(),
    unfocusedLeadingIconColor = InputFieldDefaults.unfocusedIconColor(
      customColor = unfocusedLeadingIconColor, // For custom icons
      isPlaceholder = isPlaceholder
    ),
    focusedLeadingIconColor = InputFieldDefaults.focusedIconColor(),
    unfocusedTrailingIconColor = InputFieldDefaults.unfocusedIconColor(
      customColor = unfocusedTrailingIconColor, // For custom icons
      isPlaceholder = isPlaceholder
    ),
    focusedTrailingIconColor = InputFieldDefaults.focusedIconColor(),
    cursorColor = InputFieldDefaults.cursorColor(),
    selectionColors = InputFieldDefaults.selectionColors(),
    errorBorderColor = InputFieldDefaults.errorColor(),
    errorCursorColor = InputFieldDefaults.errorColor(),
    errorLeadingIconColor = InputFieldDefaults.errorColor(),
    errorTrailingIconColor = InputFieldDefaults.errorColor(),
    errorSupportingTextColor = InputFieldDefaults.errorColor(),
    disabledBorderColor = InputFieldDefaults.disabledBorderColor(),
    disabledTrailingIconColor = InputFieldDefaults.unfocusedIconColor(
      customColor = unfocusedTrailingIconColor,
      isPlaceholder = isPlaceholder
    ),
    disabledLeadingIconColor = InputFieldDefaults.unfocusedIconColor(
      customColor = unfocusedLeadingIconColor,
      isPlaceholder = isPlaceholder
    ),
    disabledPlaceholderColor = InputFieldDefaults.disabledPlaceholderColor(),
    disabledTextColor = InputFieldDefaults.disabledTextColor()
  )

  val focused by interactionSource.collectIsFocusedAsState()
  OutlinedTextField(
    modifier = modifier.then(
      // A workaround of some blinking in the transition between focused and unfocused states.
      if (!focused) Modifier.background(
        color = InputFieldDefaults.containerColor(),
        shape = InputFieldDefaults.shape
      ) else Modifier
    ),
    value = value,
    onValueChange = onValueChange,
    textStyle = textStyle,
    enabled = enabled,
    singleLine = true,
    shape = InputFieldDefaults.shape,
    colors = colors,
    placeholder = {
      Text(
        text = placeholderText,
        style = AppTheme.typography.title14Regular
      )
    },
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    isError = isError,
    supportingText = supportingText?.let { { Text(text = it) } },
    keyboardActions = keyboardActions,
    keyboardOptions = keyboardOptions,
    visualTransformation = visualTransformation,
    interactionSource = interactionSource
  )
}
@Composable
fun EmailInputField(
  value: String,
  onValueChange: (String) -> Unit,
  isError: Boolean,
  modifier: Modifier = Modifier,
  supportingText: String? = null,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
  InputField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    placeholderText = stringResource(R.string.enter_email_address),
    supportingText = supportingText,
    isError = isError,
    keyboardOptions = keyboardOptions
  )
}

@Composable
fun PasswordInputField(
  value: String,
  onValueChange: (String) -> Unit,
  isError: Boolean,
  modifier: Modifier = Modifier,
  supportingText: String? = null,
  placeholderText: String = stringResource(R.string.password_placeholder_text),
  keyboardOptions: KeyboardOptions = KeyboardOptions(
    autoCorrect = false,
    keyboardType = KeyboardType.Password,
    imeAction = ImeAction.Done
  )
) {
  var passwordHidden by remember { mutableStateOf(true) }

  InputField(
    modifier = modifier,
    value = value,
    onValueChange = onValueChange,
    placeholderText = placeholderText,
    supportingText = supportingText,
    isError = isError,
    trailingIcon = {
      Icon(
        modifier = Modifier
          .size(InputFieldDefaults.iconSize)
          .clickable { passwordHidden = !passwordHidden },
        painter = painterResource(id = if (passwordHidden) R.drawable.ic_password_hide else R.drawable.ic_password_show),
        contentDescription = stringResource(R.string.cd_password_icon)
      )
    },
    visualTransformation = if (passwordHidden) PasswordVisualTransformation('*') else VisualTransformation.None,
    keyboardOptions = keyboardOptions
  )
}