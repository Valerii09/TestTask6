package com.example.testtask6

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.testtask6.data.Car
import com.example.testtask6.data.CarViewModel
import com.example.testtask6.ui.theme.TestTask6Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.testtask6.data.CarEntity



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTask6Theme {
                // Используйте Column для вертикального размещения элементов

                    // Поместите SearchActivity() внутри Column
                    SearchActivity()

                    // Добавьте кнопку после SearchActivity()
                    Button(
                        onClick = {
                            val intent = Intent(this@MainActivity, AddCarActivity::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Добавить автомобиль",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }



@Composable
fun SearchActivity() {
    var searchQuery by remember { mutableStateOf("") }

    // Этот блок кода определяет макет для активности
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Ввод строки поиска
        BasicTextField(
            value = searchQuery,
            onValueChange = { newValue -> searchQuery = newValue },
            keyboardOptions = KeyboardOptions.Default.copy(
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    // Ваша логика поиска здесь
                    // В этом месте вы можете обработать введенный текст
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp)
        )

        // Кнопка для запуска поиска (пока заглушка)
        Button(
            onClick = {
                // Ваша логика поиска здесь
                // В этом месте вы можете обработать введенный текст
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Search")
        }
    }
}

@Composable
fun MyButton() {
    Button(
        onClick = { /* Действие кнопки */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Добавить автомобиль",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchActivityPreview() {
    SearchActivity()
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview() {
    MyButton()
}
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    Column(


    ) {
        MyButton()
    SearchActivity()
    }
}