package com.n7art.rmiet

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import java.time.LocalDate

@Composable
fun ScheduleScreen(navController: NavController) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        var selectedDate by remember { mutableStateOf(LocalDate.now().dayOfMonth) }

        Box(contentAlignment = Alignment.BottomCenter) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Header("Артамонова Анастасия ", "ПИН-44")
                WeekCalendar(
                    header = "Знаменатель 1",
                    onDaySelected = { dayIndex ->
                        selectedDate = dayIndex
                    }

                )
                Buttons(1f, 0.7f, navController)
            }
            FindButton(navController)
        }
    }
}