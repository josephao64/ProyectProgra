package com.example.poyectoprogra2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poyectoprogra2.db.CarritoDao
import com.example.poyectoprogra2.ModeloDatos.ProductoCarrito
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarritoViewModel(private val carritoDao: CarritoDao) : ViewModel() {
    private val _productosCarrito = MutableStateFlow<List<ProductoCarrito>>(emptyList())
    val productosCarrito: StateFlow<List<ProductoCarrito>> = _productosCarrito

    fun cargarProductos(usuarioId: String) {
        viewModelScope.launch {
            _productosCarrito.value = carritoDao.getProductosCarrito(usuarioId)
        }
    }

    // Agregar métodos para agregar y eliminar productos del carrito.
}
