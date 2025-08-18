package com.example.wallgodds.screens

import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.ui.theme.BackgroundIcon
import com.example.wallgodds.utils.TopAppBar
import java.io.IOException

@Composable
fun ExpandedWallpaperPage(
	wallpaperId: Int,
	navController: NavController
) {
	val context = LocalContext.current
	var showPopup by remember { mutableStateOf(false) }

	Box() {

		Image(
			painter = painterResource(R.drawable.background),
			contentDescription = "background",
			contentScale = ContentScale.Crop,
			modifier = Modifier.fillMaxSize()
		)

		TopAppBar(
			modifier = Modifier.padding(horizontal = AppPadding.MainContentPadding),
			navController = navController
		)

		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(
					top = 105.dp,
					start = AppPadding.MainContentPadding,
					end = AppPadding.MainContentPadding,

					),
			verticalArrangement = Arrangement.SpaceBetween, // Keep this for proper spacing
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			Image(
				painter = painterResource(id = wallpaperId),
				contentDescription = "Selected wallpaper",
				modifier = Modifier
					.padding(bottom = 10.dp)
					.fillMaxWidth()
					.aspectRatio(0.65f)
					.clip(RoundedCornerShape(AppSize.HighCornerRadius)),
				contentScale = ContentScale.Crop
			)

			Spacer(modifier = Modifier.height(AppPadding.Smallest))


			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						top = AppPadding.Small,
						start = AppPadding.Large,
						end = AppPadding.Large,
						bottom = AppPadding.Large
					),
				horizontalArrangement = Arrangement.spacedBy(AppPadding.Medium, Alignment.CenterHorizontally),

				verticalAlignment = Alignment.CenterVertically
			) {

				ActionButton(
					iconRes = R.drawable.back_button_icon,
					label = "Back",
					onClick = {
						navController.popBackStack()
					},
//
				)

				ActionButton(
					iconRes = R.drawable.download_button_icon,
					label = "Download",
					onClick = {}
				)


				ActionButton(
					iconRes = R.drawable.set_button_icon,
					label = "Set",
					onClick = { showPopup = true }
				)
			}
		}

		if (showPopup) {
			WallpaperSetPopup(
				onDismiss = { showPopup = false },
				onSetHome = {
					setWallpaper(context, wallpaperId, WallpaperManager.FLAG_SYSTEM)
					showPopup = false
				},
				onSetLock = {
					setWallpaper(context, wallpaperId, WallpaperManager.FLAG_LOCK)
					showPopup = false
				},
				onSetBoth = {
					setWallpaper(
						context,
						wallpaperId,
						WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK
					)
					showPopup = false
				}
			)
		}
	}
}

@Composable
private fun ActionButton(
	iconRes: Int,
	label: String,
	onClick: () -> Unit
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(AppPadding.Small),
		modifier = Modifier
			.clickable { onClick() }
			.padding(AppPadding.Smallest)
	) {
		Box(
			modifier = Modifier
				.size(58.dp)
				.clip(CircleShape)

				.background(
					color = BackgroundIcon
				),
			contentAlignment = Alignment.Center
		) {
			Icon(
				painter = painterResource(id = iconRes),
				contentDescription = label,
				tint = Color.White,
				modifier = Modifier.size(AppSize.IconSmall)
			)
		}

		Text(
			text = label,
			color = BackgroundIcon,
			style = MaterialTheme.typography.bodyMedium
		)
	}
}

@Composable
fun WallpaperSetPopup(
	onDismiss: () -> Unit,
	onSetHome: () -> Unit,
	onSetLock: () -> Unit,
	onSetBoth: () -> Unit
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(Color.Black.copy(alpha = 0.5f)),
		contentAlignment = Alignment.BottomCenter
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp)
				.clip(RoundedCornerShape(24.dp))
				.border(
					width = 2.dp,
					brush = Brush.linearGradient(
						listOf(
							Color(0x80FF5100),
							Color(0x8000A483),
							Color(0x804400FF)
						)
					),
					shape = RoundedCornerShape(24.dp)
				)
				.background(Color.White)
				.padding(20.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			Text(
				"What would you like to do?",
				style = TextStyle(
					fontFamily = FontFamily(Font(R.font.poppins_medium)),
					fontWeight = FontWeight.Medium,
					fontSize = 16.sp,
					textAlign = TextAlign.Center
				),
				color = Color(0xFF29323B)
			)

			Column(
				modifier = Modifier.fillMaxWidth(0.8f),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				PopupOption(
					icon = R.drawable.ic_wallpaper_home,
					label = "Set on home screen",
					onClick = onSetHome
				)
				PopupOption(
					icon = R.drawable.ic_wallpaper_lock,
					label = "Set on lock screen",
					onClick = onSetLock
				)
				PopupOption(
					icon = R.drawable.ic_wallpaper_both,
					label = "Set on both screens",
					onClick = onSetBoth
				)
			}

			Button(
				onClick = onDismiss,
				modifier = Modifier
					.fillMaxWidth(0.8f)
					.padding(top = 4.dp),
				colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5100)),
				shape = RoundedCornerShape(50)
			) {
				Text(
					"Cancel",
					style = TextStyle(
						fontFamily = FontFamily(Font(R.font.poppins_regular)),
						fontWeight = FontWeight.Normal,
						fontSize = 14.sp,
						textAlign = TextAlign.Center
					),
					color = Color.White
				)
			}
		}
	}
}

@Composable
fun PopupOption(icon: Int, label: String, onClick: () -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.clickable { onClick() }
			.padding(vertical = 8.dp, horizontal = 8.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			painter = painterResource(id = icon),
			contentDescription = label,
			tint = BackgroundIcon,
			modifier = Modifier.size(35.dp)
		)
		Spacer(modifier = Modifier.width(24.dp))
		Text(
			label,
			style = TextStyle(
				fontFamily = FontFamily(Font(R.font.poppins_regular)),
				fontWeight = FontWeight.ExtraLight,
				fontSize = 13.sp,
				textAlign = TextAlign.End
			),
			color = Color(0xFF29323B)
		)
	}
}

fun setWallpaper(context: Context, wallpaperResId: Int, flag: Int) {
	val wallpaperManager = WallpaperManager.getInstance(context)
	val bitmap = BitmapFactory.decodeResource(context.resources, wallpaperResId)

	try {
		wallpaperManager.setBitmap(bitmap, null, true, flag)

		val message = when (flag) {
			WallpaperManager.FLAG_SYSTEM -> "Wallpaper set on Home screen"
			WallpaperManager.FLAG_LOCK -> "Wallpaper set on Lock screen"
			WallpaperManager.FLAG_SYSTEM or WallpaperManager.FLAG_LOCK -> "Wallpaper set on Home & Lock screens"
			else -> "Wallpaper applied"
		}
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

	} catch (e: IOException) {
		e.printStackTrace()
		Toast.makeText(context, "Failed to set wallpaper", Toast.LENGTH_SHORT).show()
	}
}