package com.example.wallgodds.utils

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize

@Composable
fun RandomWallpaperGrid(
    wallpapers: List<Int>,
    onWallpaperClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppPadding.MainContentPadding)
            .padding(top = AppPadding.Small),
        verticalArrangement = Arrangement.spacedBy(AppPadding.Small)
    ) {
        val chunkedImages = wallpapers.chunked(2)
        item {
            HeroSection()
        }
        items(chunkedImages.size) { index ->
            val images = chunkedImages[index]
            WallpaperGrid(images,onWallpaperClick)
        }
    }
}

@Composable
fun WallpaperGrid(
    images: List<Int>,
    onWallpaperClick: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppPadding.Small)
    ) {
        images.forEach { image ->
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onWallpaperClick(image) }
                    .weight(1f)
                    .aspectRatio(9f / 16f)
                    .clip(RoundedCornerShape(AppSize.WallpaperRoundedCorner)),
                contentScale = ContentScale.Fit
            )
        }

    }
}

@Composable
fun HeroSection() {
    val context = LocalContext.current
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    "https://wallgodds.vercel.app/".toUri()
                )
                context.startActivity(intent)
            }){
        Image(
            painter = painterResource(R.drawable.hero_box),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(AppSize.MediumCornerRadius))
                .border(
                    width = 0.5.dp, color = Color.LightGray,
                    RoundedCornerShape(AppSize.MediumCornerRadius)
                )
                .aspectRatio(2.5f / 1f),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "WallGodds",
                fontFamily = FontFamily(Font(R.font.gruppo_regular)),
                fontSize = AppSize.FontSizeExtraLarge,
                color = Color.Black
            )
            Text(
                "Minimal by Design, Inspired by You",
                fontFamily = FontFamily(Font(R.font.instrument_serif_italic)),
                fontSize = AppSize.HeroFontSizeMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                "“Walls That Fit Every Screen — On the Web.”",
                fontFamily = FontFamily(Font(R.font.habibi)),
                fontSize = AppSize.HeroFontSizeSmall,
                color = Color.Black
            )
        }
    }
}