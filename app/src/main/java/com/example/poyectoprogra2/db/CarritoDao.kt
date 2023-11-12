package com.example.poyectoprogra2.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.poyectoprogra2.ModeloDatos.ProductoCarrito

@Dao
interface CarritoDao {
    @Query("SELECT * FROM ProductoCarrito WHERE usuarioId = :usuarioId")
    fun getProductosCarrito(usuarioId: String): List<ProductoCarrito>

    @Insert
    fun insertarProducto(productoCarrito: ProductoCarrito)

    // Aquí puedes agregar métodos adicionales, como eliminarProducto.
}
