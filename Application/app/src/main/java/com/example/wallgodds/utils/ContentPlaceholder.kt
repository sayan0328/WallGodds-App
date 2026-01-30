package com.example.wallgodds.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallgodds.ui.theme.AppSize
import com.example.wallgodds.ui.theme.AshGray
import com.example.wallgodds.ui.theme.Blue
import com.example.wallgodds.ui.theme.GrapePurple
import com.example.wallgodds.ui.theme.guedFontFamily
import androidx.core.net.toUri

@Composable
fun ContentPlaceholder(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
            .padding(vertical = 80.dp)
            .padding(bottom = 120.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.5f), RoundedCornerShape(AppSize.LowCornerRadius))
                .dashedBorder(
                    strokeWidth = 0.5.dp,
                    color = Blue,
                    cornerRadius = AppSize.LowCornerRadius,
                    dashLength = 8.dp,
                    gapLength = 7.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = message,
                fontFamily = guedFontFamily,
                fontSize = 25.sp,
                color = AshGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = annotatedMessage,
                onTextLayout = { textLayoutResult = it },
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .pointerInput(Unit) {
                        detectTapGestures { tapOffset ->

                            val layout = textLayoutResult ?: return@detectTapGestures
                            val charIndex = layout.getOffsetForPosition(tapOffset)

                            // Wondering why is this done in such a complicated way?
                            // Well, not every OS is made perfect 🥲

                            annotatedMessage
                                .getStringAnnotations(
                                    start = charIndex,
                                    end = charIndex
                                )
                                .firstOrNull()
                                ?.let { annotation ->
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        annotation.item.toUri()
                                    )
                                    context.startActivity(intent)
                                }
                        }
                },
                fontFamily = guedFontFamily,
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

fun Modifier.dashedBorder(
    strokeWidth: Dp,
    color: Color,
    cornerRadius: Dp,
    dashLength: Dp,
    gapLength: Dp
): Modifier = this.then(
    Modifier.drawBehind {
        val stroke = Stroke(
            width = strokeWidth.toPx(),
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(
                    dashLength.toPx(),
                    gapLength.toPx()
                )
            )
        )

        drawRoundRect(
            color = color,
            size = size,
            cornerRadius = CornerRadius(
                cornerRadius.toPx(),
                cornerRadius.toPx()
            ),
            style = stroke
        )
    }
)

const val message = "This section is being designed and will be available for contributors soon"

val annotatedMessage = buildAnnotatedString {

    withStyle(SpanStyle(color = AshGray)) {
        append("Keep an eye on ")
    }

    pushStringAnnotation(
        tag = "GITHUB",
        annotation = "https://github.com/WallGodds/WallGodds-App/issues"
    )
    withStyle(SpanStyle(color = GrapePurple)) {
        append("GitHub ")
    }
    pop()

    withStyle(SpanStyle(color = AshGray)) {
        append("and ")
    }

    pushStringAnnotation(
        tag = "DISCORD",
        annotation = "https://discord.gg/kTQ5KWANp8"
    )
    withStyle(SpanStyle(color = GrapePurple)) {
        append("Discord ")
    }
    pop()

    withStyle(SpanStyle(color = AshGray)) {
        append("for updates and announcements")
    }
}