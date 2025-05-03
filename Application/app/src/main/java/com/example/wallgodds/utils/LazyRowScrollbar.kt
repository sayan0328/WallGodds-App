package com.example.wallgodds.utils

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.wallgodds.ui.theme.AppPadding

@Composable
fun LazyRowScrollbar(
    listState: LazyListState,
    indicatorColor: Color,
    indicatorHeight: Dp,
    indicatorWidth: Dp,
    trackColor: Color,
    trackHeight: Dp,
    trackWidth: Dp,
    cornerRadius: Dp,
    verticalPadding: Dp
) {
    val density = LocalDensity.current
    val horizontalPaddingPx = with(density) { AppPadding.MainContentPadding.toPx() }
    val indicatorWidthPx = with(density) { indicatorWidth.toPx() }
    val itemSpacingPx = with(density) { AppPadding.PaddingBetweenCategories.toPx() }
    var trackWidthPx by remember { mutableFloatStateOf(0f) }

    val rawScrollProgress by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            val totalItems = layoutInfo.totalItemsCount

            if (visibleItems.isEmpty() || totalItems == 0) return@derivedStateOf 0f

            val itemWidthPx = visibleItems.first().size
            val firstIndex = listState.firstVisibleItemIndex
            val firstOffset = listState.firstVisibleItemScrollOffset

            val totalContentWidth = itemWidthPx * totalItems +
                    itemSpacingPx * (totalItems - 1) +
                    horizontalPaddingPx * 2

            val viewportWidth = layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset
            val scrollableWidth = totalContentWidth - viewportWidth
            val scrolled = (itemWidthPx + itemSpacingPx) * firstIndex + firstOffset

            (scrolled / scrollableWidth).coerceIn(0f, 1f)
        }
    }

    val animatedProgress by animateFloatAsState(
        targetValue = rawScrollProgress,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "ScrollBarBounce"
    )

    Spacer(modifier = Modifier.height(verticalPadding))

    Box(
        modifier = Modifier
            .height(indicatorHeight)
            .width(trackWidth)
            .onGloballyPositioned {
                trackWidthPx = it.size.width.toFloat()
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(trackHeight)
                .background(trackColor, shape = RoundedCornerShape(cornerRadius))
        )
        Box(
            modifier = Modifier
                .offset {
                    val maxOffsetPx = (trackWidthPx - indicatorWidthPx).coerceAtLeast(0f)
                    IntOffset(x = (maxOffsetPx * animatedProgress).toInt(), y = 0)
                }
                .size(indicatorWidth, indicatorHeight)
                .background(indicatorColor, shape = RoundedCornerShape(cornerRadius))
        )
    }

    Spacer(modifier = Modifier.height(verticalPadding-8.dp))
}
