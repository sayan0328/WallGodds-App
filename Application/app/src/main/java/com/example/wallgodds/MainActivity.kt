package com.example.wallgodds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wallgodds.navigation.CustomNavigationBar
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.navigation.listOfNavItems
import com.example.wallgodds.screens.FavoritesPageScreen
import com.example.wallgodds.screens.HomePage
import com.example.wallgodds.screens.ProfilePageScreen
import com.example.wallgodds.screens.UploadPage
import com.example.wallgodds.ui.theme.WallGoddsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
            android.graphics.Color.TRANSPARENT,
            android.graphics.Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT)
        )
        setContent {
            WallGoddsTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavHost(
                    navController = navController,
                    startDestination = Routes.home_page,
                ) {
                    composable(Routes.favorites_page) {
                        FavoritesPageScreen(navController)
                    }
                    composable(Routes.home_page) {
                        HomePage(navController)
                    }
                    composable(Routes.upload_page) {
                        UploadPage(navController)
                    }
                    composable(Routes.profile_page) {
                        ProfilePageScreen(navController)
                    }
                }


                Scaffold(
                    floatingActionButton = {
                        if (navBackStackEntry?.destination?.route in listOfNavItems.map { it.route }) {
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
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.background),
                            contentDescription = "Background Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize())
                        NavHost(
                            navController = navController,
                            startDestination = Routes.home_page,
                            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
                        ) {
                            composable(Routes.favorites_page) {
                                FavoritesPageScreen(navController)
                            }
                            composable(Routes.home_page) {
                                HomePage(navController)
                            }
                            composable(Routes.upload_page) {
                                UploadPage(navController)
                            }
                            composable(Routes.profile_page) {
                                ProfilePageScreen(navController)
                            }
                        }
                    }

                }
            }
        }
    }
}
