@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.testtask6

import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testtask6.data.CarDatabase
import com.example.testtask6.data.CarEntity
import com.example.testtask6.data.CarRepository
import com.example.testtask6.data.CarViewModel
import com.example.testtask6.ui.theme.TestTask6Theme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class AddCarActivity : ComponentActivity() {
    private val viewModel: CarViewModel by viewModels()
    private lateinit var carRepository: CarRepository
    private var selectedImageUri by mutableStateOf<Uri?>(null)
    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val carDao = CarDatabase.getDatabase(application).carDao()

        carRepository = CarRepository(carDao)

        // Инициализация переменных
        val carName = "Toyota"
        val year = 2022
        val engineCapacity = 2.0
        val photoUrl = "https://example.com/image.jpg"

        setContent {
            TestTask6Theme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CarInfoInput("Название автомобиля (марка)", carName)
                    Spacer(modifier = Modifier.height(16.dp))
                    CarInfoInput("Год выпуска", year.toString())
                    Spacer(modifier = Modifier.height(16.dp))
                    CarInfoInput("Объем двигателя", engineCapacity.toString())
                    Spacer(modifier = Modifier.height(16.dp))
                    AddCarButton(carRepository, carName, photoUrl, year, engineCapacity)
                    Button(
                        onClick = {
                            val intent = Intent(this@AddCarActivity, MainActivity::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(text = "На главный экран")
                    }
                }
            }
        }
    }
}

@Composable
fun CarInfoInput(label: String, initialText: String = "") {
    var text by remember { mutableStateOf(initialText) }

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        keyboardOptions = KeyboardOptions.Default.copy(),
        keyboardActions = KeyboardActions(
            onDone = {
                // Обработка завершения ввода (по желанию)
            }
        ),
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
        placeholder = {
            Text(text = label) // Установка метки в качестве плейсхолдера
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
    )
}


@Composable
fun AddCarButton(carRepository: CarRepository, carName: String, photoUrl: String?, year: Int, engineCapacity: Double) {
    Button(
        onClick = {
            val car = photoUrl?.let {
                CarEntity(
                    name = carName,
                    photoUrl = it,
                    year = year,
                    engineCapacity = engineCapacity,
                    dateAdded = System.currentTimeMillis()
                )
            }
            val scope = MainScope()
            scope.launch {
                if (car != null) {
                    carRepository.insertCar(car)
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Добавить",
            textAlign = TextAlign.Center
        )
    }

}




@Preview(showBackground = true)
@Composable
fun AddCarActivityPreview() {
    val context = LocalContext.current // Получаем контекст из LocalContext

    val carDatabase = CarDatabase.getDatabase(context)
    val carDao = carDatabase.carDao()
    val carRepository = CarRepository(carDao)

    val carName by remember { mutableStateOf("Toyota") }
    val year by remember { mutableStateOf(2022) }
    val engineCapacity by remember { mutableStateOf(2.0) }
    val photoUrl by remember { mutableStateOf("https://example.com/image.jpg") }

    TestTask6Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CarInfoInput("Название автомобиля (марка)", carName)
            Spacer(modifier = Modifier.height(16.dp))
            CarInfoInput("Год выпуска", year.toString())
            Spacer(modifier = Modifier.height(16.dp))
            CarInfoInput("Объем двигателя", engineCapacity.toString())
            Spacer(modifier = Modifier.height(16.dp))
            AddCarButton(carRepository, carName, photoUrl, year, engineCapacity)
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "На главный экран")
            }
        }
    }
}
