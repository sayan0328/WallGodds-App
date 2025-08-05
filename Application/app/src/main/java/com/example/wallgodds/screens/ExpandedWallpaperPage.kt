
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.AppPadding
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.ui.theme.BackgroundDark
import com.example.wallgodds.utils.TopAppBar

@Composable
fun ExpandedWallpaperPage(
	wallpaperId: Int,
	navController: NavController
) {
	val context = LocalContext.current

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
					top = 90.dp,
					start = AppPadding.MainContentPadding,
					end = AppPadding.MainContentPadding,
					bottom = AppPadding.MainContentPadding
				),
			verticalArrangement = Arrangement.spacedBy(AppPadding.Small), // Keep this for proper spacing
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			Image(
				painter = painterResource(id = wallpaperId),
				contentDescription = "Selected wallpaper",
				modifier = Modifier
					.fillMaxWidth()
					.aspectRatio(0.7f)
					.clip(RoundedCornerShape(AppSize.HighCornerRadius)),
				contentScale = ContentScale.Crop
			)

			Spacer(modifier = Modifier.height(AppPadding.Small))


			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = AppPadding.Small, start = AppPadding.Large,end = AppPadding.Large, bottom = AppPadding.Large),
				horizontalArrangement = Arrangement.SpaceEvenly,
				verticalAlignment = Alignment.CenterVertically
			) {

				ActionButton(
					iconRes = R.drawable.vector,
					label = "Back",
					onClick = {
						navController.popBackStack()
					}
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
		modifier = Modifier
			.clickable { onClick() }
			.padding(AppPadding.Smallest)
	) {
		Box(
			modifier = Modifier
				.size(64.dp)
				.clip(CircleShape)
				.background(
					color = BackgroundDark
				),
			contentAlignment = Alignment.Center
		) {
			Image(
				painter = painterResource(id = iconRes),
				contentDescription = label,
				modifier = Modifier.size(AppSize.IconMedium)
			)
		}

		Spacer(modifier = Modifier.height(4.dp))

		Text(
			text = label,
			color = BackgroundDark,
			style = MaterialTheme.typography.bodyMedium
		)
	}
}
