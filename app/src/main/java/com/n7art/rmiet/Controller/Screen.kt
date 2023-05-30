package com.n7art.rmiet.Controller

sealed class Screen(val route: String){
    object Schedule: Screen(route = "schedule")
    object Task: Screen(route = "task")
    object TaskInfo: Screen(route = "taskinfo/{task}")
    object Auth: Screen(route = "auth")
    object Find: Screen(route = "find")
}