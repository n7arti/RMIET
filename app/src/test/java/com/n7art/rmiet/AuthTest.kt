package com.n7art.rmiet

import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import org.junit.Before
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
class AuthTest {
    private lateinit var device: UiDevice
    @Before
    fun setUp(){
        device = UiDevice.getInstance(getInstrumentation())
        val launcherPackageName = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackageName).depth(0)),LAUNCH_TIMEOUT)
        val context = InstrumentationRegistry.getInstrumentation().context
        val targetPackageName = context.packageName
        val app = device.findObject(By.descContains(targetPackageName))
        app.click()
    }

    @Test
    fun testUi(){
        val myButton: UiObject2 = device.wait(Until.findObject(By.res("com.myapp:id/my_button")), DEFAULT_TIMEOUT)

// Кликаем на элемент
        myButton.click()

// Проверяем, что на экране появился нужный текст
        val myText: UiObject2 = device.wait(Until.findObject(By.text("Авторизоваться")), DEFAULT_TIMEOUT)
        assertEquals("Aвторизоваться", myText.text)
    }

    companion object {
        const val LAUNCH_TIMEOUT: Long = 5000
        const val DEFAULT_TIMEOUT: Long = 2000
    }
}