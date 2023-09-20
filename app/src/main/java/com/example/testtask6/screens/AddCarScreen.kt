package com.example.testtask6.screens

import androidx.compose.runtime.Composable

import com.example.testtask6.data.CarEntity

@Composable
fun AddCarScreen(
    onAddCar: (CarEntity) -> Unit // Обработчик нажатия кнопки "Добавить"
) {
    // Здесь можно добавить поля для ввода данных о новом автомобиле, например, имя, фотографию, год выпуска, объем двигателя и т. д.
    // Обработать введенные данные и передать их в onAddCar при нажатии кнопки "Добавить"
}
