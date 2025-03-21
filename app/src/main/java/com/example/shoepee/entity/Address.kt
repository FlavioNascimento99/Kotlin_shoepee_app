package com.example.shoepee.entity

import com.google.gson.annotations.SerializedName

// Classe que compõe o dado referente ao Endereço do Cliente em seu registro.
data class Address (
    @SerializedName("cep") val cep: String = "",
    @SerializedName("logradouro") val street: String = "",
    @SerializedName("bairro") val neighborhood: String = "",
    @SerializedName("localidade") val city: String = "",
    @SerializedName("uf") val state: String = ""
)