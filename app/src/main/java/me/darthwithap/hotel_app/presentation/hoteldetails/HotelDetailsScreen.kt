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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.data.api.HotelServiceImpl
import me.darthwithap.hotel_app.data.repositories.HotelRepositoryImpl
import me.darthwithap.hotel_app.data.roundTo
import me.darthwithap.hotel_app.domain.models.Amenity
import me.darthwithap.hotel_app.domain.models.Hotel
import me.darthwithap.hotel_app.domain.models.HotelDetails
import me.darthwithap.hotel_app.domain.models.Review
import me.darthwithap.hotel_app.domain.models.Room
import me.darthwithap.hotel_app.domain.models.TopSpot
import me.darthwithap.hotel_app.domain.usecases.GetHotelDetailsUseCase
import me.darthwithap.hotel_app.ui.components.ButtonSize
import me.darthwithap.hotel_app.ui.components.HorizontalDivider
import me.darthwithap.hotel_app.ui.components.ImageCarousel
import me.darthwithap.hotel_app.ui.components.NavAppBar
import me.darthwithap.hotel_app.ui.components.NavAppBarAction
import me.darthwithap.hotel_app.ui.components.OutlineButton
import me.darthwithap.hotel_app.ui.components.PrimaryButton
import me.darthwithap.hotel_app.ui.components.cards.IconPosition
import me.darthwithap.hotel_app.ui.components.cards.ImageCard
import me.darthwithap.hotel_app.ui.components.cards.TitleCard
import me.darthwithap.hotel_app.ui.theme.AppTheme
import me.darthwithap.hotel_app.ui.theme.BeVietnamProFontFamily
import me.darthwithap.hotel_app.ui.utils.loadCircleImage
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
  onMapClick: (LatLng) -> Unit,
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
  onMapClick: (latLng: LatLng) -> Unit,
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
  onMapClick: (latLng: LatLng) -> Unit,
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
      // Todo: Business logic needs to move to domain layer
      val amenities = details.rooms.map { it.roomType.amenities }.flatten().toSet().toList()
      val features = details.features.minus(amenities.toSet())
      item {
        ImageCarousel(
          modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged { imageHeight = it.height },
          images = details.images
        )
      }
      item {
        Column(modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
          NameAndDestination(
            modifier = Modifier
              .fillMaxWidth(),
            name = details.hotel.name,
            // Todo(Calculate distance from property for user)
            subtitle = { "${details.hotel.address.city}, ${details.hotel.address.country} ∙ 2.7km" },
            onTitleSizeChanged = {
              titleHeight = it
            }
          )
          RatingsSections(
            modifier = Modifier.padding(vertical = 16.dp),
            rating = details.hotel.officialRating.toFloat(),
            numOfReviews = details.hotel.numberOfReviews
          )
          HorizontalDivider()
          AboutSection(
            about = details.description,
            onReadMoreClick = onReadMoreClick,
            modifier = Modifier.padding(vertical = 16.dp)
          )
          HorizontalDivider()
        }
      }

      featuresSection(features)

      item {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
          HorizontalDivider()
          RoomsSection(
            modifier = Modifier.padding(vertical = 16.dp),
            rooms = details.rooms
          )
          HorizontalDivider()
          SectionTitle(
            modifier = Modifier.padding(vertical = 16.dp),
            title = stringResource(id = R.string.what_this_place_offers)
          )
        }
      }

      amenitiesSection(amenities.take(amenities.size / 2))

      item {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
          OutlineButton(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 16.dp),
            text = stringResource(R.string.show_all_features),
            onClick = onAllFeaturesClick,
            buttonSize = ButtonSize.Medium
          )
          HorizontalDivider()

          val latLng = LatLng(
            details.hotel.location.latitude,
            details.hotel.location.longitude
          )
          LocationSection(
            modifier = Modifier.padding(vertical = 16.dp),
            hotelName = details.hotel.name,
            latLng = latLng,
            fullAddress = details.hotel.address,
            onStartDirectionsClick = onMapClick
          )
          HorizontalDivider()

          TopSpotsSection(
            modifier = Modifier.padding(vertical = 16.dp),
            topSpots = details.topSpots
          )
          HorizontalDivider()

          HostSection(
            modifier = Modifier.padding(vertical = 16.dp),
            primaryContact = details.hotel.primaryContact,
            onContactHostClick = onContactHostClick
          )
          HorizontalDivider()

          ReviewsSection(
            modifier = Modifier.padding(vertical = 16.dp),
            rating = details.hotel.officialRating.toFloat(),
            reviews = details.reviews,
            onShowAllReviewsClick = onAllReviewsClick
          )
        }
      }
      item {
        Spacer(modifier = Modifier.height(56.dp))
      }
    }

    AnimatedVisibility(
      visible = true,
      enter = fadeIn() + slideInVertically(),
      exit = fadeOut() + slideOutVertically()
    ) {
      NavAppBar(
        onNavigateClick = onNavigateBackClick,
        modifier = Modifier.onSizeChanged { topBarHeight = it.height },
        visible = showAppBar,
        title = details.hotel.name,
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
fun ReviewsSection(
  rating: Float,
  reviews: List<Review>,
  onShowAllReviewsClick: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    ReviewRatingTitle(
      modifier = Modifier.padding(bottom = 12.dp),
      rating = rating,
      numOfReviews = reviews.size,
      isPrimary = true
    )
    reviews.firstOrNull()?.let {
      ReviewTitleCard(
        title = it.author.name,
        subtitle = { " Review on ${it.created}" },
        description = it.text
      )
    }
    Spacer(modifier = Modifier.height(12.dp))
    OutlineButton(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp),
      text = stringResource(R.string.show_all_reviews),
      onClick = { onShowAllReviewsClick("maybe have to pass list of reviews here") },
      buttonSize = ButtonSize.Medium
    )
  }
}

