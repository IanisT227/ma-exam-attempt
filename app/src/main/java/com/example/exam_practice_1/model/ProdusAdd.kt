package com.example.exam_practice_1.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProdusAdd(
    @Json(name = "nume")
    val nume: String,
    @Json(name = "descriere")
    val descriere: String,
    @Json(name = "pret")
    val pret: Int,
    @Json(name = "cantitate")
    val cantitate: Int,
    @Json(name = "status")
    val status: Boolean
)