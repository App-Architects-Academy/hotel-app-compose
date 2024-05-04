package me.darthwithap.hotel_app.presentation.reservation.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/*** A custom Shape that creates a ticket-shaped outline.
 *
 * @param circleRadius The radius of the circle corners.
 * @param cornerSize The size of the rounded corners.
 * @param heightFactor The height of the ticket cut-out as a fraction of the total height in range 0.0 to 1.0.
 * @throws IllegalArgumentException if heightFactor is not in the range 0.0 to 1.0.
 */
class TicketShape(
    private val circleRadius: Dp,
    private val cornerSize: CornerSize,
    private val heightFactor: Float
) : Shape {

    init {
        require(heightFactor in 0.0f..1.0f) { "heightFactor must be between 0.0 and 1.0" }
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(path = getPath(size, density))
    }

    /*** Creates a Path object representing the shape of the ticket by intersecting a rounded rectangle anda ticket path.
     *
     * @param size The size of the ticket.
     * @param density The density of the display.
     * @return A Path object representing the ticket shape.
     */
    private fun getPath(size: Size, density: Density): Path {
        val roundedRect = RoundRect(size.toRect(), CornerRadius(cornerSize.toPx(size, density)))
        val roundedRectPath = Path().apply { addRoundRect(roundedRect) }
        return Path.combine(
            operation = PathOperation.Intersect,
            path1 = roundedRectPath,
            path2 = getTicketPath(size, density)
        )
    }

    /**
     * Creates a Path object representing the shape of a ticket.
     *
     * @param size The size of the ticket.
     * @param density The density of the display.
     * @return A Path object representing the ticket shape.
     */
    private fun getTicketPath(size: Size, density: Density): Path {
        // Calculate the radius of the circle corners in pixels.
        val circleRadiusInPx = with(density) { circleRadius.toPx() }
        // Calculate the ticket cutout height.
        val height = size.height * heightFactor

        // Create a new Path object.
        return Path().apply {

            // Reset the path.
            reset()

            // Move to the top-left corner of the ticket.
            moveTo(x = 0F, 0F)

            // Draw a line to the top-right corner of the ticket.
            lineTo(x = size.width, y = 0F)

            // Draw a line till the ticket cutout from the top-right corner.
            lineTo(x = size.width, y = height)

            // Draw an arc for the right hand side cutout.
            arcTo(
                rect = Rect(
                    left = (size.width).minus(circleRadiusInPx / 2),
                    top = height,
                    right = size.width.plus(circleRadiusInPx / 2),
                    bottom = ((height)).plus(circleRadiusInPx)
                ),
                startAngleDegrees = 270F,
                sweepAngleDegrees = -180F,
                forceMoveTo = false
            )

            // Draw a line to the bottom-right corner of the ticket.
            lineTo(x = size.width, y = size.height)

            // Draw a line to the bottom-left corner of the ticket.
            lineTo(x = 0F, y = size.height)

            // Draw a line till the ticket cutout from the bottom-left corner.
            lineTo(x = 0F, y = ((height)).plus(circleRadiusInPx))

            // Draw an arc for the left hand side cutout.
            arcTo(
                rect = Rect(
                    left = (circleRadiusInPx / 2) * -1,
                    top = (height),
                    right = (circleRadiusInPx / 2),
                    bottom = ((height)).plus(circleRadiusInPx)
                ),
                startAngleDegrees = 90F,
                sweepAngleDegrees = -180F,
                forceMoveTo = false
            )
        }
    }
}


@Preview
@Composable
fun TicketShapePreview() {
    Box(
        modifier = Modifier
            .height(250.dp)
            .width(250.dp)
            .background(
                color = Color.Cyan,
                shape = TicketShape(
                    circleRadius = 40.dp,
                    cornerSize = CornerSize(10.dp),
                    heightFactor = 0.75F
                )
            )
    )
}

