package me.darthwithap.hotel_app.ui.components.bottomsheets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.domain.models.Amenity
import me.darthwithap.hotel_app.presentation.hoteldetails.amenitiesSection
import me.darthwithap.hotel_app.ui.components.HorizontalDivider
import me.darthwithap.hotel_app.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturesBottomSheet(
  features: List<Amenity>,
  onDismiss: () -> Unit,
  modifier: Modifier = Modifier,
  bottomSheetState: SheetState,
  cornerRadius: Dp = 12.dp
) {

  ModalBottomSheet(
    onDismissRequest = { onDismiss() },
    shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius),
    sheetState = bottomSheetState,
    modifier = modifier,
    containerColor = AppTheme.surfaceColor
  ) {

    Column(
      horizontalAlignment = Alignment.Start,
      modifier = Modifier
        .fillMaxSize(),
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_close_x),
        contentDescription = null,
        modifier = Modifier
          .padding(start = 16.dp, end = 16.dp)
          .size(24.dp)
          .clickable { onDismiss() },
        tint = AppTheme.onSurfaceColor,
      )
      Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
        text = "What this place offers",
        style = AppTheme.typography.headlineSmall24Regular.copy(fontWeight = FontWeight.Medium),
        color = AppTheme.primaryTextColor
      )
      HorizontalDivider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
      LazyColumn(
        modifier = Modifier.padding(vertical = 16.dp)
      ) {
        amenitiesSection(amenities = features)
      }
    }
  }

}