package com.example.procon

import com.google.gson.annotations.SerializedName

data class Empresa(
    @SerializedName("empresa")
    val empresa: String,
    @SerializedName("qtde")
    val quantidade: Int
)