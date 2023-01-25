package com.example.exam_practice_1.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProdusDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProdus(produs: Produs)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun populateProductTable(produseList: List<Produs>)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<Produs>
}