package com.example.poyectoprogra2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PoyectoProgra2Theme {
                // Define el NavController para la navegación
                val navController = rememberNavController()
                // Crea un StateFlow para escuchar los cambios de estado del usuario
                val userFlow = remember { Firebase.auth.currentUserFlow() }
                // Obtiene el estado actual del usuario
                val user by userFlow.collectAsState(initial = Firebase.auth.currentUser)

                // Escucha los cambios de estado de autenticación del usuario
                LaunchedEffect(user) {
                    if (user != null) {
                        navController.navigate("pantalla_principal") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        navController.navigate("login") {
                            popUpTo("pantalla_principal") { inclusive = true }
                        }
                    }
                }

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("pantalla_principal") {
                        PantallaPrincipal(navController)
                    }
                }
            }
        }
    }
}

// Extensión para obtener un StateFlow del estado de autenticación actual
fun FirebaseAuth.currentUserFlow() = callbackFlow {
    val authStateListener = FirebaseAuth.AuthStateListener { auth ->
        this.trySend(auth.currentUser).isSuccess
    }
    addAuthStateListener(authStateListener)
    awaitClose { removeAuthStateListener(authStateListener) }
}
