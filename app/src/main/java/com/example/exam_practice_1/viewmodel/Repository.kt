package com.example.exam_practice_1.viewmodel

import androidx.lifecycle.LiveData
import com.example.exam_practice_1.model.Produs
import com.example.exam_practice_1.model.ProdusDAO

class Repository(private val produsDAO: ProdusDAO) {
    suspend fun readData(): List<Produs> {
        println("Reached this")
        return produsDAO.getAllProducts()
    }

    suspend fun addProdus(produs: Produs) {
        produsDAO.insertProdus(produs)
    }

    suspend fun repopulateDB(produseList: List<Produs>) {
        produsDAO.populateProductTable(produseList)
    }

}