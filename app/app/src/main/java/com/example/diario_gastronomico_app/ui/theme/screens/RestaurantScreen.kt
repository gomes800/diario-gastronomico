package com.example.diario_gastronomico_app.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diario_gastronomico_app.domain.Restaurant
import com.example.diario_gastronomico_app.viewModel.RestaurantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantScreen(viewModel: RestaurantViewModel = viewModel()) {
    val restaurants by viewModel.restaurants.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Restaurantes") }) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else if (restaurants.isEmpty()) {
                Text("Nenhum restaurante encontrado.", modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    items(restaurants) { restaurant ->
                        RestaurantItem(restaurant)
                    }
                }
            }
        }
    }
}

@Composable
fun RestaurantItem(restaurant: Restaurant) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = restaurant.name, style = MaterialTheme.typography.titleLarge)
            Text(text = "Endereço: ${restaurant.address}")
            Text(text = "Telefone: ${restaurant.phone}")
            Text(text = "Site: ${restaurant.site}")
        }
    }
}
