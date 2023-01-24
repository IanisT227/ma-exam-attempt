package com.example.exam_practice_1.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Produs(
    @Json(name = "id")
    val id: Int,
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
) : Parcelable
