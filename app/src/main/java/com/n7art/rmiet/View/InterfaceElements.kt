package com.n7art.rmiet

import android.os.Build
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.n7art.rmiet.Controller.Screen
import com.n7art.rmiet.Model.*
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import java.text.SimpleDateFormat
import java.time.LocalDate


private val centurygothic = FontFamily(
    Font(R.font.century_gothic_regular),
    Font(R.font.century_gothic_bold, FontWeight.Bold)
)

@Composable
fun Auth(navController: NavController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isFocusedLogin = remember { mutableStateOf(false) }
    val isFocusedPassword = remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(colorResource(R.color.orioks))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
            textStyle = TextStyle(fontSize = 16.sp, fontFamily = centurygothic),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White, // цвет при получении фокуса
                unfocusedBorderColor = Color.White, // цвет при отсутствии фокуса
                backgroundColor = Color.White,
                textColor = Color.Black,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Black,
                errorLabelColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black
            ),
            label = {
                if (!isFocusedLogin.value && login.isEmpty()) Text(
                    text = "Логин",
                    fontSize = 16.sp,
                    fontFamily = centurygothic,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.alpha(0.5f)
                )
            },
            modifier = Modifier
                .onFocusChanged { isFocusedLogin.value = it.isFocused }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            textStyle = TextStyle(fontSize = 16.sp, fontFamily = centurygothic),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White, // цвет при получении фокуса
                unfocusedBorderColor = Color.White, // цвет при отсутствии фокуса
                backgroundColor = Color.White,
                textColor = Color.Black,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Black,
                errorLabelColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility },
                ) {
                    Icon(
                        painter = painterResource(
                            if (passwordVisibility) R.drawable.visibility
                            else R.drawable.visibility_off
                        ),
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            },
            label = {
                if (!isFocusedPassword.value && login.isEmpty())
                    Text(
                        "Пароль",
                        fontSize = 16.sp,
                        fontFamily = centurygothic,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.alpha(0.5f)
                    )
            },
            modifier = Modifier
                .onFocusChanged { isFocusedPassword.value = it.isFocused }
        )
        Button(
            onClick = {
                onLoginClick(login, password, navController, context)
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .offset(0.dp, 10.dp)
                .fillMaxHeight(0.07f),
        ) {
            Text(stringResource(R.string.auth), fontSize = 16.sp, fontFamily = centurygothic)
        }
    }
}

@Composable
fun Header(name: String, group: String) {
    //вычисление инициалов
    val words = name.split(" ")
    val initials = words[0][0] + words[1].substring(0, 1)
    //две колонки:имя,фамилия,группа; аватар
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.09f),
        shape = RectangleShape,
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .background(colorResource(R.color.orioks)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            //две строки:имя,фамилия; группа
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(0.85f)

            ) {
                Text(
                    name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = centurygothic,
                    color = Color.White
                )
                Text(
                    group,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = centurygothic,
                    color = Color.White,
                    modifier = Modifier.alpha(0.9f)
                )
            }
            //для наслоения квадрата на круг
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxSize()
                    .weight(0.17f),
                contentAlignment = Alignment.TopEnd

            ) {
                //квадрат в правом верхнем углу
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                        .aspectRatio(1f, true)
                        .background(Color.White),
                    verticalArrangement = Arrangement.Top

                ) {}
                //Круг(box) с инициалами
                Box(
                    modifier = Modifier
                        .background(Color.White, CircleShape)
                        .fillMaxHeight()
                        .aspectRatio(1f, true)
                ) {
                    Text(
                        initials,
                        Modifier.align(Alignment.Center),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = centurygothic
                    )
                }
            }
        }
    }

}

@Composable
fun HeaderBack(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.08f),
        shape = RectangleShape,
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier
                .background(colorResource(R.color.orioks))
                .padding(10.dp, 0.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.Task.route) {
                            popUpTo(Screen.Task.route) {
                                inclusive = true
                            }
                        }
                    },
                painter = painterResource(R.drawable.back),
                contentDescription = "back"
            )
        }
    }

}

