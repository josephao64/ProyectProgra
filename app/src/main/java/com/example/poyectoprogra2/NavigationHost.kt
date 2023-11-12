package com.example.menu

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.menu.Items_menu.*
import com.example.poyectoprogra2.Accesorios
import com.example.poyectoprogra2.Alimentos
import com.example.poyectoprogra2.Carrito
import com.example.poyectoprogra2.Inicio
import com.example.poyectoprogra2.Mascotas
import com.example.poyectoprogra2.Perfil

@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(navController = navController,
        startDestination = Pantalla1.ruta,
    ){
        composable(Pantalla1.ruta){
            Inicio()
        }
        composable(Pantalla2.ruta){
            Mascotas()
        }
        composable(Pantalla3.ruta){
            Alimentos()
        }
        composable(Pantalla4.ruta){
            Accesorios()
        }
        composable(Pantalla5.ruta){
            Carrito()
        }
        composable(Pantalla6.ruta){
            Perfil()
        }
    }
}

