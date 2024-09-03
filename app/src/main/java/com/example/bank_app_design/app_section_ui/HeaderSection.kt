package com.example.bank_app_design.app_section_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.bank_app_design.R
import com.example.bank_app_design.configuration.generateQRCode
import com.example.bank_app_design.userAccounts

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HeaderSection() {
    var isDialogOpen by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Profile Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.lisa_profile),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "Hello, ${userAccounts[0].name}!",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "View Profile",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

        // QR Code and Notifications Icons
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.NotificationsNone,
                contentDescription = "Notifications",
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_bk),
                contentDescription = "QR Code",
                tint = Color.Unspecified,
                modifier = Modifier.clickable {
                    isDialogOpen = true
                }
            )

        }
    }

    // Display the QR Code Dialog
    if (isDialogOpen) {
        QRCodeDialog(
            qrText = userAccounts[0].linkQr,
            userName = userAccounts[0].name,
            amount = 0.0.toString(),
            onDismiss = { isDialogOpen = false }
        )
    }
}


// QR Code Dialog
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRCodeDialog(
    qrText: String,
    userName: String,
    amount: String,
    onDismiss: () -> Unit,
) {


    Dialog(
        onDismissRequest = onDismiss,
//        properties = DialogProperties(
//            usePlatformDefaultWidth = false // This makes the dialog full screen
//        )
    ) {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                    title = {},
                    actions = {
                        IconButton(
                            onClick = onDismiss
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.White // Use black or any other color that fits your design
                            )
                        }
                    }
                )
            },

            ) { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                // Background with all content
                QRCodeContainer(qrText = qrText, userName = userName, amount = amount)
            }
        }
    }
}

// Composable function to display the QR code in the UI
@Composable
fun QRCodeView(text: String) {
    val qrCodeBitmap = generateQRCode(text)

    Image(
        bitmap = qrCodeBitmap.asImageBitmap(),
        contentDescription = "QR Code",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(270.dp) // Adjust size as needed
            .padding(top = 8.dp)
    )
}

@Composable
fun QRCodeContainer(qrText: String, userName: String, amount: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f) // Adjust the aspect ratio as needed
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.qrbg),
            contentDescription = "QR Code Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header section (e.g., logo, label)
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.30f)
                    .fillMaxWidth()
                    .padding(start = 30.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = userName.uppercase(),
                    color = Color.DarkGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Amount section
                Text(
                    text = "$ 0",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold

                )

            }

            // QR Code View
            Column(
                modifier = Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                QRCodeView(text = qrText)

            }
        }
    }
}


@Composable
fun Preview() {
    QRCodeContainer(
        userName = "Srun Lisa",
        qrText = "https://pay.ababank.com/L5wRuBdxCFvy8YhS7", amount = "1000.0"
    )
}


