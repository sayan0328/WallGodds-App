package com.example.wallgodds.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.wallgodds.navigation.Routes
import com.example.wallgodds.ui.theme.*

@Composable
fun FeedbackPage(navController: NavController) {
    val options = listOf("Feedback", "Bug Report")
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Feedback") }

    // State for the Feedback form
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    // State for the Bug Report form
    var bugTitle by remember { mutableStateOf("") }
    var bugDetails by remember { mutableStateOf("") }
    var bugSteps by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Top Bar - Stays fixed at the top
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppPadding.MainContentPadding), // Use standard padding
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.navigate(Routes.home_page) },
                    modifier = Modifier.size(AppSize.IconMedium)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = "Back"
                    )
                }
                Box {
                    OutlinedButton(
                        onClick = { expanded = true },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
                        border = BorderStroke(0.2.dp, Gray),
                        contentPadding = PaddingValues(horizontal = AppPadding.Small)
                    ) {
                        Text(
                            text = selectedOption,
                            modifier = Modifier.padding(end = 12.dp),
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown",
                            tint = Color.White,
                            modifier = Modifier
                                .size(20.dp)
                                .background(Blue, shape = CircleShape)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium, color = Color.Black) },
                                onClick = {
                                    selectedOption = option
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = AppPadding.MainContentPadding),
                contentPadding = PaddingValues(bottom = AppPadding.Large)
            ) {

                item {
                    Spacer(modifier = Modifier.height(AppPadding.Large))
                }

                // Main Content Card
                item {
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Gray)
                    ) {
                        when (selectedOption) {
                            "Feedback" -> {

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(AppPadding.MainContentPadding)
                                ) {
                                    // ... (All original Feedback UI code remains here)
                                    Image(painter = painterResource(R.drawable.feedback_illustration), contentDescription = null, modifier = Modifier.align(Alignment.CenterHorizontally))
                                    Spacer(modifier = Modifier.height(24.dp))
                                    Text(text = "Start with a Title", fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium, color = Color.Black, textAlign = TextAlign.Center)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Box(modifier = Modifier.fillMaxWidth().height(40.dp).border(1.dp, Gray, shape = RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 8.dp), contentAlignment = Alignment.CenterStart) {
                                        if (title.isEmpty()) { Text(text = "Write a Title here", color = Gray, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium) }
                                        BasicTextField(value = title, onValueChange = { title = it }, singleLine = true, textStyle = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium, color = Color.Black), modifier = Modifier.fillMaxWidth())
                                    }
                                    Spacer(modifier = Modifier.height(24.dp))
                                    Text(text = "Tell Us More", fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium, color = Color.Black, textAlign = TextAlign.Center)
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Box(modifier = Modifier.fillMaxWidth().height(100.dp).border(1.dp, Gray, shape = RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 8.dp), contentAlignment = Alignment.TopStart) {
                                        if (desc.isEmpty()) { Text(text = "Write a description here", color = Gray, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium) }
                                        BasicTextField(value = desc, onValueChange = { desc = it }, textStyle = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium, color = Color.Black), modifier = Modifier.fillMaxWidth())
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))
                                    Text(text = "Your words matter to us.\nWallGodds is nothing without you!", color = Color.Black, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.CenterHorizontally))
                                    Spacer(modifier = Modifier.height(12.dp))
                                    val context = LocalContext.current
                                    val isFormValid = title.isNotBlank() && desc.isNotBlank()
                                    Button(onClick = { if (isFormValid) { Toast.makeText(context, "Submitted successfully!", Toast.LENGTH_SHORT).show() } }, colors = ButtonDefaults.buttonColors(containerColor = if (isFormValid) EnabledButtonColor else DisabledButtonColor), modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                        Text(text = "Submit", color = Color.White, fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium, modifier = Modifier.padding(horizontal = AppPadding.Medium))
                                    }
                                }
                            }
                            "Bug Report" -> {

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(AppPadding.MainContentPadding),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Image(painter = painterResource(id = R.drawable.bug_report), contentDescription = "Bug report illustration", contentScale = ContentScale.Fit)
                                    Spacer(modifier = Modifier.height(24.dp))

                                    // Title of the Bug
                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        FormTitle(title = "Title of the Bug")
                                        Spacer(modifier = Modifier.height(AppPadding.Small))

                                        Box(modifier = Modifier.fillMaxWidth().height(40.dp).border(1.dp, Gray, shape = RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 8.dp), contentAlignment = Alignment.CenterStart) {
                                            if (bugTitle.isEmpty()) { Text("Write a Title here", color = Gray, fontSize = 14.sp,fontFamily = FontFamily(Font(R.font.poppins_regular))) }
                                            BasicTextField(value = bugTitle, onValueChange = { bugTitle = it }, singleLine = true, textStyle = TextStyle(fontSize = 14.sp, color = Color.Black), modifier = Modifier.fillMaxWidth())
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(24.dp))

                                    // Drop the Details
                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        FormTitle(title = "Drop the Details")
                                        Spacer(modifier = Modifier.height(AppPadding.Small))

                                        Box(modifier = Modifier.fillMaxWidth().height(100.dp).border(1.dp, Gray, shape = RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 8.dp), contentAlignment = Alignment.TopStart) {
                                            if (bugDetails.isEmpty()) { Text("Write the Details here", color = Gray, fontSize = 14.sp,fontFamily = FontFamily(Font(R.font.poppins_regular))) }
                                            BasicTextField(value = bugDetails, onValueChange = { bugDetails = it }, textStyle = TextStyle(fontSize = 14.sp, color = Color.Black), modifier = Modifier.fillMaxWidth())
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(24.dp))

                                    // How it Went Wrong?
                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        FormTitle(title = "How it Went Wrong?")
                                        Spacer(modifier = Modifier.height(AppPadding.Small))

                                        Box(modifier = Modifier.fillMaxWidth().height(100.dp).border(1.dp, Gray, shape = RoundedCornerShape(12.dp)).padding(horizontal = 12.dp, vertical = 8.dp), contentAlignment = Alignment.TopStart) {
                                            if (bugSteps.isEmpty()) { Text("Steps to reproduce the bug", color = Gray, fontSize = 14.sp,fontFamily = FontFamily(Font(R.font.poppins_regular))) }
                                            BasicTextField(value = bugSteps, onValueChange = { bugSteps = it }, textStyle = TextStyle(fontSize = 14.sp, color = Color.Black), modifier = Modifier.fillMaxWidth())
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(24.dp))

                                    // How Bad Is It?
                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        FormTitle(title = "How Bad Is It?")
                                        Spacer(modifier = Modifier.height(AppPadding.Small))
                                        SeverityDropdown()
                                    }
                                    Spacer(modifier = Modifier.height(24.dp))

                                    // Got Evidence?
                                    Column(modifier = Modifier.fillMaxWidth()) {
                                        FormTitle(title = "Got Evidence?")
                                        Spacer(modifier = Modifier.height(AppPadding.Small))
                                        EvidenceBox(onClick = { /* Handle Upload */ })
                                    }
                                    Spacer(modifier = Modifier.height(24.dp))

                                    Text(text = "You found it. We'll fix it.\nTeamwork makes WallGodds work!", textAlign = TextAlign.Center, modifier = Modifier.padding(vertical = AppPadding.Medium),fontFamily = FontFamily(Font(R.font.poppins_regular)))
                                    Spacer(modifier = Modifier.height(12.dp))

                                    val isBugReportValid = bugTitle.isNotBlank() && bugDetails.isNotBlank() && bugSteps.isNotBlank()
                                    Button(onClick = { /* Handle Submit */ }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(AppPadding.Small), colors = ButtonDefaults.buttonColors(containerColor = if (isBugReportValid) AccentBlue else DisabledButtonColor)) {
                                        Text(text = "Submit", color = Color.White, fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)), fontWeight = FontWeight.Medium, modifier = Modifier.padding(horizontal = AppPadding.Medium))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


