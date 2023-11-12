package com.example.poyectoprogra2



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.poyectoprogra2.ModeloDatos.Mascota
import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme

@Composable
fun Mascotas() {
    var categoriaSeleccionada by remember { mutableStateOf("Perros") }
    val categorias = listOf("Perros", "Gatos")

    val listaPerros = listOf(
        Mascota("Bulldog", "Bulldog Inglés", "$200"),
        Mascota("Beagle", "Beagle", "$150")
        // Agrega más perros aquí
    )

    val listaGatos = listOf(
        Mascota("Siames", "Siamés", "$120"),
        Mascota("Persa", "Persa", "$180")
        // Agrega más gatos aquí
    )

    val carrito = remember { mutableStateListOf<Mascota>() }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var lastAddedItem by remember { mutableStateOf<Mascota?>(null) }

    PoyectoProgra2Theme {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Mascotas", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            // Botones para seleccionar la categoría
            Row {
                categorias.forEach { categoria ->
                    Button(
                        onClick = { categoriaSeleccionada = categoria },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (categoria == categoriaSeleccionada) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(categoria)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de mascotas
            val listaMascotas = if (categoriaSeleccionada == "Perros") listaPerros else listaGatos

            LazyColumn(contentPadding = PaddingValues(bottom = 56.dp)) {
                items(listaMascotas) { mascota ->
                    MascotaCard(mascota) {
                        carrito.add(mascota)
                        lastAddedItem = mascota
                        showConfirmationDialog = true
                    }
                }
            }

            // Diálogo de confirmación
            if (showConfirmationDialog) {
                AlertDialog(
                    onDismissRequest = { showConfirmationDialog = false },
                    title = { Text("Confirmación") },
                    text = { Text("Agregaste ${lastAddedItem?.nombre} al carrito.") },
                    confirmButton = {
                        TextButton(onClick = { showConfirmationDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }

            // Mostrar carrito
            Carrito(carrito)
        }
    }
}

@Composable
fun MascotaCard(mascota: Mascota, onAddToCartClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val imageResource = painterResource(id = mascotaImagenes[mascota.nombre] ?: R.drawable.placeholder_image)
            Image(
                painter = imageResource,
                contentDescription = "Imagen de ${mascota.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(mascota.nombre, style = MaterialTheme.typography.titleMedium)
            Text(mascota.raza, style = MaterialTheme.typography.bodyMedium)
            Text("Precio: ${mascota.precio}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onAddToCartClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar al carrito")
            }
        }
    }
}

@Composable
fun Carrito(carrito: List<Mascota>) {
    if (carrito.isNotEmpty()) {
        Text("Productos en el Carrito", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        carrito.forEach { producto ->
            Text("Mascota: ${producto.nombre}, Precio: ${producto.precio}")
            Spacer(modifier = Modifier.height(4.dp))
        }
    } else {
        Text("El carrito está vacío", style = MaterialTheme.typography.titleLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun MascotasPreview() {
    Mascotas()
}

val mascotaImagenes = mapOf(
    "Bulldog" to R.drawable.bulldog,
    "Beagle" to R.drawable.bulldog,
    "Siames" to R.drawable.persa,
    "Persa" to R.drawable.persa
    // Agrega más referencias de imágenes aquí
)
