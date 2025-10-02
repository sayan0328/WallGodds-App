package com.example.wallgodds.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wallgodds.ui.theme.AppSize

@Composable
fun SnackBarComponent(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(vertical = 90.dp),
        snackbar = { data ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(AppSize.HighCornerRadius)
                    )
            ) {
                Text(
                    text = data.visuals.message,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                        .fillMaxWidth()
                )
            }
        }
    )
}