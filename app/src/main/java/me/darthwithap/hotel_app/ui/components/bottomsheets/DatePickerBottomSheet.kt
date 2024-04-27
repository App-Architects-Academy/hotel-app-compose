package me.darthwithap.hotel_app.ui.components.bottomsheets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.darthwithap.hotel_app.R
import me.darthwithap.hotel_app.domain.models.HotelDetails
import me.darthwithap.hotel_app.presentation.hoteldetails.BottomCtaPanel
import me.darthwithap.hotel_app.ui.theme.AppTheme
import me.darthwithap.hotel_app.ui.utils.toSimpleDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerBottomSheet(
  details: HotelDetails,
  modifier: Modifier = Modifier,
  bottomSheetState: SheetState,
  cornerRadius: Dp = 12.dp,
  onDismiss: () -> Unit = {},
  onSelectDates: (checkInDate: Long, checkOutDate: Long) -> Unit = { _, _ -> },
) {

  val dateRangeState = rememberDateRangePickerState(
    initialSelectedStartDateMillis = null,
    initialSelectedEndDateMillis = null,
  )

//  Date Range Validator:
//  (Long) -> Boolean
//  return !details.bookedDates.contains(utcTimeMillis.toSimpleDate()) &&
//      utcTimeMillis > System.currentTimeMillis()

  ModalBottomSheet(
    onDismissRequest = { onDismiss() },
    shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius),
    sheetState = bottomSheetState,
    modifier = modifier,
    containerColor = AppTheme.surfaceColor
  ) {

    Column(
      modifier = Modifier
        .padding(start = 16.dp, end = 16.dp)
        .fillMaxWidth(),
    ) {

      DateRangePicker(
        state = dateRangeState,
        modifier = Modifier.weight(1f),
        colors = DatePickerDefaults.colors(
          containerColor = AppTheme.surfaceColor,
          weekdayContentColor = AppTheme.onSurface40Color,
          subheadContentColor = AppTheme.primaryTextColor,
          dayContentColor = AppTheme.primaryTextColor,
          disabledDayContentColor = AppTheme.onSurface20Color,
          todayContentColor = AppTheme.colors.primary,
          selectedDayContentColor = AppTheme.primaryTextColor,
          selectedDayContainerColor = AppTheme.onSurfaceColor,
          todayDateBorderColor = AppTheme.colors.primary
        )
      )

      BottomCtaPanel(
        modifier = Modifier,
        price = "$43.29",
        ctaText = stringResource(R.string.apply_btn),
        onCtaClick = {
          onSelectDates(
            dateRangeState.selectedStartDateMillis!!,
            dateRangeState.selectedEndDateMillis!!
          )
        },
        isEnabled = dateRangeState.selectedStartDateMillis != null &&
            dateRangeState.selectedEndDateMillis != null,
      )
      Spacer(modifier = Modifier.height(48.dp))
    }
  }

}