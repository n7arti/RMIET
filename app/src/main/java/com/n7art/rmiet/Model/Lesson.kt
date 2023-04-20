package com.n7art.rmiet.Model

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class Lesson(_name: String, _teacher: String, _classroom: String) {
    var name: String
    var teacher: String
    var classroom: String

    init {
        name = _name
        teacher = _teacher
        classroom = _classroom
    }
    @Composable
    fun Output(){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(name)
            Text(teacher)
            Text(classroom)

        }
        //Log.i("Lesson\n", "Название: ${name}\nПреподаватель: ${teacher}\nАудитория: ${classroom}\n")
    }
}