package com.example.wallgodds.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.utils.LazyRowScrollbar
import com.example.wallgodds.utils.RandomWallpaperGrid
import com.example.wallgodds.utils.TopAppBar
import com.example.wallgodds.utils.hasShownSnackbar
import com.example.wallgodds.utils.markSnackbarShown
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull


@Composable
fun HomePage(navController: NavController) {

    val wallpapers = List(50) {
        R.drawable.sample_wallpaper
    }

    val listState = rememberLazyListState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(500)

        if (!hasShownSnackbar(context)) {

            withTimeoutOrNull(3000) {
                snackbarHostState.showSnackbar(
                    message = "Double Tap to Like Wallpaper",
                    duration = SnackbarDuration.Indefinite
                )
            }

            markSnackbarShown(context)
        }
    }




    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // *** Use this as the background for your page ***
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.padding(horizontal = AppPadding.PaddingBetweenCategories)) {
                TopAppBar(
                    navController = navController
                )
            }
            LazyRow(
                state = listState,
                contentPadding = PaddingValues(horizontal = AppPadding.MainContentPadding),
                horizontalArrangement = Arrangement.spacedBy(AppPadding.PaddingBetweenCategories)
            ) {
                listOf<String>(
                    "Abstract",
                    "Nature",
                    "Anime",
                    "Art",
                    "Movies",
                    "Vehicles",
                    "Sports",
                    "Games",
                    "Travel",
                    "Spiritual",
                    "Music",
                    "AIGen",
                ).forEach {
                    item {
                        Box(
                            modifier = Modifier
                                .height(AppSize.CategoryPreview)
                                .aspectRatio(3f / 1f)
                                .clip(RoundedCornerShape(AppSize.LowCornerRadius))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.category_abstract),
                                contentDescription = "Book Cover",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Text(
                                text = it,
                                color = Color.White,
                                fontSize = AppSize.FontSizeSmall,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.5f)),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            LazyRowScrollbar(
                listState = listState,
                indicatorColor = Color.Black,
                indicatorHeight = AppSize.indicatorHeight,
                indicatorWidth = AppSize.indicatorWidth,
                trackColor = Color.Gray,
                trackHeight = AppSize.trackHeight,
                trackWidth = AppSize.trackWidth,
                cornerRadius = AppSize.HighCornerRadius,
                verticalPadding = AppPadding.Medium
            )
            RandomWallpaperGrid(
                wallpapers = wallpapers,
                onWallpaperClick = { imageResId ->
                    // Navigate to expanded wallpaper screen
                    navController.navigate("${Routes.expanded_wallpaper_page.replace("{wallpaperId}", imageResId.toString())}")
                }
            )

        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(vertical = 90.dp),
            snackbar = { data ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(AppSize.HighCornerRadius)
                        )
                ) {
                    Text(
                        text = data.visuals.message,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(vertical = 16.dp, horizontal = 24.dp)
                            .fillMaxWidth()
                    )
                }
            }
        )
    }
}