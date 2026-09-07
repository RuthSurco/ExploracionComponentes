package com.example.exploracioncomponentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    VistaControles()
                }
            }
        }
    }
}

// -----------------------------------------------------------
// 1. VISTA DE CONTENEDORES (LazyColumn, LazyRow, Card, Surface, Chip)
// -----------------------------------------------------------
@Composable
fun VistaContenedores() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("1. Contenedores", style = MaterialTheme.typography.headlineMedium)
        }

        // LazyRow
        item {
            Text("LazyRow (Fila desplazable):", style = MaterialTheme.typography.titleMedium)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(5) { index ->
                    AssistChip(
                        onClick = { },
                        label = { Text("Chip ${index + 1}") }
                    )
                }
            }
        }

        // Card
        item {
            Text("Card (Tarjeta):", style = MaterialTheme.typography.titleMedium)
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Este es el interior de una Card", style = MaterialTheme.typography.bodyLarge)
                    Text("Agrupa elementos visualmente", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        // Surface
        item {
            Text("Surface (Superficie elevable):", style = MaterialTheme.typography.titleMedium)
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Contenido dentro de un Surface",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

// -----------------------------------------------------------
// 2. VISTA DE CONTROLES (Checkbox, Switch, Slider, RadioButton, FAB)
// -----------------------------------------------------------
@Composable
fun VistaControles() {
    var isChecked by remember { mutableStateOf(true) }
    var isSwitched by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(0.5f) }
    var selectedRadio by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("2. Controles de Interacción", style = MaterialTheme.typography.headlineMedium)

        // Checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
            Text("Checkbox activo: $isChecked")
        }

        // Switch
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = isSwitched, onCheckedChange = { isSwitched = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("Switch: ${if (isSwitched) "ON" else "OFF"}")
        }

        // RadioButton
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = selectedRadio == 1, onClick = { selectedRadio = 1 })
            Text("Opción 1")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(selected = selectedRadio == 2, onClick = { selectedRadio = 2 })
            Text("Opción 2")
        }

        // Slider
        Text("Slider: ${(sliderValue * 100).toInt()}%")
        Slider(value = sliderValue, onValueChange = { sliderValue = it })

        // ProgressIndicator
        Text("ProgressBar / CircularProgress:")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator()
            LinearProgressIndicator(modifier = Modifier.weight(1f))
        }

        // FloatingActionButton
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FloatingActionButton(onClick = { }) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
            Text("FloatingActionButton (FAB)")
        }
    }
}

// -----------------------------------------------------------
// VISTAS PREVIAS (PREVIEWS)
// -----------------------------------------------------------
@Preview(showBackground = true, name = "1. Contenedores")
@Composable
fun PreviewContenedores() {
    MaterialTheme {
        VistaContenedores()
    }
}

@Preview(showBackground = true, name = "2. Controles")
@Composable
fun PreviewControles() {
    MaterialTheme {
        VistaControles()
    }
}