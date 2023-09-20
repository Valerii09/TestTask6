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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testtask6.ui.theme.TestTask6Theme

class AddCarActivity : ComponentActivity() {
    private var selectedImageUri by mutableStateOf<Uri?>(null)
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedImageUri = uri
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTask6Theme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    CarInfoInput("Название автомобиля (марка)")
                    Spacer(modifier = Modifier.height(16.dp))
                    CarInfoInput("Год выпуска")
                    Spacer(modifier = Modifier.height(16.dp))
                    CarInfoInput("Объем двигателя")

                }}
        }
    }
}

@Composable
fun CarInfoInput(label: String) {
    var text by remember { mutableStateOf("text") }

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
fun AddCarButtom() {
    Button(
        onClick = { /* Действие кнопки */ },
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
    TestTask6Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CarInfoInput("Название автомобиля (марка)")
            Spacer(modifier = Modifier.height(16.dp))
            CarInfoInput("Год выпуска")
            Spacer(modifier = Modifier.height(16.dp))
            CarInfoInput("Объем двигателя")
            AddCarButtom()
        }
    }
}
