package com.example.poyectoprogra2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.menu.Items_menu

import com.example.menu.NavigationHost



import com.example.poyectoprogra2.ui.theme.PoyectoProgra2Theme

class Pantalla : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Si PantallaPrincipal requiere un NavController, lo inicializamos aquí.
                    val navController = rememberNavController()
                    PantallaPrincipal(navController) // Pasamos el NavController a PantallaPrincipal.
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(navController: NavController) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navigationItems = listOf(
        Items_menu.Pantalla1,
        Items_menu.Pantalla2,
        Items_menu.Pantalla3,
        Items_menu.Pantalla4,
        Items_menu.Pantalla5,
        Items_menu.Pantalla6
    )
    Scaffold(
        bottomBar = { NavegacionInferior(navController, navigationItems) },

        ) {
        NavigationHost(navController)
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}

@Composable
fun NavegacionInferior(
    navController: NavHostController,
    menuItems: List<Items_menu>
) {
    BottomAppBar() {
        BottomNavigation() {
            val currentRoute = currentRoute(navController)
            menuItems.forEach { item->
                BottomNavigationItem(
                    selected = currentRoute == item.ruta,
                    onClick = { navController.navigate(item.ruta) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    alwaysShowLabel = false
                )
            }
        }
    }
}