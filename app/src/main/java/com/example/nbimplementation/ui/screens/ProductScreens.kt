package com.example.nbimplementation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nbimplementation.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavHostController, productId: String) {
    var qty by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Produk") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Box(modifier = Modifier.fillMaxWidth().height(250.dp).background(Color.Gray))
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nama Produk $productId", style = MaterialTheme.typography.titleLarge)
                Text("Rp 10.000", style = MaterialTheme.typography.titleMedium, color = Color.LightGray)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Deskripsi produk yang sangat mendalam dan informatif tentang barang ini.", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Stok: 99", style = MaterialTheme.typography.bodySmall)
                
                Spacer(modifier = Modifier.weight(1f))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { if (qty > 1) qty-- }) { Text("-") }
                    Text("$qty", modifier = Modifier.padding(horizontal = 16.dp))
                    IconButton(onClick = { qty++ }) { Text("+") }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { /* Add to Cart */ }, modifier = Modifier.weight(1f)) {
                        Text("Keranjang")
                    }
                    Button(onClick = { navController.navigate(Screen.Checkout.route) }, modifier = Modifier.weight(1f)) {
                        Text("Beli Sekarang")
                    }
                }
            }
        }
    }
}
