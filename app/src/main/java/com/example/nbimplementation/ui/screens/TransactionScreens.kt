package com.example.nbimplementation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.nbimplementation.ui.navigation.Screen
import com.example.nbimplementation.ui.theme.*

@Composable
fun CartScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total: Rp 30.000")
                    Button(onClick = { navController.navigate(Screen.Checkout.route) }) {
                        Text("Checkout")
                    }
                }
            }
        }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize().padding(padding), color = MaterialTheme.colorScheme.background) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(3) {
                    CartItem()
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

@Composable
fun CartItem() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = true, onCheckedChange = {})
        Column {
            Text("Nama Produk", style = MaterialTheme.typography.bodyLarge)
            Text("Rp 10.000", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text("x1")
    }
}

@Composable
fun CheckoutScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Checkout", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Alamat Pengiriman", style = MaterialTheme.typography.titleSmall)
            Text("Jl. Industrial Minimalist No. 42, Charcoal City", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Ringkasan Pesanan", style = MaterialTheme.typography.titleSmall)
            itemsCount(2) {
                Text("- Produk A x1 (Rp 10.000)")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text("Total Bayar: Rp 20.000", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { /* Midtrans Integration */ navController.navigate(Screen.History.route) }, modifier = Modifier.fillMaxWidth()) {
                Text("Bayar Sekarang")
            }
        }
    }
}

@Composable
fun HistoryScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize().padding(padding), color = MaterialTheme.colorScheme.background) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(5) { index ->
                    HistoryItem(status = if (index % 3 == 0) "PAID" else if (index % 3 == 1) "PENDING" else "FAILED")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}

@Composable
fun HistoryItem(status: String) {
    val statusColor = when(status) {
        "PAID" -> StatusPaid
        "PENDING" -> StatusPending
        else -> StatusFailed
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Order #TRX12345", style = MaterialTheme.typography.bodySmall)
                Text(status, color = statusColor, style = MaterialTheme.typography.labelSmall)
            }
            Text("Total: Rp 50.000", style = MaterialTheme.typography.bodyLarge)
            Text("14 Aug 2026", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                TextButton(onClick = {}) { Text("Lihat Struk") }
                Button(onClick = {}) { Text("Beli Lagi") }
            }
        }
    }
}

@Composable
private fun itemsCount(count: Int, content: @Composable (Int) -> Unit) {
    repeat(count) { content(it) }
}
