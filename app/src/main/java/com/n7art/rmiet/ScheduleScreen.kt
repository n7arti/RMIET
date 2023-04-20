package com.n7art.rmiet

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                    header = "Числитель 1",
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