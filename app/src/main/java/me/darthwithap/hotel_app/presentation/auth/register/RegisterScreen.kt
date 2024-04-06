package me.darthwithap.hotel_app.presentation.auth.register

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.presentation.auth.auth.AuthScreen
import me.darthwithap.hotel_app.presentation.navigation.Routes
import me.darthwithap.hotel_app.ui.components.ButtonSize
import me.darthwithap.hotel_app.ui.components.EmailInputField
import me.darthwithap.hotel_app.ui.components.NavAppBar
import me.darthwithap.hotel_app.ui.components.PasswordInputField
import me.darthwithap.hotel_app.ui.components.PrimaryButton
import me.darthwithap.hotel_app.ui.components.PrivacyTermsText
import me.darthwithap.hotel_app.ui.theme.AppTheme

data class RegisterScreen(
    val prefillEmail: String? = "",
    val prefillPass: String? = "",
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val context = LocalContext.current
        RegisterScreen(
            onNavigateBackClick = {
                navigator?.pop()
            },
            onRegisterAndAcceptClick = {
                Toast.makeText(context, "Registered. TODO", Toast.LENGTH_LONG).show()
            },
            onEmailValueChange = {},
            onPasswordValueChange = {},
        )
    }
}

@Composable
fun RegisterScreen(
    onNavigateBackClick: () -> Unit,
    onRegisterAndAcceptClick: () -> Unit,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    prefillEmail: String? = "",
    prefillPass: String? = "",
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    LaunchedEffect(prefillEmail, prefillPass) {
        if (prefillEmail.isNullOrBlank() || prefillPass.isNullOrBlank()) {
            //Don't do anything
        } else {
            Toast.makeText(context, "Email $prefillEmail, pass $prefillPass", Toast.LENGTH_LONG)
                .show()
        }
    }

    RegisterScreenContent(
        onNavigateBackClick,
        onEmailValueChange,
        onPasswordValueChange,
        onRegisterAndAcceptClick,
        modifier
            .navigationBarsPadding()
            .systemBarsPadding(),
    )
}

@Composable
fun RegisterScreenContent(
    onNavigateBackClick: () -> Unit,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onRegisterAndAcceptClick: () -> Unit,
    modifier: Modifier
) {


    // Todo: Add Loading Widget
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
            text = stringResource(id = R.string.register_screen_heading),
            style = AppTheme.typography.headlineSmall24Regular,
            color = AppTheme.primaryTextColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.register_screen_subheading),
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
                value = "", // state.email
                onValueChange = onEmailValueChange,
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
                value = "Password", // state.password
                onValueChange = onPasswordValueChange,
                isError = true, // !state.isValidPassword
                //supportingText = if (!state.isValidPassword) "Invalid password" else null
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        PrivacyTermsText(onPrivacyPolicyClick = {}, onTermsAndConditionsClick = {})
        Spacer(modifier = Modifier.weight(1f))

        var buttonEnabled by remember { mutableStateOf(true) }
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.register_and_accept),
            onClick = onRegisterAndAcceptClick,
            buttonSize = ButtonSize.Large,
            enabled = buttonEnabled // state.isRegisterButtonEnabled
        )
    }
}