package com.n7art.rmiet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.res.painterResource

private val centurygothic = FontFamily(
    Font(R.font.century_gothic_regular),
    Font(R.font.century_gothic_bold, FontWeight.Bold)
)

@Composable
fun AuthScreen(navController: NavController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isFocusedLogin = remember { mutableStateOf(false) }
    val isFocusedPassword = remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(false) }

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
                navController.navigate(Screen.Schedule.route) {
                    popUpTo(Screen.Schedule.route) {
                        inclusive = true
                    }
                }
                onLoginClick(login, password)
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

fun onLoginClick(username: Any, password: String) {

}