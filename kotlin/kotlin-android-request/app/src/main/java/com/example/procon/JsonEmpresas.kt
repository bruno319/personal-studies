package com.example.procon

import com.google.gson.annotations.SerializedName

data class JsonEmpresas(
    @SerializedName("empresasMaisReclamadas")
    val empresasMaisReclamadas: List<Empresa>
)
