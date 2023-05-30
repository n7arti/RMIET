package com.n7art.rmiet

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.LocalDate


private val centurygothic = FontFamily(
    Font(R.font.century_gothic_regular),
    Font(R.font.century_gothic_bold, FontWeight.Bold)
)

@Composable
fun WeekCalendar(
    header: String,
    onDaySelected: (Int) -> Unit
) {
    val weekDays = listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        var selectedDay by remember { mutableStateOf<LocalDate>(LocalDate.now()) }
        Column(
            modifier = Modifier
                .background(colorResource(R.color.calendar))
        ) {
            Box(
                modifier = Modifier
                    .padding(0.dp, 3.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    header,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = centurygothic,
                    color = colorResource(R.color.black),
                )
            }
            Row(
                modifier = Modifier
                    .padding(0.dp, 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                weekDays.forEachIndexed { index, day ->
                    Text(
                        text = day,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = centurygothic,
                        color = colorResource(R.color.black)
                    )
                }
            }
            val days = getDaysOfWeek()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                days.forEachIndexed { index, day ->
                    DayView(day, onDaySelected, index, selectedDay == day) { selectedDay = day }
                }
            }
            MonthCalendar()
        }
        Body(header, selectedDay)
    }
}

@Composable
fun DayView(
    day: LocalDate,
    onDaySelected: (Int) -> Unit,
    index: Int,
    isSelected: Boolean,
    onSelected: () -> Unit
) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Box(
            modifier = Modifier
                .padding(0.dp, 5.dp)
                .background(
                    if (isSelected) colorResource(R.color.orioks) else Color.Transparent,
                    CircleShape
                )
                .clickable {
                    onSelected()
                    onDaySelected(index)
                }
                .fillMaxWidth(0.065f)
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                day.dayOfMonth.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = centurygothic,
                color = if (isSelected) Color.White else Color.Black
            )
        }
    }
}

fun getDaysOfWeek(): List<LocalDate> {
    val days = mutableListOf<LocalDate>()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val today = LocalDate.now()
        val startOfWeek = today.with(DayOfWeek.MONDAY)
        for (i in 0 until 7) {
            days.add(startOfWeek.plusDays(i.toLong()))
        }
    }
    return days
}

@Composable
fun MonthCalendar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        var month = ""
        when (LocalDate.now().month.name) {
            "JANUARY" -> month = "января"
            "FEBRUARY" -> month = "февраля"
            "MARCH" -> month = "марта"
            "APRIL" -> month = "апреля"
            "MAY" -> month = "мая"
            "JUNE" -> month = "июня"
            "JULY" -> month = "июля"
            "AUGUST" -> month = "августа"
            "SEPTEMBER" -> month = "сентяря"
            "OCTOBER" -> month = "октября"
            "NOVEMBER" -> month = "ноября"
            "DECEMBER" -> month = "декабря"
            else -> {
                month = LocalDate.now().month.name
            }
        }
        Row(
            modifier = Modifier
                .padding(0.dp, 5.dp)
                .fillMaxWidth()
                .background(colorResource(R.color.calendar)),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = "down",
                modifier = Modifier
                    .offset(-3.dp, -3.dp)
                    .alpha(0.95f)
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    LocalDate.now().dayOfMonth.toString() + " " + month,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = centurygothic,
                    color = colorResource(R.color.black)
                )
            }
            Image(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = "down",
                modifier = Modifier
                    .offset(3.dp, -3.dp)
                    .alpha(0.95f)
            )
        }
    }
}