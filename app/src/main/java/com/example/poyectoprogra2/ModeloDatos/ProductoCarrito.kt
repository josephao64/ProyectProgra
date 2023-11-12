package com.example.poyectoprogra2.ModeloDatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductoCarrito(
    @PrimaryKey val id: String,
    val nombre: String,
    val precio: String,
    val cantidad: Int,
    val usuarioId: String
)
