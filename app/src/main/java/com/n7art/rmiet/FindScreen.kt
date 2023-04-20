package com.n7art.rmiet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.n7art.rmiet.Model.*

private val centurygothic = FontFamily(
    Font(R.font.century_gothic_regular),
    Font(R.font.century_gothic_bold, FontWeight.Bold)
)

@Composable
fun FindScreen(navController: NavController) {
    var find by remember { mutableStateOf("") }
    var alphag by remember { mutableStateOf(1f) }
    var alphat by remember { mutableStateOf(0.7f) }
    var alphaw by remember { mutableStateOf(0.7f) }
    val groups = createFile()
    val groupsList = getGroupList(groups)
    val teachersList = getTeacherList(groups)
    var findList by remember { mutableStateOf(groupsList) }
    var showDropdown by remember { mutableStateOf(false) }
    val isFocusedFind = remember { mutableStateOf(false) }
    Box(contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.orioks)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = find,

                onValueChange = { newText ->
                    find = newText.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase() else it.toString()
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go,
                    capitalization = KeyboardCapitalization.Words
                ),
                keyboardActions = KeyboardActions(
                    onGo = {}
                ),
                textStyle = TextStyle(fontSize = 16.sp, fontFamily = centurygothic),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White, // цвет при получении фокуса
                    unfocusedBorderColor = Color.White, // цвет при отсутствии фокуса
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    cursorColor = Color.Black,
                ),
                shape = CircleShape,//RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(10.dp, 15.dp, 10.dp, 10.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.07f)
                    .onFocusChanged {
                        isFocusedFind.value = it.isFocused
                        Log.i("CLICK", it.isFocused.toString())
                        showDropdown = it.isFocused
                    }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    "группа",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = centurygothic,
                    color = colorResource(R.color.white),
                    modifier = Modifier
                        .alpha(alphag)
                        .clickable {
                            alphag = 1f
                            alphat = 0.7f
                            alphaw = 0.7f
                            findList = groupsList
                        }
                )
                Text(
                    "преподаватель",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = centurygothic,
                    color = colorResource(R.color.white),
                    modifier = Modifier
                        .alpha(alphat)
                        .clickable {
                            alphag = 0.7f
                            alphat = 1f
                            alphaw = 0.7f
                            findList = teachersList
                        }
                )
                Text(
                    "окно",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = centurygothic,
                    color = colorResource(R.color.white),
                    modifier = Modifier
                        .alpha(alphaw)
                        .clickable {
                            alphag = 0.7f
                            alphat = 0.7f
                            alphaw = 1f
                        }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DropdownMenu(modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.5f),
                expanded = showDropdown,
                onDismissRequest = { showDropdown = true }) {
                findList.forEach { option ->
                    DropdownMenuItem(onClick = {
                        find = option
                        showDropdown = false
                    }) {
                        Text(option)
                    }
                }
            }
        }
        FindButtonToFind()
    }
}

fun getGroupList(groups: List<Group>): List<String> {
    return groups.map { it.name }
}

fun getTeacherList(groups: List<Group>): List<String> {
    val teachersList: MutableList<String> = mutableListOf()
    for (group: Group in groups)
        for (week: Week in group.schedule.WeekToList())
            for (day: Day in week.DayToList())
                for (lesson: Lesson in day.LessonToList())
                    if (lesson != null && !lesson.teacher.contains("Преподаватель") && !teachersList.contains(
                            lesson.teacher
                        )
                    )
                        teachersList.add(lesson.teacher)
    return teachersList
}
