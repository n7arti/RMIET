package com.n7art.rmiet

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.n7art.rmiet.Controller.MainActivity
import org.junit.Rule
import org.junit.Test

class AuthTest {
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()
    //val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun clickAuth() {
        rule.setContent { MainActivity() }
        rule.onNodeWithText("Авторизоваться").performClick()
        rule.onNodeWithText("ПИН-44").assertIsDisplayed()
    }
}