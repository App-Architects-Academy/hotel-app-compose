package me.darthwithap.hotel_app.presentation.hoteldetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
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
import me.darthwithap.hotel_app.ui.components.ButtonSize
import me.darthwithap.hotel_app.ui.components.ImageCarousel
import me.darthwithap.hotel_app.ui.components.NavAppBar
import me.darthwithap.hotel_app.ui.components.NavAppBarAction
import me.darthwithap.hotel_app.ui.components.OutlineButton
import me.darthwithap.hotel_app.ui.components.PrimaryButton
import me.darthwithap.hotel_app.ui.theme.AppTheme
import kotlin.math.roundToInt
import kotlin.random.Random

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
  modifier: Modifier = Modifier.background(AppTheme.surfaceColor)
) {
  when (state) {
    is HotelDetailsUiState.Success -> {
      HotelDetailsContent(
        modifier = modifier.fillMaxSize(),
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
        modifier = modifier.fillMaxSize(),
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
  var imageHeight by remember { mutableIntStateOf(0) }
  var topBarHeight by remember { mutableIntStateOf(0) }
  var titleHeight by remember { mutableIntStateOf(0) }
  val scrollState = rememberLazyListState()

  val showAppBar by remember {
    derivedStateOf {
      scrollState.firstVisibleItemIndex > 0 ||
          scrollState.firstVisibleItemScrollOffset >= imageHeight + titleHeight
    }
  }

  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.TopStart
  ) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 16.dp),
      state = scrollState
    ) {
      item {
        ImageCarousel(
          modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { imageHeight = it.height },
          images = details.images
        )
      }
      item {
        Column(modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
          NameAndDestination(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 16.dp),
            name = details.hotel.name,
            // Todo(Calculate distance from property for user)
            subtitle = { "${details.hotel.address.city}, ${details.hotel.address.country} ∙ 2.7km" },
            onTitleSizeChanged = {
              titleHeight = it
            }
          )
          Spacer(modifier = Modifier.height(16.dp))
          Ratings(details.hotel.officialRating.toFloat(), details.hotel.numberOfReviews)

          Divider(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 24.dp),
            color = AppTheme.onSurface05Color
          )

          AboutSection(
            about = details.description,
            onReadMoreClick = onReadMoreClick,
            modifier = Modifier.padding(bottom = 24.dp)
          )

          Divider(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 24.dp),
            color = AppTheme.onSurface05Color
          )
        }
      }
      featuresSection(details.features)
    }

    AnimatedVisibility(
      //visible = showAppBar,
      visible = true,
      enter = fadeIn() + slideInVertically(),
      exit = fadeOut() + slideOutVertically()
    ) {
      NavAppBar(
        onNavigateClick = onNavigateBackClick,
        modifier = Modifier.onSizeChanged { topBarHeight = it.height },
        visible = showAppBar,
        title = "Saza Villa",//details.hotel.name,
        actions = {
          NavAppBarAction(
            modifier = Modifier
              .size(24.dp)
              .padding(horizontal = 16.dp),
            iconResId = R.drawable.ic_share_2,
            onClick = { onShareClick(details) },
            contentDescription = stringResource(id = R.string.share_button),
            tint = Color.Red// if (!isTopBarVisible && !AppTheme.isDark) AppTheme.surfaceColor else AppTheme.onSurfaceColor
          )
          NavAppBarAction(
            modifier = Modifier.size(24.dp),
            iconResId = if (isWishlisted) R.drawable.ic_love_fill else R.drawable.ic_love,
            onClick = { onWishlistedClick(details.hotel.id, isWishlisted) },
            contentDescription = stringResource(id = R.string.wishlist_button),
            tint = if (!showAppBar && !AppTheme.isDark) AppTheme.surfaceColor else AppTheme.onSurfaceColor
          )
        }
      )
    }

    BottomCtaPanel(
      modifier = Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
        .align(Alignment.BottomCenter),
      price = "$${(details.rooms.minOf { it.basePrice }).roundToInt()}",
      ctaText = stringResource(R.string.check_availability),
      onCtaClick = {
        onCheckAvailabilityClick(
          details.hotel.id,
          // Todo(Logic of checking availability)
          Random.nextBoolean()
        )
      }
    )
  }
}