@Composable
fun ReviewRatingTitle(
  rating: Float, numOfReviews: Int,
  modifier: Modifier = Modifier,
  isPrimary: Boolean = false
) {
  Text(
    modifier = modifier,
    text = "$rating ∙ ($numOfReviews Reviews)",
    style = AppTheme.typography.subtitle18Regular,
    color = if (isPrimary) AppTheme.colors.primary else AppTheme.primaryTextColor
  )
}

@Composable
fun HostSection(
  primaryContact: Hotel.HotelPrimaryContact,
  onContactHostClick: (primaryContact: Hotel.HotelPrimaryContact) -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier = modifier) {
    SectionTitle(
      modifier = Modifier.padding(bottom = 12.dp),
      title = stringResource(id = R.string.meet_your_guide)
    )
    ReviewTitleCard(
      title = primaryContact.user.fullName,
      subtitle = { "Guide since ${primaryContact.hostSinceDate}" },
      description = primaryContact.details,
      isVerified = primaryContact.isVerified,
      imageUrl = primaryContact.user.photoUrl,
      rating = primaryContact.rating.toFloat(),
      numOfReviews = primaryContact.numberOfReviews
    )
    OutlineButton(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 12.dp),
      text = stringResource(R.string.contact_your_guide),
      onClick = { onContactHostClick(primaryContact) },
      buttonSize = ButtonSize.Medium
    )
  }
}

@Composable
fun TopSpotsSection(
  topSpots: List<TopSpot>,
  modifier: Modifier = Modifier,
  onTopSpotClick: (TopSpot) -> Unit = {}
) {
  Column(modifier = modifier) {
    TitleCard(
      title = stringResource(id = R.string.top_spots_heading),
      subtitle = stringResource(id = R.string.top_spots_subtitle),
      icon = R.drawable.ic_chevron_right,
      iconPosition = IconPosition.Right
    )
    LazyRow {
      items(topSpots) { spot ->
        val paddingEnd = if (topSpots.last() == spot) 0.dp else 12.dp
        val annotatedText = buildAnnotatedString {
          withStyle(
            style = SpanStyle(
              color = AppTheme.primaryTextColor,
              fontSize = 12.sp,
              fontWeight = FontWeight.Normal,
              fontFamily = BeVietnamProFontFamily
            )
          ) {
            append("\$${spot.pricePerPerson}")
          }
          withStyle(
            style = SpanStyle(
              color = AppTheme.onSurface40Color,
              fontSize = 12.sp,
              fontWeight = FontWeight.Normal,
              fontFamily = BeVietnamProFontFamily
            )
          ) {
            append("/person")
          }
        }

        ImageCard(
          modifier = Modifier.padding(end = paddingEnd),
          imageUrl = spot.thumbnailImage,
          onClick = { onTopSpotClick(spot) },
          title = spot.title,
          // Todo: Calculate distance of the top spot from the hotel's distance
          line2 = { "${spot.placeName} ∙ ${Random.nextDouble().roundTo(1)}km" },
          line3 = {
            if (spot.pricePerPerson > 0.0) {
              annotatedText.text
            } else ""
          },
          selectable = true
        )
      }
    }
  }
}

@Composable
fun LocationSection(
  hotelName: String,
  latLng: LatLng,
  fullAddress: Hotel.Address,
  onStartDirectionsClick: (LatLng) -> Unit = {},
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    SectionTitle(
      modifier = Modifier.padding(),
      title = stringResource(id = R.string.location_heading)
    )
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp)
        .height(150.dp)
        .clip(AppTheme.shapes.small)
    ) {
      val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 10f)
      }
      GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPosition
      ) {
        Marker(
          state = MarkerState(position = latLng),
          title = hotelName,
          snippet = "Hotel Location"
        )
      }
    }
    Text(
      text = fullAddress.fullAddress,
      style = AppTheme.typography.forms16Regular,
      color = AppTheme.primaryTextColor
    )
    OutlineButton(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      text = stringResource(R.string.start_directions),
      onClick = { onStartDirectionsClick(latLng) },
      buttonSize = ButtonSize.Medium
    )
  }
}

