package com.example.sensorsplayground.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun SingleValueMeter(
    value: Float = 30f,
    width: Dp = 50.dp,
    color: Color = Color.Blue,
    max: Float = 100f,
    mSize: Dp = 250.dp
) {
    val percent = abs(value) / max * 275f
    Box (
        modifier = Modifier.size(mSize),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(mSize)
        ) {
            drawIndicator(
                width = width
            )
            drawIndicator(
                width = width,
                color = if (value < 0) Color.Red else color,
                sweepAngle = percent
            )
        }
        Text(value.toString())
    }
}

private fun DrawScope.drawIndicator(
    color: Color = Color.Gray,
    sweepAngle: Float = 275f,
    width: Dp = 100.dp
) {
    val fWidth = width.toPx()
    drawArc(
        color = color,
        startAngle = 135f,
        sweepAngle = sweepAngle,
        topLeft = Offset(fWidth / 2, fWidth / 2),
        useCenter = false,
        size = Size(size.width - fWidth, size.height - fWidth),
        style = Stroke (
            width = fWidth,
            cap = StrokeCap.Round
        )
    )
}

@Composable
@Preview(showBackground = true)
fun SingleValueMeterPreview() {
    MaterialTheme {
        SingleValueMeter(value = 158f, max = 1000f)
    }
}