// --- HELPER COMPOSABLES ---
@Composable
fun FormTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start,
        fontFamily = FontFamily(Font(R.font.poppins_regular))
    )
}

@Composable
fun SeverityDropdown() {
    var expanded by remember { mutableStateOf(false) }
    val severities = listOf("Annoying", "Disruptive", "Serious", "Dangerous", "App-breaking")
    var selectedSeverity by remember { mutableStateOf(severities[0]) }

    Box {

        Surface(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(AppPadding.SemiMedium),
            border = BorderStroke(1.dp, Gray),
            color = SurfaceWhite
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppPadding.Small)
            ) {
                Text(text = selectedSeverity, modifier = Modifier.align(Alignment.Center), fontFamily = FontFamily(Font(R.font.poppins_regular)))
                Image(painter = painterResource(id = R.drawable.dropdown), contentDescription = "Select Severity", modifier = Modifier.align(Alignment.CenterEnd).size(AppPadding.MainContentPadding))
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            severities.forEach { severity ->
                DropdownMenuItem(
                    text = { Text(severity, fontFamily = FontFamily(Font(R.font.poppins_regular))) },
                    onClick = {
                        selectedSeverity = severity
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun EvidenceBox(onClick: () -> Unit) {

    val solidBorder = BorderStroke(1.dp, Gray)
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(AppPadding.SemiMedium),
        color = SurfaceWhite,
        border = solidBorder
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppPadding.Small, Alignment.CenterVertically)
        ) {
            Image(painter = painterResource(id = R.drawable.upload_button_image), contentDescription = "Upload Icon", modifier = Modifier.size(40.dp))
            Text(text = "Upload Your Screenshot", color = TextBlack, style = MaterialTheme.typography.bodyLarge, fontFamily = FontFamily(Font(R.font.poppins_regular)))
        }
    }
}

// --- PREVIEW ---
@Preview(showBackground = true)
@Composable
fun FeedbackPagePreview() {
    val navController = rememberNavController()
    FeedbackPage(navController = navController)
}