package com.example.bank_app_design.model

data class UserAccount(
    val name: String,
    val accountNumber: String,
    val balance: Double,
    val profile: Int,
    val linkQr: String
)
