package com.n7art.rmiet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.n7art.rmiet.Model.Task
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TaskScreen(navController: NavController) {
    val tasks: MutableList<Task> = mutableListOf()
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val dt = "2023-03-15"
    val c = Calendar.getInstance()
    c.time = sdf.parse(dt)
    tasks.add(
        Task(
            "Лабораторная работа №1",
            "Выполнить + отчет",
            "Математическое моделирование",
            c
        )
    )
    Box(contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Header("Артамонова Анастасия ", "ПИН-44")
            AddNewTask()
            Tasks(tasks, navController)
            Buttons(0.7f, 1f, navController)
        }
        FindButton(navController)
    }

}