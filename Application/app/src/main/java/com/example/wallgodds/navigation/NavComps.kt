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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.ui.theme.GrapePurple
import com.example.wallgodds.ui.theme.guedFontFamily

@Composable
fun CustomNavigationBar(
    currentDestination: NavDestination?,
    items: List<NavItem>,
    onItemClick: (NavItem) -> Unit
) {

//    val bottomFade = Brush.verticalGradient(0f to Color.Transparent, 0.2f to Color.Black.copy(0.4f))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppPadding.Medium)
                .height(72.dp)
                .clip(RoundedCornerShape(AppSize.MediumCornerRadius))
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
    item: NavItem,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    val fontSize by animateFloatAsState(
        targetValue = if (isSelected) 12f else 8f,
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
        targetValue = if (isSelected) 20.dp else 18.dp,
        animationSpec = tween(durationMillis = 500)
    )

    val shadowModifier = if (isSelected) {
        Modifier.shadow(elevation = 3.dp, shape = RoundedCornerShape(50))
    } else Modifier

    val selectedBackgroundColor by animateColorAsState(
        targetValue = if(isSelected) GrapePurple else Color.White,
        animationSpec = tween(durationMillis = 500)
    )


    val tint by animateColorAsState(
        targetValue = if (isSelected) Color.White else Gray,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(
                onClick = onItemClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Box(
            modifier = Modifier
//                .then(shadowModifier)
                .clip(RoundedCornerShape(AppSize.HighCornerRadius))
//                .then(borderModifier)
                .background(selectedBackgroundColor)
                .padding(horizontal = AppPadding.Large, vertical = AppPadding.SemiMedium)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = item.icon),
                    contentDescription = item.label,
                    modifier = Modifier.size(20.dp),
                    tint = tint
                )
                if(isSelected) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = item.label,
                        fontFamily = guedFontFamily,
                        color = tint,
                        fontSize = AppSize.FontSizeSmaller
                    )
                }
            }
        }
    }
}