@Composable
fun Buttons(alpha1: Float, alpha2: Float, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f),
        shape = RectangleShape,
        elevation = 10.dp

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ScheduleButton(navController, alpha1)
            TaskButton(navController, alpha2)
        }
    }
}

@Composable
fun ScheduleButton(navController: NavController, alpha: Float) {
    Column(
        modifier = Modifier
            .clickable {
                navController.navigate(Screen.Schedule.route) {
                    popUpTo(Screen.Schedule.route) {
                        inclusive = true
                    }
                }
            }
            .fillMaxHeight()
            .fillMaxWidth(0.37f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.schedule),
            contentDescription = "schedule",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .alpha(alpha)
        )
        Text(
            stringResource(R.string.schedule),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .alpha(alpha),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = centurygothic,
            color = colorResource(R.color.text),
        )

    }
}

@Composable
fun TaskButton(navController: NavController, alpha: Float) {
    Column(
        modifier = Modifier
            .clickable {
                navController.navigate(Screen.Task.route) {
                    popUpTo(Screen.Task.route) {
                        inclusive = true
                    }
                }
            }
            .fillMaxHeight()
            .fillMaxWidth(0.59f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.tasks),
            contentDescription = "tasks",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .alpha(alpha)
        )
        Text(
            stringResource(R.string.tasks),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .alpha(alpha),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = centurygothic,
            color = colorResource(R.color.text)
        )
    }
}

@Composable
fun FindButton(navController: NavController) {
    Box(
        modifier = Modifier
            .offset(0.dp, 30.dp)
            .background(colorResource(R.color.orioks), CircleShape)
            .fillMaxHeight(0.14f)
            .aspectRatio(1f, true)
            .clickable {
                navController.navigate(Screen.Find.route) {
                    popUpTo((Screen.Find.route)) {
                        inclusive = true
                    }
                }
            },
        contentAlignment = Alignment.Center
    )
    {
        Image(
            modifier = Modifier

                .align(Alignment.Center)
                .offset(0.dp, -15.dp),
            painter = painterResource(R.drawable.search_inactive),
            contentDescription = "search",

            )
    }
}

@Composable
fun FindButtonToFind() {
    Box(
        modifier = Modifier
            .offset(0.dp, 30.dp)
            .background(Color.White, CircleShape)
            .fillMaxWidth(0.27f)
            .aspectRatio(1f, true),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            modifier = Modifier

                .align(Alignment.Center)
                .offset(0.dp, -15.dp),
            painter = painterResource(R.drawable.search_active),
            contentDescription = "search",
        )
    }
}

@Composable
fun TaskInfo(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .bottomBorder(1.dp, colorResource(R.color.black), 0.5f)
                .fillMaxWidth()
                .fillMaxHeight(0.05f),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                task.name,
                modifier = Modifier
                    .padding(10.dp, 0.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = centurygothic,
                color = colorResource(R.color.black),
            )
        }
        Card(
            modifier = Modifier
                .padding(10.dp, 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, colorResource(R.color.black))
        ) {
            Text(
                task.text,
                modifier = Modifier
                    .padding(5.dp, 5.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = centurygothic,
                color = colorResource(R.color.black),
            )
        }
        Box(
            modifier = Modifier
                .bottomBorder(1.dp, colorResource(R.color.black), 0.5f)
                .fillMaxWidth()
                .fillMaxHeight(0.05f)
        ) {
            Text(
                task.subject,
                modifier = Modifier
                    .padding(10.dp, 0.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = centurygothic,
                color = colorResource(R.color.black),
            )
        }
        StaticCalendar()
    }

}

@Composable
fun AddNewTask() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.06f)
            .bottomBorder(1.dp, colorResource(R.color.black), 0.5f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(R.drawable.add),
            contentDescription = "add new task",
        )
        Text(
            stringResource(R.string.new_task),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = centurygothic,
            color = colorResource(R.color.black)
        )
    }
}

