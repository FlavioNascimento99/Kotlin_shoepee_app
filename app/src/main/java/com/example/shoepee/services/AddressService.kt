package com.example.shoepee.services

import com.example.shoepee.entity.Address
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {

    @GET("ws/{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): Address
}

object AddressApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://viacep.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: AddressService = retrofit.create(AddressService::class.java)
}
