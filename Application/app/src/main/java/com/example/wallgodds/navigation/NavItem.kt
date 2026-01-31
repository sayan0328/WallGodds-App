package com.example.wallgodds.navigation

import com.example.wallgodds.R

data class NavItem (
    val label: String,
    val icon: Int,
    val route: String
)

val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        label = "Home",
        icon = R.drawable.home_icon,
        route = Routes.home_page
    ),
    NavItem(
        label = "Upload",
        icon = R.drawable.upload_icon,
        route = Routes.upload_page
    ),
    NavItem(
        label = "Favorites",
        icon = R.drawable.favorites_icon,
        route = Routes.favorites_page
    ),
    NavItem(
        label = "Profile",
        icon = R.drawable.profile_icon,
        route = Routes.profile_page
    ),
    )