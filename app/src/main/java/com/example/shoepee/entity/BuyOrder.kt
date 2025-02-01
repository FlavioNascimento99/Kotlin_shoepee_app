package com.example.shoepee.entity

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class BuyOrder {
    val id: String = ""
    val client: Client = Client()
    val items: List<Item> = emptyList()
    val totalValue: Double = 0.0
    val status: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    val dateBuy: LocalDateTime = LocalDateTime.now()
}