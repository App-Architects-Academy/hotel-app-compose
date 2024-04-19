package me.darthwithap.hotel_app.presentation.hoteldetails

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.data.api.HotelServiceImpl
import me.darthwithap.hotel_app.data.repositories.HotelRepositoryImpl
import me.darthwithap.hotel_app.domain.models.Hotel
import me.darthwithap.hotel_app.domain.models.HotelDetails
import me.darthwithap.hotel_app.domain.usecases.GetHotelDetailsUseCase
import me.darthwithap.hotel_app.ui.components.ImageCarousel
import me.darthwithap.hotel_app.ui.components.NavAppBar
import me.darthwithap.hotel_app.ui.components.NavAppBarAction
import me.darthwithap.hotel_app.ui.theme.AppColorScheme
import me.darthwithap.hotel_app.ui.theme.AppTheme

object HotelDetailsScreen : Screen {
  private val service = HotelServiceImpl()
  private val repository = HotelRepositoryImpl(service)
  private val useCase = GetHotelDetailsUseCase(repository)
  private val viewModel = HotelDetailsScreenViewModel(useCase)

  @Composable
  override fun Content() {
    val navigator = LocalNavigator.current
    HotelDetailsScreen(
      onNavigateBackClick = {
        navigator?.pop()
      },
      onShareClick = {},
      onMapClick = {},
      onContactHostClick = {},
      viewModel = viewModel
    )
  }
}

@Composable
fun HotelDetailsScreen(
  onNavigateBackClick: () -> Unit,
  onShareClick: (hotelDetails: HotelDetails) -> Unit,
  onMapClick: (location: Hotel.Location) -> Unit,
  onContactHostClick: (primaryContact: Hotel.HotelPrimaryContact) -> Unit,
  viewModel: HotelDetailsScreenViewModel
) {
  val state by viewModel.state.collectAsState()

  HotelDetailsScreenContent(
    state = state,
    onNavigateBackClick = onNavigateBackClick,
    onWishlistedClick = { _, _ -> /*TODO*/ },
    onShareClick = onShareClick,
    onReadMoreClick = { /*TODO*/ },
    onAllFeaturesClick = { /*TODO*/ },
    onMapClick = onMapClick,
    onAllReviewsClick = { /*TODO*/ },
    onCheckAvailabilityClick = { _, _ -> /*TODO*/ },
    onContactHostClick = onContactHostClick
  )
}

@Composable
private fun HotelDetailsScreenContent(
  state: HotelDetailsUiState,
  onNavigateBackClick: () -> Unit,
  onWishlistedClick: (hotelId: String, isBookmarked: Boolean) -> Unit,
  onShareClick: (hotelDetails: HotelDetails) -> Unit,
  onReadMoreClick: () -> Unit,
  onAllFeaturesClick: () -> Unit,
  onMapClick: (location: Hotel.Location) -> Unit,
  onAllReviewsClick: (hotelId: String) -> Unit,
  onCheckAvailabilityClick: (hotelId: String, isAvailable: Boolean) -> Unit,
  onContactHostClick: (primaryContact: Hotel.HotelPrimaryContact) -> Unit,
  modifier: Modifier = Modifier
) {
  when (state) {
    is HotelDetailsUiState.Success -> {
      HotelDetailsContent(
        details = state.hotelDetails,
        isWishlisted = state.isWishlisted,
        onNavigateBackClick = onNavigateBackClick,
        onWishlistedClick = onWishlistedClick,
        onShareClick = onShareClick,
        onReadMoreClick = onReadMoreClick,
        onAllFeaturesClick = onAllFeaturesClick,
        onMapClick = onMapClick,
        onAllReviewsClick = onAllReviewsClick,
        onCheckAvailabilityClick = onCheckAvailabilityClick,
        onContactHostClick = onContactHostClick
      )
    }

    HotelDetailsUiState.Error -> {
      Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        Text(
          text = "Error while loading the hotel details please forgive",
          textAlign = TextAlign.Center,
          style = AppTheme.typography.headlineMedium26Regular.copy(color = AppTheme.errorColor)
        )
      }
    }

    HotelDetailsUiState.Loading -> {
      LoadingContent(Modifier.fillMaxSize())
    }
  }
}

