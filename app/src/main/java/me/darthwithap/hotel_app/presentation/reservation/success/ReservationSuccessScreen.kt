package me.darthwithap.hotel_app.presentation.reservation.success

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.ui.components.ButtonSize
import me.darthwithap.hotel_app.ui.components.SecondaryButton
import me.darthwithap.hotel_app.ui.theme.AppTheme
import java.util.UUID

object ReservationSuccessScreen : Screen {
    @Composable
    override fun Content() {
        ReservationSuccessScreen()
    }
}

@Composable
fun ReservationSuccessScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .systemBarsPadding()
            .background(color = AppTheme.colors.lightCard),
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            ReservationTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            InvoiceCard()
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            FlightRecommendationCard()
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            BackToHomeButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                onClick = {}
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ReservationTitle(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.reservation_success),
            style = AppTheme.typography.headlineMedium26Bold.copy(fontWeight = FontWeight.Normal),
            color = AppTheme.colors.black100,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.padding(horizontal = 52.dp),
            textAlign = TextAlign.Center,
            text = buildAnnotatedString {
                withStyle(
                    style = AppTheme.typography.forms16Regular.toSpanStyle()
                        .copy(color = AppTheme.colors.black40)
                ) {
                    append("Your reservation at ")
                }

                withStyle(
                    style = AppTheme.typography.forms16Bold.toSpanStyle()
                        .copy(color = AppTheme.colors.black70)
                ) {
                    append("Saza Villa at Bali ")
                }

                withStyle(
                    style = AppTheme.typography.forms16Regular.toSpanStyle()
                        .copy(color = AppTheme.colors.black40)
                ) {
                    append("booked at ")
                }

                withStyle(
                    style = AppTheme.typography.forms16Bold.toSpanStyle()
                        .copy(color = AppTheme.colors.black70)
                ) {
                    append("20 June - 27 Aug")
                }
            }
        )
    }
}

@Composable
private fun InvoiceCard(
    modifier: Modifier = Modifier
) {
    Surface(
        color = AppTheme.colors.white70,
        shadowElevation = 1.dp,
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.white100)
                .padding(16.dp)
        ) {
            InvoiceHeader()

            Spacer(modifier = Modifier.height(24.dp))

            InvoiceHotelInfo()

            Spacer(modifier = Modifier.height(16.dp))

            InvoiceDateInfo()

            Spacer(modifier = Modifier.height(16.dp))

            InvoiceGuestInfo()

            Spacer(modifier = Modifier.height(16.dp))

            InvoiceOrderNumberInfo()

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun InvoiceHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.invoice),
            style = AppTheme.typography.forms16Regular,
            color = AppTheme.colors.black100
        )
        Image(
            painter = painterResource(id = R.drawable.ic_mastercard),
            contentDescription = "Payment Card Logo"
        )
    }
}

@Composable
private fun InvoiceHotelInfo() {
    Text(
        text = stringResource(R.string.hotel_info),
        style = AppTheme.typography.title14Regular,
        color = AppTheme.colors.black40
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "Saza Villa ∙ Home 1",
        style = AppTheme.typography.title14Bold.copy(fontWeight = FontWeight.Normal),
        color = AppTheme.colors.black100
    )
}

@Composable
private fun InvoiceDateInfo() {
    Text(
        text = stringResource(R.string.date),
        style = AppTheme.typography.title14Regular,
        color = AppTheme.colors.black40
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "20 June - 27 Aug, 2024",
        style = AppTheme.typography.title14Bold.copy(fontWeight = FontWeight.Normal),
        color = AppTheme.colors.black100
    )
}

@Composable
private fun InvoiceGuestInfo() {
    Text(
        text = stringResource(R.string.guest),
        style = AppTheme.typography.title14Regular,
        color = AppTheme.colors.black40
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "2 Adults",
        style = AppTheme.typography.title14Bold.copy(fontWeight = FontWeight.Normal),
        color = AppTheme.colors.black100
    )
}

@Composable
private fun InvoiceOrderNumberInfo() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Order No. ${Math.abs(UUID.randomUUID().hashCode())}",
            style = AppTheme.typography.title14Regular,
            color = AppTheme.colors.black40
        )
        Image(
            painter = painterResource(id = R.drawable.ic_barcode),
            contentDescription = "Payment Card Logo"
        )

    }
}

@Composable
private fun FlightRecommendationCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = AppTheme.colors.black100)
            .padding(16.dp)
    ) {
        FlightRecommendationTitle()

        Spacer(modifier = Modifier.height(16.dp))

        FlightRecommendationBookingCard()

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun FlightRecommendationBookingCard() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FlightBookingContainer(
            modifier = Modifier
                .weight(1f)
                .height(100.dp),
            drawableId = R.drawable.ic_today,
            contentDescription = stringResource(R.string.book_tickets_for_date, R.string.today),
            contentText = stringResource(R.string.today)
        )

        FlightBookingContainer(
            modifier = Modifier
                .weight(1f)
                .height(100.dp),
            drawableId = R.drawable.ic_tomorrow,
            contentDescription = stringResource(R.string.book_tickets_for_date, R.string.tomorrow),
            contentText = stringResource(R.string.tomorrow)
        )

        FlightBookingContainer(
            modifier = Modifier
                .weight(1f)
                .height(100.dp),
            drawableId = R.drawable.ic_custom,
            contentDescription = stringResource(R.string.book_tickets_for_date, R.string.custom),
            contentText = stringResource(R.string.custom)
        )
    }
}

@Composable
private fun FlightRecommendationTitle() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = stringResource(R.string.haven_t_booked_flights),
                style = AppTheme.typography.subtitle18Regular,
                color = AppTheme.colors.white100
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(
                    R.string.our_recommendation_flights_tickets_to_city,
                    "Bali"
                ),
                style = AppTheme.typography.title14Regular,
                color = AppTheme.colors.white40
            )
        }

        Image(
            painter = painterResource(id = R.drawable.round_white_arrow_forward_24),
            contentDescription = stringResource(R.string.arrow_forward),
        )
    }
}

@Composable
private fun FlightBookingContainer(
    modifier: Modifier = Modifier,
    @DrawableRes drawableId: Int,
    contentDescription: String?,
    contentText: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentScale = ContentScale.Fit,
            contentDescription = contentDescription
        )
        Text(
            text = contentText,
            style = AppTheme.typography.title14Bold,
            color = AppTheme.colors.white100
        )
    }
}

@Composable
private fun BackToHomeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    SecondaryButton(
        modifier = modifier,
        text = stringResource(R.string.back_to_home),
        onClick = onClick,
        buttonSize = ButtonSize.Large,
        enabled = true
    )
}
