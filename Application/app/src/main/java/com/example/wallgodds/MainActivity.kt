package com.example.wallgodds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wallgodds.navigation.CustomNavigationBar
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.navigation.listOfNavItems
import com.example.wallgodds.screens.HomePage
import com.example.wallgodds.screens.ProfilePageScreen
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.ui.theme.WallGoddsTheme
import com.example.wallgodds.utils.LazyRowScrollbar
import com.example.wallgodds.utils.RandomWallpaperGrid


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WallGoddsTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Scaffold(
                    floatingActionButton = {
                        if (navBackStackEntry?.destination?.route in listOfNavItems.map { it.route }) {
                            Box(
                                modifier = Modifier.offset(y = 16.dp)
                            ) {
                                CustomNavigationBar(
                                    currentDestination = currentDestination,
                                    items = listOfNavItems,
                                    onItemClick = { item ->
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.home_page,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.favorites_page) {
                            HomePage(onProfileClick = { navController.navigate(Routes.profile_page) })
                        }
                        composable(Routes.home_page) {
                            HomePage(onProfileClick = { navController.navigate(Routes.profile_page) })
                        }
                        composable(Routes.upload_page) {
                            HomePage(onProfileClick = { navController.navigate(Routes.profile_page) })
                        }
                        composable(Routes.profile_page) {
                            ProfilePageScreen(
                                onBackPressed = { navController.popBackStack() },
                                onDarkModeClicked = {},
                                onFeedbackClicked = {},
                                onLogoutClicked = {},
                                onDeleteAccountClicked = {}
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WallGoddsTheme {
        HomePage()
    }
}