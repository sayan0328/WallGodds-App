package com.example.wallgodds.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.ui.theme.BackgroundIcon
import com.example.wallgodds.utils.TopAppBar

@Composable
fun ExpandedWallpaperPage(
	wallpaperId: Int,
	navController: NavController
) {

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
					.padding(top = AppPadding.Small, start = AppPadding.Large,end = AppPadding.Large, bottom = AppPadding.Large),
				horizontalArrangement = Arrangement.spacedBy(AppPadding.Medium, Alignment.CenterHorizontally),

				verticalAlignment = Alignment.CenterVertically
			) {

				ActionButton(
					iconRes = R.drawable.vector,
					label = "Back",
					onClick = {
						navController.popBackStack()
					},
//
				)

				ActionButton(
					iconRes = R.drawable.vector_1_,
					label = "Download",
					onClick = {}
				)


				ActionButton(
					iconRes = R.drawable.vector_2_,
					label = "Set",
					onClick = {}
				)
			}
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
