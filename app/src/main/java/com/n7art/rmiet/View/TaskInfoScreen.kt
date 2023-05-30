package com.n7art.rmiet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.n7art.rmiet.Model.Task

@Composable
fun TaskInfoScreen(navController: NavController, task: Task?) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        HeaderBack(navController)
        task?.let { TaskInfo(it) }
    }
}