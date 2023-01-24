package com.example.exam_practice_1.viewmodel

import com.example.exam_practice_1.model.Produs
import com.example.exam_practice_1.model.ProdusAdd
import com.example.exam_practice_1.model.ProdusBuy
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface NetworkService {

    @GET("/produse")
    suspend fun getProduseList(): List<Produs>

    @POST("/adauga")
    suspend fun addProdus(@Body produsData: ProdusAdd): Produs

    @POST("/cumpara")
    suspend fun buyProdus(@Body buyData: ProdusBuy): Produs
}