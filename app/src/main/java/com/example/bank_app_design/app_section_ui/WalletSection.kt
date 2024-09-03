package com.example.bank_app_design.app_section_ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app_design.R
import com.example.bank_app_design.ui.theme.BlueCenter
import com.example.bank_app_design.ui.theme.BlueEnd
import com.example.bank_app_design.ui.theme.BlueStart
import com.example.bank_app_design.userAccounts
import kotlin.math.round

val getGradient = Brush.linearGradient(
    colors = listOf(
        BlueEnd,
        BlueStart
    )
)

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun WalletSection() {

    val gradientColors = listOf(Color(0xFFDA4453), BlueCenter)

    var isBalanceVisible by remember {
        mutableStateOf(true)
    }

    val textBlur by animateDpAsState(
        targetValue = if (isBalanceVisible) 0.dp else 15.dp,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .height(120.dp)
            .background(getGradient)
            .border(
                BorderStroke(
                    width = 1.dp, // Adjust the border width as needed
                    brush = Brush.linearGradient(colors = gradientColors)
                ),
                shape = RoundedCornerShape(16.dp) // Apply rounded corners to the border
            )
            // Clip after the border to ensure corners are visible
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Balance Text with conditional blur
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .blur(
                            radius = textBlur,
                            edgeTreatment = BlurredEdgeTreatment.Rectangle
                        ),
                ) {
                    Text(
                        text = "${userAccounts[0].balance} $",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,// Optional: adjust font weight,
                        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                // Visibility Toggle Button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(BlueEnd)
                        .clickable { isBalanceVisible = !isBalanceVisible } // Toggle visibility
                        .padding(4.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isBalanceVisible) R.drawable.id_visibility_24 else R.drawable.ic_visibility_off_24
                        ),
                        contentDescription = "Toggle Balance Visibility",
                        modifier = Modifier.size(12.dp),
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(BlueCenter)
                        .padding(horizontal = 4.dp)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Default",
                        color = Color.White,
                        fontSize = 8.sp,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Saving",
                    fontSize = 8.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                )
            }

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_receive),
                contentDescription = "Receive Money",
                tint = Color.Unspecified,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Receive money",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_pay),
                contentDescription = "Receive Money",
                tint = Color.Unspecified,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = "Send money",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )

        }

    }


}
