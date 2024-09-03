package com.example.bank_app_design

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bank_app_design.app_section_ui.BottomSheetWithChips
import com.example.bank_app_design.app_section_ui.FinanceSection
import com.example.bank_app_design.app_section_ui.HeaderSection
import com.example.bank_app_design.app_section_ui.PromotionSlideShow
import com.example.bank_app_design.app_section_ui.WalletSection
import com.example.bank_app_design.configuration.generateQRCode
import com.example.bank_app_design.model.UserAccount
import com.example.bank_app_design.ui.theme.BankAppDesignTheme
import com.example.bank_app_design.ui.theme.BlueEnd
import com.example.bank_app_design.ui.theme.BlueStart
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankAppDesignTheme {
                // A surface container using the 'background' color from the theme
                SetBarColor(BlueStart)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BlueStart

                ) {

                    HomeScreen()

                }
            }
        }
    }
}

@Composable
private fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}

val userAccounts = listOf(
    UserAccount(
        name = "Srun Lisa",
        accountNumber = "123456789",
        balance = 1000.0,
        profile = R.drawable.lisa_profile,
        linkQr = "https://pay.ababank.com/L5wRuBdxCFvy8YhS7"
    ),
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BlueStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(22.dp))
            WalletSection()
            Spacer(modifier = Modifier.height(16.dp))
            FinanceSection()
            Spacer(modifier = Modifier.height(22.dp))
            PromotionSlideShow()
            Spacer(modifier = Modifier.height(16.dp))
            BottomSheetWithChips()
        }
    }
}




