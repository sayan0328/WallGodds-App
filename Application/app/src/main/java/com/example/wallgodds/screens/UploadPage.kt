package com.example.wallgodds.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.poppinsFontFamily
import com.example.wallgodds.utils.dashedBorder

@Composable
fun UploadPage(navController: NavController) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppPadding.MainContentPadding)
            .padding(bottom = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(46.dp))
                .background(Color(0x85FFFFFF)) // #FFFFFF 52%
                .dashedBorder(
                    strokeWidth = 2.dp,
                    color = Color(0xFF8EA3E6),
                    cornerRadius = 46.dp,
                    dashLength = 8.dp,
                    gapLength = 8.dp
                )
                .clickable { launcher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(53.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF2F5FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.upload_icon),
                        contentDescription = "Upload Icon",
                        tint = Color(0xFF8EA3E6).copy(alpha = 0.75f),
                        modifier = Modifier.size(45.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Upload an Image",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily,
                    color = Color(0xFF3C56B1)
                )
            }
        }

        Spacer(modifier = Modifier.height(180.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Nothing here yet.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFontFamily,
                color = Color(0xFF808080)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Upload a wallpaper to share your creativity.",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFontFamily,
                color = Color(0xFF808080),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}