@Composable
fun Tasks(tasks: List<Task>, navController: NavController) {
    val day = SimpleDateFormat("dd")
    val month = SimpleDateFormat("MM")
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f),
        verticalArrangement = Arrangement.Top,
    ) {
        itemsIndexed(
            tasks
        ) { index, item ->
            Card(
                modifier = Modifier
                    .clickable {
                        Log.i(
                            "TASK",
                            item.name + " " + item.text + " " + item.subject + " " + item.date
                        )
                        navController.navigate(
                            "taskinfo/${
                                Task(
                                    item.name,
                                    item.text,
                                    item.subject,
                                    item.date
                                )
                            }"
                        )
                    }
                    .fillMaxWidth(),
                shape = RectangleShape
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp, 5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(colorResource(R.color.orioks), CircleShape)
                            .fillMaxWidth(0.2f)
                            .aspectRatio(1f, true),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            day.format(item.date.time) + "." + month.format(item.date.time),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = centurygothic,
                            color = colorResource(R.color.white),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(10.dp, 5.dp, 0.dp, 0.dp)
                    ) {
                        Text(
                            item.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = centurygothic,
                            color = colorResource(R.color.black)
                        )
                        Text(
                            item.subject,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = centurygothic,
                            color = colorResource(R.color.black),
                            modifier = Modifier
                                .alpha(0.7f)
                                .offset(0.dp, 4.dp),
                        )
                    }
                }

            }

        }
    }
}

@Composable
fun Body(week: String, selectedDay: LocalDate) {
    val groups = createFile()
    val mygroup: Group = groups.find { it.name.equals("ПИН-44") }!!
    val schedule = mygroup.schedule
    val nowWeek: Week
    when (week) {
        "Числитель 1" -> nowWeek = schedule.ch1
        "Знаменатель 1" -> nowWeek = schedule.z1
        "Числитель 2" -> nowWeek = schedule.ch2
        "Знаменатель 2" -> nowWeek = schedule.z2
        else -> nowWeek = schedule.ch1
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val lessons: List<Lesson> = when (selectedDay.dayOfWeek.value) {
            1 -> nowWeek.mon.LessonToList()
            2 -> nowWeek.tue.LessonToList()
            3 -> nowWeek.wed.LessonToList()
            4 -> nowWeek.thu.LessonToList()
            5 -> nowWeek.fri.LessonToList()
            6 -> nowWeek.sat.LessonToList()
            else -> emptyList()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            verticalArrangement = Arrangement.Top,
        ) {
            itemsIndexed(
                lessons
            ) { index, item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RectangleShape
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .bottomBorder(1.dp, Color.Black, 1f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillParentMaxWidth(0.15f)
                                .fillParentMaxHeight(0.15f)
                                .rightBorder(1.dp, Color.Black, 1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                "${index + 1} пара",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = centurygothic,
                                color = colorResource(R.color.black)
                            )
                            Text(
                                "9:00",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = centurygothic,
                                color = colorResource(R.color.black)
                            )
                            Text(
                                "10:30",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = centurygothic,
                                color = colorResource(R.color.black)
                            )
                        }
                        if (item != null) {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxWidth(0.65f)
                                    .fillParentMaxHeight(0.15f)
                                    .rightBorder(1.dp, Color.Black, 1f),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    item.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = centurygothic,
                                    color = colorResource(R.color.black),
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    item.teacher,
                                    modifier = Modifier.alpha(0.8f),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = centurygothic,
                                    color = colorResource(R.color.black),
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    item.classroom,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = centurygothic,
                                    color = colorResource(R.color.black),
                                    textAlign = TextAlign.Center
                                )
                            }
                        } else {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxWidth(0.65f)
                                    .fillParentMaxHeight(0.15f)
                                    .rightBorder(1.dp, Color.Black, 1f),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {}
                            Box(
                                contentAlignment = Alignment.Center
                            ) {}
                        }

                    }

                }
            }
        }
    }
}

fun Modifier.bottomBorder(strokeWidth: Dp, color: Color, alpha: Float) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx / 2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx,
                alpha = alpha
            )
        }
    }
)

fun Modifier.rightBorder(strokeWidth: Dp, color: Color, alpha: Float) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx / 2

            drawLine(
                color = color,
                start = Offset(x = width, y = 0f),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx,
                alpha = alpha
            )
        }
    }
)