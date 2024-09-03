package com.example.bank_app_design.app_section_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app_design.R
import com.example.bank_app_design.ui.theme.BlueStart
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetWithChips() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var isSheetVisible by remember { mutableStateOf(false) }

    // Main screen content goes here
    Button(
        onClick = {
            scope.launch {
                isSheetVisible = true
            }
        },
        colors = ButtonDefaults.buttonColors(Color.White)
    ) {
        Text(
            text = "Edit Home",
            color = Color.DarkGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal

        )
    }

    if (isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isSheetVisible = false },
            sheetState = sheetState,
        ) {
            BottomSheetContent(
                onSaveClick = {
                    // Handle Save action (e.g., persist selected chip states)
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) isSheetVisible = false
                    }
                }
            )
        }
    }
}

@Composable
fun BottomSheetContent(
    onSaveClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            "Appearance", fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Your chip content here...
        var themesChipSelected by remember { mutableStateOf(true) }
        var darkModeChipSelected by remember { mutableStateOf(false) }
        var languageChipSelected by remember { mutableStateOf(false) }

        Row {
            AppChip(
                label = "Themes",
                selected = themesChipSelected,
                onClick = { themesChipSelected = !themesChipSelected }
            )
            Spacer(modifier = Modifier.width(8.dp))
            AppChip(
                label = "Dark Mode",
                selected = darkModeChipSelected,
                onClick = { darkModeChipSelected = !darkModeChipSelected }
            )
            Spacer(modifier = Modifier.width(8.dp))
            AppChip(
                label = "Language",
                selected = languageChipSelected,
                onClick = { languageChipSelected = !languageChipSelected }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Adding LazyRow for horizontal scrolling of ImageCards
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(8) { // Replace 8 with the number of items you want
                ImageCard()
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onSaveClick,
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(BlueStart)

        ) {
            Text(
                "Save",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun AppChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(label) },
        shape = RoundedCornerShape(16.dp),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            labelColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = Modifier.padding(4.dp)
    )
}

@Composable
fun ImageCard() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .height(120.dp),
        shadowElevation = 2.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.theme),
                contentDescription = "Theme Icon",
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetWithChipsPreview() {
    BottomSheetWithChips()
}