@Composable
fun NameAndDestination(
  name: String,
  subtitle: () -> String,
  onTitleSizeChanged: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(2.dp)
  ) {
    Text(
      modifier = Modifier.onSizeChanged { onTitleSizeChanged(it.height) },
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
fun Ratings(rating: Float, numOfReviews: Int) {
  Row(
    verticalAlignment = Alignment.CenterVertically
  ) {
    for (i in 1..5) {
      val starIcon = when {
        rating >= i -> R.drawable.ic_filled_star
        rating > i - 1 -> R.drawable.ic_half_filled_star
        else -> R.drawable.ic_empty_star
      }
      Icon(
        painter = painterResource(id = starIcon),
        contentDescription = "Rating Star",
        modifier = Modifier.size(16.dp),
        tint = if (starIcon == R.drawable.ic_empty_star) Color.LightGray else AppTheme.colors.primary
      )
      if (i < 5) Spacer(modifier = Modifier.width(4.dp)) // space between stars
    }

    Spacer(modifier = Modifier.width(8.dp))

    Text(
      text = "$rating ($numOfReviews Reviews)",
      style = AppTheme.typography.caption12Regular.copy(color = AppTheme.onSurface40Color)
    )
  }
}

@Composable
fun AboutSection(
  about: String,
  onReadMoreClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
  ) {
    Text(
      text = stringResource(id = R.string.about_this_place),
      style = AppTheme.typography.subtitle18Regular,
      color = AppTheme.primaryTextColor
    )
    Spacer(modifier = Modifier.height(10.dp))
    Text(
      text = about,
      style = AppTheme.typography.forms16Regular,
      color = AppTheme.onSurface40Color
    )
    Spacer(modifier = Modifier.height(10.dp))
    OutlineButton(
      modifier = Modifier.fillMaxWidth(),
      text = stringResource(R.string.read_more),
      onClick = onReadMoreClick,
      buttonSize = ButtonSize.Medium
    )
  }
}


fun LazyListScope.featuresSection(
  features: List<HotelDetails.HotelFeature>,
) {
  items(features) {
    FeatureItem(feature = it)
  }
}


@Composable
fun FeatureItem(feature: HotelDetails.HotelFeature, modifier: Modifier = Modifier) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      painter = painterResource(id = feature.iconId.resId),
      contentDescription = feature.feature,
      modifier = Modifier
        .size(24.dp)
        .padding(end = 16.dp)
    )
    Column {
      Text(
        text = feature.feature,
        style = AppTheme.typography.forms16Regular,
        color = AppTheme.primaryTextColor,
        modifier = Modifier.padding(bottom = 4.dp)
      )
      Text(
        text = feature.description,
        style = AppTheme.typography.caption12Regular,
        color = AppTheme.onSurface40Color
      )
    }
  }
}

@Composable
private fun BottomCtaPanel(
  price: String,
  ctaText: String,
  onCtaClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .shadow(4.dp, AppTheme.shapes.medium)
      .clip(AppTheme.shapes.medium),
    color = AppTheme.surfaceColor,
  ) {
    Box(
      modifier = Modifier.background(if (AppTheme.isDark) AppTheme.colors.greyscale900 else AppTheme.colors.light),
      contentAlignment = Alignment.Center
    ) {
      Row(
        modifier = Modifier
          .padding(horizontal = 16.dp, vertical = 10.dp)
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(modifier = Modifier.padding(end = 8.dp)) {
          Text(
            text = price,
            style = AppTheme.typography.subtitle18Bold,
            color = AppTheme.primaryTextColor
          )
          Text(
            modifier = Modifier.padding(start = 2.dp),
            text = stringResource(id = R.string.per_night),
            style = AppTheme.typography.forms16Regular
              .copy(color = AppTheme.onSurface40Color)
          )
        }
        PrimaryButton(
          text = ctaText,
          onClick = onCtaClick,
          buttonSize = ButtonSize.Medium
        )
      }
    }
  }
}