package com.n7art.rmiet

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.n7art.rmiet.Model.Task
import java.util.*

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
    ) {
        composable(
            route = Screen.Auth.route
        ) {
            AuthScreen(navController)
        }
        composable(
            route = Screen.Schedule.route
        ) {
            ScheduleScreen(navController)
        }
        composable(
            route = Screen.Task.route
        ) {
            TaskScreen(navController)
        }
        composable(
            route = Screen.TaskInfo.route,
            arguments = listOf(
                navArgument("task") {
                    type = NavType.ParcelableType(Task::class.java)
                }
            )
        ) {
            val task = it.arguments?.getParcelable<Task>("task")
            TaskInfoScreen(navController, task)
        }
        composable(
            route = Screen.Find.route
        ) {
            FindScreen(navController)
        }
    }
}