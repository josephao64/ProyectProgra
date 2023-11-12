package com.example.poyectoprogra2

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.poyectoprogra2.ModeloDatos.Accesorio
import com.example.poyectoprogra2.ModeloDatos.Alimento
import com.example.poyectoprogra2.ModeloDatos.Mascota
import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme

@Composable
fun Carrito(carrito: List<Any> = listOf()) { // Asegúrate de que el tipo de carrito sea el correcto
    PoyectoProgra2Theme {
        Column(modifier = Modifier.padding(16.dp)) {
            if (carrito.isNotEmpty()) {
                Text("Productos en el Carrito", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))

                carrito.forEach { producto ->
                    when (producto) {
                        is Mascota -> Text("Mascota: ${producto.nombre}, Precio: ${producto.precio}")
                        is Alimento -> Text("Alimento: ${producto.nombre}, Precio: ${producto.precio}")
                        is Accesorio -> Text("Accesorio: ${producto.nombre}, Precio: ${producto.precio}")
                        // Añade más casos si hay otros tipos de productos
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Button(
                    onClick = { /* Implementar lógica para finalizar la compra */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Finalizar Compra")
                }
            } else {
                Text("El carrito está vacío", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}
