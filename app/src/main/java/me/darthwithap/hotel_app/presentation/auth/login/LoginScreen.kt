package me.darthwithap.hotel_app.presentation.auth.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.presentation.auth.register.RegisterScreen
import me.darthwithap.hotel_app.presentation.navigation.Routes
import me.darthwithap.hotel_app.ui.components.ButtonSize
import me.darthwithap.hotel_app.ui.components.EmailInputField
import me.darthwithap.hotel_app.ui.components.NavAppBar
import me.darthwithap.hotel_app.ui.components.PasswordInputField
import me.darthwithap.hotel_app.ui.components.PrimaryButton
import me.darthwithap.hotel_app.ui.theme.AppTheme

object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        LoginScreen(
            onNavigateBackClick = {
                navigator?.pop()
            },
            onRegisterClick = { email, pass ->
                navigator?.pop()
                navigator?.push(RegisterScreen(
                    prefillEmail = email, prefillPass = pass
                ))
            }
        )
    }
}

@Composable
fun LoginScreen(
    onNavigateBackClick: () -> Unit,
    onRegisterClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    val onLoginClick: (String, String) -> Unit by remember {
        mutableStateOf({ email, pass ->
            Log.d("Login Click", "$email, $pass")
        })
    }

    LoginScreenContent(
        onNavigateBackClick,
        onLoginClick,
        onRegisterClick,
        modifier
            .navigationBarsPadding()
            .systemBarsPadding(),
    )
}

@Composable
fun LoginScreenContent(
    onNavigateBackClick: () -> Unit,
    onLoginClick: (email: String, pass: String) -> Unit,
    onRegisterClick: (email: String, pass: String) -> Unit,
    modifier: Modifier
) {
    // Todo: Add Loading Widget

    var passwordInput by remember {
        mutableStateOf("")
    }

    var emailInput by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier.padding(16.dp)) {
        NavAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = "",
            onNavigateClick = onNavigateBackClick,
            actions = {}
        )

        Text(
            modifier = modifier,
            text = stringResource(id = R.string.login_screen_heading),
            style = AppTheme.typography.headlineSmall24Regular,
            color = AppTheme.primaryTextColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.login_screen_subheading),
            style = AppTheme.typography.forms16Regular,
            color = if (AppTheme.isDark) AppTheme.colors.white70 else AppTheme.colors.black70
        )

        Spacer(modifier = Modifier.height(26.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.email_address),
                style = AppTheme.typography.caption12Regular
            )
            EmailInputField(
                modifier = Modifier.fillMaxWidth(),
                value = emailInput, // state.email
                onValueChange = {
                    emailInput = it
                },
                isError = false, // !state.isValidEmail
                //supportingText = if (!state.isValidEmail) "Invalid email" else null,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.set_password),
                style = AppTheme.typography.caption12Regular
            )
            PasswordInputField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordInput, // state.password
                onValueChange = {
                    passwordInput = it
                },
                isError = true, // !state.isValidPassword
                //supportingText = if (!state.isValidPassword) "Invalid password" else null
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = stringResource(R.string.forgot_password),
                style = AppTheme.typography.caption12Regular.copy(color = AppTheme.colors.primary)
            )


            Text(
                text = "Register",
                style = AppTheme.typography.caption12Regular.copy(color = AppTheme.colors.primary),
                modifier = Modifier.clickable {
                    onRegisterClick(emailInput, passwordInput)
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        var buttonEnabled by remember { mutableStateOf(true) }
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            onClick = {
                onLoginClick(emailInput, passwordInput)
            },
            buttonSize = ButtonSize.Large,
            enabled = buttonEnabled // state.isRegisterButtonEnabled
        )
    }
}