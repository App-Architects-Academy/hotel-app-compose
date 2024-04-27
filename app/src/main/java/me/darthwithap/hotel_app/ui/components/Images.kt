package me.darthwithap.hotel_app.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.darthwithap.hotel_app.ui.utils.loadRoundedBottomImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(
  images: List<String>,
  modifier: Modifier = Modifier
) {
  val scope = rememberCoroutineScope()
  val pagerState = rememberPagerState(pageCount = { images.size })

  Box(modifier = modifier.height(300.dp)) {
    HorizontalPager(
      modifier = Modifier.fillMaxSize(),
      state = pagerState
    ) { page ->
      images[page].loadRoundedBottomImage()
    }

    SliderIndicator(modifier = Modifier
      .align(Alignment.BottomCenter)
      .padding(bottom = 16.dp),
      slideCount = images.size,
      currentIndex = pagerState.currentPage,
      onAnimationFinish = {
        val nextPage = if (pagerState.currentPage < images.size - 1) {
          pagerState.currentPage + 1
        } else 0

        scope.launch {
          pagerState.animateScrollToPage(nextPage)
        }
      }
    )
  }
}

@Composable
fun SliderIndicator(
  slideCount: Int,
  currentIndex: Int,
  durationPerSlide: Int = 5000,
  onAnimationFinish: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .padding(16.dp)
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    (0 until slideCount).forEach { index ->
      IndicatorLine(
        active = index == currentIndex,
        filled = index < currentIndex,
        durationPerSlide = durationPerSlide,
        onAnimationFinish = onAnimationFinish,
        modifier = Modifier
          .height(4.dp)
          .weight(1f)
      )
      if (index < slideCount - 1) {
        Spacer(modifier = Modifier.width(8.dp))
      }
    }
  }
}


@Composable
fun IndicatorLine(
  active: Boolean,
  filled: Boolean,
  durationPerSlide: Int,
  onAnimationFinish: () -> Unit,
  modifier: Modifier = Modifier
) {

  val currentProgress = remember { Animatable(if (filled) 1f else 0f) }
  var animationJob = remember { mutableStateOf<Job?>(null) }

  LaunchedEffect(active, filled) {
    animationJob.value?.cancel()
    animationJob.value = launch {
      if (active) {
        currentProgress.snapTo(0f)
        currentProgress.animateTo(
          targetValue = 1f,
          animationSpec = tween(durationMillis = durationPerSlide, easing = LinearOutSlowInEasing)
        )
        onAnimationFinish()
      } else {
        currentProgress.snapTo(if (filled) 1f else 0f)
      }
    }
  }

  DisposableEffect(Unit) {
    onDispose {
      animationJob.value?.cancel()
    }
  }

  Canvas(
    modifier = modifier
      .height(2.dp)
      .clip(RoundedCornerShape(50f))
  ) {
    val height = size.height
    val width = size.width
    drawRoundRect(
      color = Color.Gray, // Can't call AppTheme.colors here as it is a composable
      size = Size(width, height),
      cornerRadius = CornerRadius(x = height / 2, y = height / 2)
    )

    drawRoundRect(
      color = Color.White,
      topLeft = Offset.Zero,
      size = Size(width = width * currentProgress.value, height = height),
      cornerRadius = CornerRadius(x = height / 2, y = height / 2)
    )
  }
}