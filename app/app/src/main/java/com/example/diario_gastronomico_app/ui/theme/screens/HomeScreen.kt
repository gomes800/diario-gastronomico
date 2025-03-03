package com.example.diario_gastronomico_app.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun HomeScreenPreview() {

    val navController = rememberNavController()

    HomeScreen(navController)
}

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8EA8C3))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "Tela inicial",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 32.dp))

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = { navController.navigate("restaurants") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF23395B))) {
                Text("Meus Restaurantes",
                    style = TextStyle(fontSize = 20.sp),
                    color = Color.White
                )
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF23395B))) {
                Text("Favoritos",
                    style = TextStyle(fontSize = 20.sp),
                    color = Color.White)
            }
        }
    }
}
