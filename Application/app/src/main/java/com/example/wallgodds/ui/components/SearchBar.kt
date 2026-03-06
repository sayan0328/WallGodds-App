package com.example.wallgodds.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallgodds.R
import com.example.wallgodds.ui.theme.poppinsFontFamily

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(16.dp),
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_figma),
                contentDescription = "Search",
                tint = Color(0xFF929292),
                modifier = Modifier.size(20.dp)
            )
        },
        placeholder = {
            Text(
                text = "Search",
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily,
                lineHeight = 14.sp,
                color = Color(0xFF929292)
            )
        },
        singleLine = true,
        minLines = 1,
        maxLines = 1,
        textStyle = TextStyle(fontSize = 14.sp, lineHeight = 14.sp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White.copy(alpha = 0.6f),
            focusedContainerColor = Color.White.copy(alpha = 0.6f),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            focusedTextColor = Color(0xFF929292),
            unfocusedTextColor = Color(0xFF929292),
            cursorColor = Color(0xFF929292)
        )
    )
}