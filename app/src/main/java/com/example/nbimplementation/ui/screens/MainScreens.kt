package com.example.nbimplementation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nbimplementation.ui.navigation.Screen

@Composable
fun DashboardScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize().padding(padding), color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Dashboard", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                Card(modifier = Modifier.fillMaxWidth().height(150.dp)) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
                        Text("Promo Banner", color = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Produk Populer", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(4) {
                        ProductCard(onClick = { navController.navigate(Screen.ProductDetail.createRoute("prod_$it")) })
                    }
                }
            }
        }
    }
}
@Composable
fun CatalogScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize().padding(padding), color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Cari barang...") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Kategori", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(10) {
                        ProductCard(onClick = { navController.navigate(Screen.ProductDetail.createRoute("prod_$it")) })
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize().padding(padding), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.size(100.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text("User Name", style = MaterialTheme.typography.titleLarge)
                Text("username@example.com", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = { /* Edit Profil */ }, modifier = Modifier.fillMaxWidth()) {
                    Text("Edit Profil")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { navController.navigate(Screen.Login.route) }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                    Text("Logout")
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Home") },
            selected = true,
            onClick = { navController.navigate(Screen.Dashboard.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            label = { Text("Katalog") },
            selected = false,
            onClick = { navController.navigate(Screen.Catalog.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            label = { Text("Keranjang") },
            selected = false,
            onClick = { navController.navigate(Screen.Cart.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.History, contentDescription = null) },
            label = { Text("History") },
            selected = false,
            onClick = { navController.navigate(Screen.History.route) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text("Profile") },
            selected = false,
            onClick = { navController.navigate(Screen.Profile.route) }
        )
    }
}

@Composable
fun ProductCard(onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.Gray))
            Text("Nama Produk", style = MaterialTheme.typography.bodyLarge)
            Text("Rp 10.000", style = MaterialTheme.typography.bodyMedium)
            Text("Stok: 10", style = MaterialTheme.typography.labelSmall)
            Button(onClick = { /* Tambah Keranjang */ }, modifier = Modifier.fillMaxWidth().height(30.dp), contentPadding = PaddingValues(0.dp)) {
                Text("Tambah", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}
