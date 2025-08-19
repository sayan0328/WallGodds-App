package com.example.wallgodds.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.ui.theme.SoftPink

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var textValue by remember { mutableStateOf("") }
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppPadding.Medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .background(color = SoftPink, RoundedCornerShape(50))
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.wallgodds_icon),
                    contentDescription = null,
                    modifier = Modifier.size(AppSize.IconMedium)
                )
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(fontSize = AppSize.placeholderText),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box {
                            if (textValue.isEmpty()) {
                                Text(
                                    "Favourite Wallpapers, categories, etc",
                                    color = Color.Black.copy(0.5f),
                                    maxLines = 1,
                                    fontSize = AppSize.searchBarText,
                                    fontFamily = FontFamily(Font(R.font.comfortaa))
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                if (textValue.isNotEmpty()) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        modifier = Modifier.clickable { textValue = "" },
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = Color.Black.copy(0.5f)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Black.copy(0.5f),
                        modifier = Modifier.size(AppSize.IconSmall)
                    )
                }

            }
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(R.drawable.profile_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(AppSize.IconMedium)
                    .clickable {
                        navController.navigate(Routes.profile_page)
                    }
            )
        }
    }
}