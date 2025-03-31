package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(innerPadding)
                }
            }
        }
    }
}

@Composable
fun MainContent(innerPadding: PaddingValues) {
    var val1 by remember { mutableStateOf("") }
    var val2 by remember { mutableStateOf("") }
    var res by remember { mutableDoubleStateOf(0.0) }
    var operacao by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(Color(0xFF1E1B33)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = val1,
            onValueChange = { val1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text("Digite o primeiro número...", color = Color.LightGray) },
            label = { Text("Primeiro número:", color = Color.White) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF3A2E6D),
                unfocusedContainerColor = Color(0xFF3A2E6D)
            )
        )

        TextField(
            value = val2,
            onValueChange = { val2 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text("Digite o segundo número...", color = Color.LightGray) },
            label = { Text("Segundo número:", color = Color.White) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF3A2E6D),
                unfocusedContainerColor = Color(0xFF3A2E6D)
            )
        )

        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    if (operacaoTratada(val1, val2) != 1) {
                        operacao += "Erro: Entrada Inválida!\n"
                    } else {
                        res = val1.toDouble() + val2.toDouble()
                        operacao += "$val1 + $val2 = $res\n"
                    }
                    val1 = ""
                    val2 = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A52A3)),
                modifier = Modifier.size(60.dp)
            ) {
                Text("+", fontSize = 18.sp, color = Color.White)
            }
            Button(
                onClick = {
                    if (operacaoTratada(val1, val2) != 1) {
                        operacao += "Erro: Entrada Inválida!\n"
                    } else {
                        res = val1.toDouble() - val2.toDouble()
                        operacao += "$val1 - $val2 = $res\n"
                    }
                    val1 = ""
                    val2 = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A52A3)),
                modifier = Modifier.size(60.dp)
            ) {
                Text("-", fontSize = 18.sp, color = Color.White)
            }
            Button(
                onClick = {
                    if (operacaoTratada(val1, val2) != 1) {
                        operacao += "Erro: Entrada Inválida!\n"
                    } else {
                        res = val1.toDouble() * val2.toDouble()
                        operacao += "$val1 * $val2 = $res\n"
                    }
                    val1 = ""
                    val2 = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A52A3)),
                modifier = Modifier.size(60.dp)
            ) {
                Text("*", fontSize = 18.sp, color = Color.White)
            }
            Button(
                onClick = {
                    if (operacaoTratada(val1, val2) != 1) {
                        operacao += "Erro: Entrada Inválida!\n"
                    } else if (val2 == "0") {
                        operacao += "Erro: Divisão por zero!\n"
                    } else {
                        res = val1.toDouble() / val2.toDouble()
                        operacao += "$val1 / $val2 = $res\n"
                    }
                    val1 = ""
                    val2 = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A52A3)),
                modifier = Modifier.size(60.dp)
            ) {
                Text("/", fontSize = 18.sp, color = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = operacao,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

fun operacaoTratada(val1: String, val2: String): Int {
    return try {
        val1.toDouble()
        val2.toDouble()
        1
    } catch (e: Exception) {//Teste
        0
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorAppTheme {
        MainContent(PaddingValues(0.dp))
    }
}
