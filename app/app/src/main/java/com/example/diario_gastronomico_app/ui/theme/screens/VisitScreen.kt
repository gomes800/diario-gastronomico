package com.example.diario_gastronomico_app.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.diario_gastronomico_app.domain.Visit
import com.example.diario_gastronomico_app.viewModel.VisitViewModel

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitScreen(viewModel: VisitViewModel = viewModel()) {
    val visits by viewModel.visits.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Visitas") }) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            } else if (visits.isEmpty()) {
                Text("Nenhuma visita encontrada.",
                    modifier = Modifier.padding(16.dp))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    items(visits) { visit ->
                        VisitItem(visit)
                    }
                }
            }
        }
    }
}

@Composable
fun VisitItem(visit: Visit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = visit.restaurant.name, style = MaterialTheme.typography.titleLarge)
            Text(text = "Avaliação: ${visit.rating} estrelas.")
            Text(text = visit.comment)
            val formattedDate = visit.visitDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
            Text(text = "Data da visita: $formattedDate")
        }
    }
}
