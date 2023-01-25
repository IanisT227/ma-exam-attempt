package com.example.exam_practice_1.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Produs::class], version = 1, exportSchema = false)
abstract class ProdusDB : RoomDatabase() {

    abstract fun produsDao(): ProdusDAO

    companion object {
        @Volatile
        private var INSTANCE: ProdusDB? = null

        fun getDatabase(context: Context): ProdusDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProdusDB::class.java,
                    "produs_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}