package com.example.poyectoprogra2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.poyectoprogra2.ModeloDatos.ProductoCarrito

@Database(entities = [ProductoCarrito::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carritoDao(): CarritoDao
}