@Composable
fun RoomsSection(
  rooms: List<Room>,
  modifier: Modifier = Modifier,
  onRoomClick: (Room) -> Unit = {}
) {
  Column(modifier = modifier) {
    SectionTitle(
      modifier = Modifier.padding(bottom = 16.dp),
      title = stringResource(id = R.string.where_youll_live)
    )
    LazyRow {
      items(rooms) { room ->
        val paddingEnd = if (rooms.last() == room) 0.dp else 12.dp
        ImageCard(
          modifier = Modifier.padding(end = paddingEnd),
          imageUrl = room.thumbnailImage,
          onClick = { onRoomClick(room) },
          title = room.roomType.typeName,
          line2 = room.roomType.amenities.firstOrNull()?.let {
            { "${it.amenity} ∙ ${it.description}" }
          },
          line3 = room.roomType.amenities.lastOrNull()?.let {
            { "${it.amenity} ∙ ${it.description}" }
          },
          selectable = true
        )
      }
    }
  }
}


@Composable
fun ReviewTitleCard(
  title: String,
  subtitle: () -> String,
  description: String?,
  modifier: Modifier = Modifier,
  rating: Float? = null,
  numOfReviews: Int? = null,
  imageUrl: String? = null,
  isVerified: Boolean = false,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 12.dp),
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .fillMaxWidth()
        .background(AppTheme.surfaceColor)
    ) {
      Column(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.subtitle18Regular,
            color = AppTheme.primaryTextColor,
          )
          if (isVerified) {
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
              painter = painterResource(id = R.drawable.ic_verified),
              contentDescription = null,
              modifier = Modifier.size(18.dp),
              tint = AppTheme.colors.primary
            )
          }
        }
        if (subtitle().isNotEmpty()) {
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            // Todo: Format guide since date
            text = subtitle(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = AppTheme.typography.caption12Regular,
            color = AppTheme.onSurface40Color
          )
        }
      }
      imageUrl?.let {
        Spacer(Modifier.width(16.dp))
        it.loadCircleImage(
          modifier = Modifier
            .size(38.dp)
        )
      }
    }
    rating?.let {
      RatingsSections(
        modifier = Modifier.padding(top = 16.dp, bottom = 10.dp),
        rating = it, numOfReviews = numOfReviews
      )
    }
    Spacer(modifier = Modifier.height(14.dp))
    description?.let {
      Text(
        text = it,
        style = AppTheme.typography.title14Regular,
        color = if (AppTheme.isDark) AppTheme.colors.white70 else AppTheme.colors.black70
      )
    }
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
    modifier = modifier.onSizeChanged { onTitleSizeChanged(it.height) },
    verticalArrangement = Arrangement.spacedBy(8.dp)
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
fun RatingsSections(rating: Float, numOfReviews: Int?, modifier: Modifier = Modifier) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    RatingStars(rating, modifier = Modifier.padding(end = 8.dp))
    numOfReviews?.let {
      Text(
        text = "$rating ($numOfReviews Reviews)",
        style = AppTheme.typography.caption12Regular.copy(color = AppTheme.onSurface40Color)
      )
    }
  }
}

@Composable
fun RatingStars(rating: Float, modifier: Modifier = Modifier) {
  Row(
    modifier = modifier,
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
    SectionTitle(title = stringResource(id = R.string.about_this_place))
    Text(
      modifier = Modifier.padding(top = 10.dp, bottom = 8.dp),
      text = about,
      style = AppTheme.typography.forms16Regular,
      color = AppTheme.onSurface40Color
    )
    OutlineButton(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp),
      text = stringResource(R.string.read_more),
      onClick = onReadMoreClick,
      buttonSize = ButtonSize.Medium
    )
  }
}


fun LazyListScope.featuresSection(
  features: List<Amenity>,
) {
  items(features) {
    TitleCard(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
      title = it.amenity,
      subtitle = it.description,
      icon = it.iconId.resId
    )
  }
  item { Spacer(modifier = Modifier.height(8.dp)) }
}

fun LazyListScope.amenitiesSection(
  amenities: List<Amenity>,
) {
  items(amenities) {
    TitleCard(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
      title = it.amenity,
      subtitle = it.description,
      icon = it.iconId.resId
    )
  }
  item { Spacer(modifier = Modifier.height(8.dp)) }
}

@Composable
private fun BottomCtaPanel(
  price: String,
  ctaText: String,
  onCtaClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(AppTheme.surfaceColor)
  ) {
    val backgroundColor = if (AppTheme.isDark) AppTheme.colors.dark1 else AppTheme.colors.light
    Surface(
      modifier = modifier
        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp)
        .shadow(4.dp, AppTheme.shapes.medium),
      color = backgroundColor
    ) {
      Box(
        modifier = Modifier
          .background(backgroundColor),
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
}

@Composable
fun SectionTitle(
  title: String,
  modifier: Modifier = Modifier.fillMaxWidth()
) {
  Text(
    modifier = modifier,
    text = title,
    style = AppTheme.typography.subtitle18Regular,
    color = AppTheme.primaryTextColor,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis
  )
}