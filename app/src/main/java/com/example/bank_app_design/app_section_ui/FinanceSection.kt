package com.example.bank_app_design.app_section_ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app_design.model.Finance
import com.example.bank_app_design.R
import com.example.bank_app_design.ui.theme.BlueCenter

val financeList = listOf(
    Finance(
        icon = R.drawable.ic_acc,
        name = "Accounts",
        info = "Your balance"
    ),
    Finance(
        icon = R.drawable.ic_bills,
        name = "Pay bills",
        info = "School, Shop, etc"
    ),
    Finance(
        icon = R.drawable.ic_transfer,
        name = "Transfer",
        info = "Send money"
    ),
    Finance(
        icon = R.drawable.ic_fav,
        name = "Favorites",
        info = "Users"
    ),
    Finance(
        icon = R.drawable.ic_scan,
        name = "BAB Scan",
        info = "School, Shop, etc"
    ),
    Finance(
        icon = R.drawable.ic_service,
        name = "Service",
        info = "Your balance"
    ),

    )

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun FinanceSection() {
    val gradientColors = listOf(Color(0xFFDA4453), BlueCenter)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(
                    width = 1.dp, // Adjust the border width as needed
                    brush = Brush.linearGradient(colors = gradientColors)
                ),
                shape = RoundedCornerShape(16.dp) // Apply the same corner radius here
            )
            .clip(RoundedCornerShape(16.dp)), // Clip the card to ensure corners are properly shaped
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .background(getGradient)
        ) {
            items(financeList.size) {
                FinanceCard(it)
            }
        }
    }
}

@Composable
fun FinanceCard(index: Int) {
    val finance = financeList[index]

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .height(85.dp)
                .fillMaxWidth()
                .background(Color.White)
                .clickable { }
                .padding(12.dp)
        ) {

            Icon(
                painter = painterResource(id = finance.icon),
                contentDescription = finance.name,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(20.dp)
                    .fillMaxWidth()
                    .align(Alignment.End)
            )

            Text(
                text = finance.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp),
                textAlign = TextAlign.Start

            )

            Text(
                text = finance.info,
                fontSize = 10.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp),
                textAlign = TextAlign.Start

            )
        }
    }

}