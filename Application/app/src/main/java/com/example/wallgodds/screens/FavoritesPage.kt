package com.example.wallgodds.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.poppinsFontFamily
import com.example.wallgodds.utils.ContentPlaceholder

@Composable
fun FavoritesPageScreen(navController: NavController) {
   val favoriteWallpapers = List(30) { R.drawable.sample_wallpaper } // Static list for now
    var sortOption by remember { mutableStateOf("Newest") }
    var sortExpanded by remember { mutableStateOf(false) }
    val isEmpty = true
    var searchText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppPadding.MainContentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {searchText = it},
                modifier = Modifier
                   // .width(215.dp)
                    .weight(1f)
                    .height(48.dp),
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFF929292),
                    )
                },
                placeholder = {
                    Text("Search",
                        fontSize= 14.sp,
                        lineHeight = 14.sp,
                        color = Color(0xFF929292)
                    )},
                singleLine = true,
                minLines = 1,
                maxLines = 1,
                textStyle = TextStyle(fontSize = 14.sp,lineHeight = 14.sp ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White.copy(alpha = 0.6f),
                    focusedContainerColor = Color.White.copy(alpha = 0.6f),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    focusedTextColor =Color(0xFF929292),
                    unfocusedTextColor = Color(0xFF929292),
                    cursorColor = Color(0xFF929292)
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Box {
                OutlinedButton(
                    onClick = { sortExpanded = true },
                    modifier = Modifier
                        .width(100.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White.copy(alpha = 0.6f)
                    ),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = sortOption,
                            color = Color(0xFF929292),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color(0xFF929292),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                
                    DropdownMenu(
                        expanded = sortExpanded,
                        onDismissRequest = { sortExpanded = false },
                        modifier = Modifier.background(Color.White.copy(alpha = 0.6f))
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Newest",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF929292),
                                    fontFamily = poppinsFontFamily
                                )
                            },
                            onClick = {
                                sortOption = "Newest"
                                sortExpanded = false
                            },
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Oldest",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color(0xFF929292),
                                    fontFamily = poppinsFontFamily
                                )
                            },
                            onClick = {
                                sortOption = "Oldest"
                                sortExpanded = false
                            }
                        )
                    }
            }
        }
        Spacer(modifier = Modifier.height(180.dp))
        if (isEmpty) {
            EmptyState()
        }
    }
}




@Composable
private fun EmptyState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.favourite_page_guy),
            contentDescription = "No favourites illustration",
            modifier = Modifier
                .size(220.dp, 200.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "No favourites yet.",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            color = Color(0xFF808080),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Start saving wallpapers you like.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily,
            color = Color(0xFF808080)
        )
    }
}



