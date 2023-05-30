package com.n7art.rmiet

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.n7art.rmiet.Controller.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.jsoup.Jsoup
import java.io.IOException

@Composable
fun AuthScreen(navController: NavController) {
    Auth(navController)
}

fun onLoginClick(
    username: String,
    password: String,
    navController: NavController,
    context: Context
) {
    val client = OkHttpClient()
    var csrfToken = ""
    val grequest = Request.Builder()
        .url("https://orioks.miet.ru/user/login")
        .get()
        .build()
    client.newCall(grequest).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            val responseBody = response.body?.string()
            response.body?.close()
            if (response.isSuccessful) {
                val doc = Jsoup.parse(responseBody)
                csrfToken = doc.select("input[name=_csrf]").attr("value")
            }
        }
    })

    val formBody = FormBody.Builder()
        .add("_csrf", csrfToken)
        .add("LoginForm%5Blogin%5D", username)
        .add("LoginForm%5Bpassword%5D", password)
        .build()
    val request = Request.Builder()
        .url("https://orioks.miet.ru/user/login")
        .post(formBody)
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                (context as LifecycleOwner).lifecycleScope.launch(Dispatchers.Main) {
                    navController.navigate(Screen.Schedule.route) {
                        popUpTo(Screen.Schedule.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, "Неправильный логин или пароль", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    })
}
