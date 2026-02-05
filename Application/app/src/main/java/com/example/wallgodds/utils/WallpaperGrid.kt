package com.example.wallgodds.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

fun LazyGridScope.WallpaperGrid(wallpapers: List<Any>, onClick: () -> Unit = {}) {
    items(wallpapers) { wallpaper ->
        Box(
            modifier = Modifier
                .height(320.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
            AsyncImage(
                model = wallpaper,
                contentDescription = "Wallpaper image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick }
            )
        }
    }
}