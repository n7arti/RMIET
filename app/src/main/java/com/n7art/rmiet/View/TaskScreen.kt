package com.n7art.rmiet

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.n7art.rmiet.Model.Task

@Composable
fun TaskScreen(navController: NavController, tasks: List<Task>, user: String, group: String) {
    Box(contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Header(user, group)
            AddNewTask()
            Tasks(tasks, navController)
            Buttons(0.7f, 1f, navController)
        }
        FindButton(navController)
    }
}