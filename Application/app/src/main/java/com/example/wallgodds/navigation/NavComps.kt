package com.example.wallgodds.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize

@Composable
fun CustomNavigationBar(
    currentDestination: NavDestination?,
    items: List<NavItems>,
    onItemClick: (NavItems) -> Unit
) {

    val bottomFade = Brush.verticalGradient(0f to Color.Transparent, 0.2f to Color.Black.copy(0.4f))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bottomFade),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppPadding.Medium)
                .height(100.dp)
                .clip(RoundedCornerShape(topStart = AppSize.HighCornerRadius, topEnd = AppSize.HighCornerRadius))
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                CustomNavigationBarItem(
                    item = item,
                    isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onItemClick = { onItemClick(item) }
                )
            }
        }
    }
}

@Composable
fun CustomNavigationBarItem(
    item: NavItems,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    val fontSize by animateFloatAsState(
        targetValue = if (isSelected) 16f else 12f,
        animationSpec = tween(durationMillis = 500)
    )
    val borderAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    val borderBrush = Brush.horizontalGradient(
        colors = listOf(Color(0xFFFF8A65).copy(alpha = borderAlpha), Color(0xFF4DD0E1).copy(alpha = borderAlpha))
    )
    val borderModifier = if (borderAlpha > 0f) {
        Modifier.border(
            width = 2.dp,
            brush = borderBrush,
            shape = RoundedCornerShape(50)
        )
    } else Modifier
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 16.dp else 12.dp,
        animationSpec = tween(durationMillis = 500)
    )
    val shadowModifier = if (isSelected) {
        Modifier.shadow(elevation = 3.dp, shape = RoundedCornerShape(50))
    } else Modifier
    val tint by animateColorAsState(
        targetValue = if (isSelected) Color.Black else Gray,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                onClick = onItemClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Box(
            modifier = Modifier
                .then(shadowModifier)
                .clip(RoundedCornerShape(50))
                .then(borderModifier)
                .background(Color.White)
                .padding(horizontal = AppPadding.Large, vertical = AppPadding.Medium)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = item.icon),
                    contentDescription = item.label,
                    modifier = Modifier.size(iconSize),
                    tint = tint
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = item.label,
                    color = tint,
                    fontSize = fontSize.sp
                )
            }
        }
    }
}