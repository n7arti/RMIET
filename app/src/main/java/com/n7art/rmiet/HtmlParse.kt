package com.n7art.rmiet

import android.util.Log
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import org.jsoup.nodes.Element
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class HtmlParse {
    companion object {
        private const val url = "https://www.miet.ru/schedule"

        fun parse(group: String): String {
            var schedule: Elements = Elements()

            data class Option(val value: String, val title: String)

            val networkThread = Thread {
                try {
                    val url = URL(url)
                    val connection = url.openConnection()
                    Thread.sleep(10_000)
                    BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
                        var line: String?
                        while (inp.readLine().also { line = it } != null) {
                            Log.v("rrr", line.toString())
                        }
//                    val doc = Jsoup.connect(url).get()
//                    Log.v("AAA", doc.html().toString())
//                    val jsoupOptions = Jsoup.connect(url).get()
//                        .select("option")
//                        .map {
//                            Option(
//                                value = it.attr("value"),
//                                title = it.attr("title")
//                            )
//                        }
//                    Log.v("AAA","Jsoup: $jsoupOptions")
//                    val doc = Jsoup.connect(url).get()
//                    //schedule = doc.selectXpath("#select2-6i9l-container")
//                    Log.v("AAA", "true")
//                    Log.v("AAA", doc.toString())
                    }
                } catch (e: Exception) {
                    Log.v("AAA", e.message.toString())
                }
            }
            networkThread.start()
            return schedule.toString()
        }
    }
}