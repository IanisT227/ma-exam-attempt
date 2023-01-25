package com.example.exam_practice_1.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "products")
data class Produs(
    @Json(name = "id")
    @PrimaryKey
    val id: Int,
    @Json(name = "nume")
    @ColumnInfo(name = "nume")
    val nume: String,
    @Json(name = "descriere")
    @ColumnInfo(name = "descriere")
    val descriere: String,
    @Json(name = "pret")
    @ColumnInfo(name = "pret")
    val pret: Int,
    @Json(name = "cantitate")
    @ColumnInfo(name= "cantitate")
    val cantitate: Int,
    @Json(name = "status")
    @ColumnInfo(name = "status")
    val status: Boolean
) : Parcelable
