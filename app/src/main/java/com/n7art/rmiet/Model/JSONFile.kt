package com.n7art.rmiet.Model

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.gson.GsonBuilder
import com.n7art.rmiet.R
import java.io.*

@Composable
fun createFile(): List<Group> {
    val inputStream: InputStream = LocalContext.current.resources.openRawResource(R.raw.schedule)

    val reader = BufferedReader(InputStreamReader(inputStream))
    val builder = GsonBuilder()
    val gson = builder.create()
    val groups: List<Group> = gson.fromJson(reader, Array<Group>::class.java).toList()

    try {
        inputStream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return groups
}