package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

// ==========================
// ACTIVIDAD PRINCIPAL
// ==========================
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }
}

// ==========================
// CONTENIDO GENERAL DE LA APP
// ==========================
@Composable
fun AppContent() {

    var selectedScreen by remember { mutableStateOf(Screen.HOME) }

    Column(modifier = Modifier.fillMaxSize()) {

        TopBar()

        when (selectedScreen) {
            Screen.HOME -> HomeScreen(
                onNavigate = { selectedScreen = Screen.INFO }
            )
            Screen.INFO -> InfoScreen(
                onNavigateBack = { selectedScreen = Screen.HOME }
            )
        }
    }
}

// ==========================
// ENUM PARA NAVEGACIÓN
// ==========================
enum class Screen {
    HOME,
    INFO
}

// ==========================
// BARRA SUPERIOR
// ==========================
@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Text(
            text = "Aplicación Android",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// ==========================
// PANTALLA PRINCIPAL
// ==========================
@Composable
fun HomeScreen(onNavigate: () -> Unit) {

    var counter by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Bienvenido",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Aplicación desarrollada con Android Studio y Jetpack Compose",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Contador: $counter",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            Button(onClick = { counter++ }) {
                Text("Incrementar")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { counter = 0 }) {
                Text("Reiniciar")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = onNavigate) {
            Text("Ir a Información")
        }

        Spacer(modifier = Modifier.height(30.dp))

        Divider()

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Lista de tecnologías usadas",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))

        TechnologyList()
    }
}

// ==========================
// LISTA DE TECNOLOGÍAS
// ==========================
@Composable
fun TechnologyList() {

    val technologies = listOf(
        "Kotlin",
        "Android Studio",
        "Jetpack Compose",
        "Material Design 3",
        "Arquitectura basada en componentes",
        "Buenas prácticas de codificación",
        "Control de versiones con Git",
        "Pruebas unitarias"
    )

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(technologies) { tech ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = tech,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

// ==========================
// PANTALLA DE INFORMACIÓN
// ==========================
@Composable
fun InfoScreen(onNavigateBack: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Información del Proyecto",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        InfoItem("Proyecto", "Aplicación móvil Android")
        InfoItem("Lenguaje", "Kotlin")
        InfoItem("IDE", "Android Studio")
        InfoItem("Arquitectura", "Componentes reutilizables")
        InfoItem("Resultado", "APK funcional")

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = onNavigateBack) {
            Text("Volver al inicio")
        }
    }
}

// ==========================
// COMPONENTE REUTILIZABLE
// ==========================
@Composable
fun InfoItem(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold)
        Text(text = value)
        Divider()
    }
}