@Composable
fun LoadingContent(
  modifier: Modifier = Modifier
) {

}

@Composable
fun HotelDetailsContent(
  details: HotelDetails,
  isWishlisted: Boolean,
  onNavigateBackClick: () -> Unit,
  onWishlistedClick: (hotelId: String, isWishlisted: Boolean) -> Unit,
  onShareClick: (hotelDetails: HotelDetails) -> Unit,
  onReadMoreClick: () -> Unit,
  onAllFeaturesClick: () -> Unit,
  onMapClick: (location: Hotel.Location) -> Unit,
  onAllReviewsClick: (hotelId: String) -> Unit,
  onCheckAvailabilityClick: (hotelId: String, isAvailable: Boolean) -> Unit,
  onContactHostClick: (primaryContact: Hotel.HotelPrimaryContact) -> Unit,
  modifier: Modifier = Modifier
) {
  val verticalScrollState = rememberScrollState()
  var imageHeight by remember { mutableIntStateOf(0) }
  var topBarHeight by remember { mutableIntStateOf(0) }
  var titleHeight by remember { mutableIntStateOf(0) }

  val isTopBarVisible by remember {
    derivedStateOf {
      verticalScrollState.value > (imageHeight + titleHeight) - topBarHeight
    }
  }

  Box(
    modifier = modifier,
    contentAlignment = Alignment.TopStart
  ) {
    Column(modifier = Modifier.fillMaxSize()) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(verticalScrollState)
          .weight(1f)
      ) {

        ImageCarousel(
          modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { imageHeight = it.height },
          images = details.images
        )

        NameAndDestination(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp),
          name = details.hotel.name,
          // Todo(Calculate distance from property for user)
          subtitle = { "${details.hotel.address.city}, ${details.hotel.address.country} ∙ 2.7km" }
        )

        Ratings(details.hotel.officialRating.toFloat())
      }
    }

    NavAppBar(
      onNavigateClick = onNavigateBackClick,
      modifier = Modifier.onSizeChanged { topBarHeight = it.height },
      visible = isTopBarVisible,
      title = details.hotel.name,
      actions = {
        NavAppBarAction(
          modifier = Modifier
            .size(24.dp)
            .padding(horizontal = 2.dp),
          iconResId = R.drawable.ic_share_2,
          onClick = { onShareClick(details) },
          contentDescription = stringResource(id = R.string.share_button)
        )
        NavAppBarAction(
          modifier = Modifier.size(24.dp),
          iconResId = if (isWishlisted) R.drawable.ic_love_fill else R.drawable.ic_love,
          onClick = { onWishlistedClick(details.hotel.id, isWishlisted) },
          contentDescription = stringResource(id = R.string.wishlist_button)
        )
      }
    )
  }
}

@Composable
fun NameAndDestination(
  name: String,
  subtitle: () -> String,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    Text(
      text = name,
      style = AppTheme.typography.headlineMedium26Regular,
      color = AppTheme.primaryTextColor
    )
    Text(
      text = subtitle(),
      style = AppTheme.typography.title14Regular,
      color = AppTheme.onSurface40Color
    )
  }
}

@Composable
fun Ratings(
  rating: Float,
  modifier: Modifier = Modifier
) {
  Canvas(modifier = modifier) {
    drawContext.canvas.nativeCanvas.apply {
      drawRatingStar(rating = rating, AppColorScheme.primaryColor)
    }
  }
}

fun DrawScope.drawRatingStar(
  rating: Float,
  fillColor: Color
) {

  drawCircle(
    color = fillColor,
    radius = 8.dp.toPx(),
    style = Stroke(width = 1.dp.toPx())
  )

  // Draw the filled portion
  val fillRadius = 8.dp.toPx() * rating
  if (fillRadius > 0) {
    drawCircle(
      color = fillColor,
      radius = fillRadius
    )
  }
}