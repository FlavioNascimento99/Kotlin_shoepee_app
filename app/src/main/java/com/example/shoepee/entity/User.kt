package com.example.shoepee.entity

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class User {
    val id: String = ""
    val name: String = ""
    val email: String = ""
    val password: String = ""

    // Segundo o LSP do Android Studio essa notação é necessária para que LocalDateTime funcione.
    @RequiresApi(Build.VERSION_CODES.O)
    val dateRegister: LocalDateTime = LocalDateTime.now();
}