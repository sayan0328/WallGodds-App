package com.example.wallgodds.screens

import android.graphics.ImageDecoder
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.Blue
import com.example.wallgodds.ui.theme.Gray
import com.example.wallgodds.utils.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUpPage(navController: NavController) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var wallpaperName by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {

        // Background image
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Top Bar
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                modifier = Modifier.padding(horizontal = AppPadding.MainContentPadding),
                navController = navController
            )

            // Scrollable Center Card
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                OutlinedCard(
                    modifier = Modifier
                        .padding(AppPadding.MainContentPadding),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Gray)
                ) {
                    Column {

                        // Header Bar
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Blue)
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_circle),
                                    contentDescription = "Circle",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painter = painterResource(R.drawable.ic_triangle),
                                    contentDescription = "Triangle",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painter = painterResource(R.drawable.ic_square),
                                    contentDescription = "Square",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            // Close button
                            IconButton(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Red, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Close",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }

                        // Scrollable Content
                        Box(Modifier.padding(horizontal = AppPadding.Medium, vertical = 12.dp)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .verticalScroll(scrollState)
                                    .fillMaxWidth()
                            ) {
                                val bitmap = remember(selectedImageUri) {
                                    selectedImageUri?.let {
                                        try {
                                            val source =
                                                ImageDecoder.createSource(
                                                    context.contentResolver,
                                                    it
                                                )
                                            ImageDecoder.decodeBitmap(source)
                                        } catch (_: Exception) {
                                            null
                                        }
                                    }
                                }

                                if (bitmap == null) {
                                    // Placeholder Box
                                    Box(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .fillMaxWidth()
                                            .clickable { imagePickerLauncher.launch("image/*") },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Canvas(
                                            modifier = Modifier
                                                .matchParentSize()
                                                .clip(RoundedCornerShape(12.dp))
                                        ) {
                                            val stroke = Stroke(
                                                width = 2.dp.toPx(),
                                                pathEffect = PathEffect.dashPathEffect(
                                                    floatArrayOf(
                                                        30f,
                                                        20f
                                                    ), 0f
                                                )
                                            )
                                            drawRoundRect(
                                                color = Gray,
                                                size = size,
                                                style = stroke,
                                                cornerRadius = CornerRadius(12.dp.toPx())
                                            )
                                        }

                                        Column(
                                            modifier = Modifier.padding(vertical = 50.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.upload_button_image),
                                                contentDescription = "Insert Image",
                                                tint = Color.Black,
                                                modifier = Modifier.size(40.dp)
                                            )
                                            Spacer(modifier = Modifier.height(6.dp))
                                            Text(
                                                text = "Insert Image",
                                                fontSize = 14.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                fontWeight = FontWeight.Normal,
                                                color = Color.Black,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                } else {
                                    // Box with fixed aspect ratio for uploaded image
                                    Box(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable { imagePickerLauncher.launch("image/*") },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        // Dotted border
                                        Canvas(modifier = Modifier.matchParentSize()) {
                                            val stroke = Stroke(
                                                width = 2.dp.toPx(),
                                                pathEffect = PathEffect.dashPathEffect(
                                                    floatArrayOf(
                                                        30f,
                                                        20f
                                                    ), 0f
                                                )
                                            )
                                            drawRoundRect(
                                                color = Gray,
                                                size = size,
                                                style = stroke,
                                                cornerRadius = CornerRadius(12.dp.toPx())
                                            )
                                        }

                                        Image(
                                            bitmap = bitmap.asImageBitmap(),
                                            contentDescription = "Selected Image",
                                            modifier = Modifier
                                                .padding(horizontal = 52.dp, vertical = 32.dp)
                                                .fillMaxSize()
                                                .aspectRatio(0.65f)
                                                .clip(RoundedCornerShape(16.dp)),
                                            contentScale = ContentScale.Crop
                                        )

                                        // Close button
                                        IconButton(
                                            onClick = { selectedImageUri = null },
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .padding(4.dp)
                                                .size(24.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Remove Image",
                                                tint = Color.Gray,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                // Wallpaper Name Field
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Wallpaper Name",
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(36.dp)
                                            .border(1.dp, Gray, RoundedCornerShape(12.dp))
                                            .padding(horizontal = 12.dp, vertical = 4.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        if (wallpaperName.isEmpty()) {
                                            Text(
                                                text = "Enter Your Wallpaper Name",
                                                color = Gray,
                                                fontSize = 12.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                fontWeight = FontWeight.Normal
                                            )
                                        }
                                        BasicTextField(
                                            value = wallpaperName,
                                            onValueChange = { wallpaperName = it },
                                            singleLine = true,
                                            textStyle = TextStyle(
                                                fontSize = 12.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                fontWeight = FontWeight.Normal,
                                                color = Color.Black
                                            ),
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                // Dropdown Menu
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = "Category",
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black,
                                        textAlign = TextAlign.Center
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))

                                    ExposedDropdownMenuBox(
                                        expanded = expanded,
                                        onExpandedChange = { expanded = !expanded }
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .menuAnchor()
                                                .fillMaxWidth()
                                                .height(36.dp)
                                                .border(1.dp, Gray, RoundedCornerShape(12.dp))
                                                .padding(horizontal = 12.dp, vertical = 4.dp),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            Text(
                                                text = if (selectedCategory.isNotEmpty()) selectedCategory else "Choose Your Category",
                                                color = if (selectedCategory.isNotEmpty()) Color.Black else Gray,
                                                fontSize = 12.sp,
                                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                fontWeight = FontWeight.Normal
                                            )

                                            Box(
                                                modifier = Modifier
                                                    .align(Alignment.CenterEnd)
                                                    .size(20.dp)
                                                    .background(Blue, shape = CircleShape),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.ArrowDropDown,
                                                    contentDescription = "Dropdown",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        }

                                        DropdownMenu(
                                            expanded = expanded,
                                            onDismissRequest = { expanded = false },
                                            modifier = Modifier
                                                .exposedDropdownSize()
                                                .heightIn(max = 200.dp)
                                        ) {
                                            listOf(
                                                "Abstract", "Nature", "Anime", "Art", "Movies",
                                                "Vehicles", "Sports", "Games", "Travel",
                                                "Spiritual", "Music", "AIGen"
                                            ).forEach { option ->
                                                DropdownMenuItem(
                                                    text = {
                                                        Text(
                                                            text = option,
                                                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                                            fontWeight = FontWeight.Normal,
                                                            fontSize = 14.sp
                                                        )
                                                    },
                                                    onClick = {
                                                        selectedCategory = option
                                                        expanded = false
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(24.dp))

                                // Submit Button
                                Button(
                                    onClick = {
                                        if (selectedImageUri != null && wallpaperName.isNotBlank() && selectedCategory.isNotEmpty()) {
                                            Toast.makeText(
                                                context,
                                                "Submitted successfully!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please fill all fields!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                                ) {
                                    Text(
                                        text = "Submit",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier.padding(horizontal = 16.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopUpPagePreview() {
    val fakeNavController = rememberNavController()
    com.example.wallgodds.ui.theme.WallGoddsTheme {
        PopUpPage(fakeNavController)
    }
}
