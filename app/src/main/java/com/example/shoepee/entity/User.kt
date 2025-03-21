package com.example.shoepee.entity

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import com.example.shoepee.entity.Address
import com.example.shoepee.entity.BuyOrder

class User {
    val id: String = ""
    val name: String = ""
    val email: String = ""
    val password: String = ""
    val age: String = ""
    val cpf: String = ""
    val phone: String = ""
    val address: Address = Address()
    val buyOrder: BuyOrder = BuyOrder()


    // Segundo o LSP do Android Studio essa notação é necessária para que LocalDateTime funcione.
    @RequiresApi(Build.VERSION_CODES.O)
    val dateRegister: LocalDateTime = LocalDateTime.now();
}