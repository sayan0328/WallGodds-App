package com.example.wallgodds.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.utils.ContentPlaceholder

@Composable
fun FavoritesPageScreen(navController: NavController) {
    val favoriteWallpapers = List(30) { R.drawable.sample_wallpaper } // Static list for now

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppPadding.MainContentPadding)
    ) {
        // Code here
        ContentPlaceholder()
    }
}