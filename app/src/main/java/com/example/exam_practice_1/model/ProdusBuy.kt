package com.example.exam_practice_1.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProdusBuy(
    @Json(name = "id")
    val id: Int,
    @Json(name = "cantitate")
    val cantitate: Int
)
