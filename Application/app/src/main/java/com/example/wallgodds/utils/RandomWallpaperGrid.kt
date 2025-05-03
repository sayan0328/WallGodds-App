package com.example.wallgodds.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize

@Composable
fun RandomWallpaperGrid(wallpapers: List<Int>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppPadding.MainContentPadding,vertical = AppPadding.Small),
        verticalArrangement = Arrangement.spacedBy(AppPadding.Small)
    ) {
        val chunkedImages = wallpapers.chunked(3)
        items(chunkedImages.size) { index ->
            val images = chunkedImages[index]
            val layoutType = index % 4
            when(layoutType) {
                0 -> BigOneOnTheLeft(images)
                1 -> ThreeInRow(images)
                2 -> BigOneOnTheRight(images)
                else -> ThreeInRow(images)
            }
        }
    }
}

@Composable
fun BigOneOnTheLeft(images: List<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppPadding.Small)
    ) {
        images.getOrNull(0)?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier
                    .weight(2f)
                    .aspectRatio(9f / 16f)
                    .clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner))
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Fit
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(AppPadding.Smallest)
        ) {
            images.getOrNull(1)?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(9f / 16f)
                        .clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner)),
                    contentScale = ContentScale.Fit
                )
            }
            images.getOrNull(2)?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(9f / 16f)
                        .clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner)),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun ThreeInRow(images: List<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppPadding.Small)
    ) {
        images.forEach { image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(9f / 16f)
                    .clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner)),
                contentScale = ContentScale.Fit
            )
        }

    }
}

@Composable
fun BigOneOnTheRight(images: List<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppPadding.Small)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(AppPadding.Smallest)
        ) {
            images.getOrNull(0)?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(9f / 16f)
                        .clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner)),
                    contentScale = ContentScale.Fit
                )
            }
            images.getOrNull(1)?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(9f / 16f)
                        .clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner)),
                    contentScale = ContentScale.Fit
                )
            }
        }
        images.getOrNull(2)?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = null,
                modifier = Modifier
                    .weight(2f)
                    .aspectRatio(9f / 16f)
                    .clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner))
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Fit
            )
        }
    }
}