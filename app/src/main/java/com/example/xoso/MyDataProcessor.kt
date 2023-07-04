package com.example.xoso

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class MyDataProcessor {
    fun processScrapingData(tinh: String, ngay: String, callback: (List<String>?, Exception?) -> Unit) {
        val url = "https://1688xoso.net/backend/wp-content/themes/168xoso/ma-nhung/ket-qua.php"
        val requestBody = "area=$tinh&date=$ngay".toRequestBody("application/x-www-form-urlencoded".toMediaType())

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                var rs = response.body?.string()
                val contents = mutableListOf<String>()
                rs = rs?.replace(" class=\\\"db\\\"", "")
                rs = rs?.replace("Giải ĐB", "ĐB")
                rs = rs?.replace("Giải Nhất", "G.1")
                rs = rs?.replace("Giải Nhì", "G.2")
                rs = rs?.replace("Giải ba", "G.3")
                rs = rs?.replace("Giải tư", "G.4")
                rs = rs?.replace("Giải năm", "G.5")
                rs = rs?.replace("Giải sáu", "G.6")
                rs = rs?.replace("Giải bảy", "G.7")
                rs?.let {
                    val startTag = "<td>"
                    val startIndexes = mutableListOf<Int>()
                    var startIndex = it.indexOf(startTag)

                    while (startIndex != -1) {
                        startIndexes.add(startIndex)
                        startIndex = it.indexOf(startTag, startIndex + 1)
                    }

                    val endTag = "</td>"
                    val endIndexes = mutableListOf<Int>()

                    for (startIndex in startIndexes) {
                        val endIndex = it.indexOf(endTag, startIndex)
                        if (endIndex != -1) {
                            endIndexes.add(endIndex)
                        }
                    }

                    for (i in 0 until minOf(startIndexes.size, endIndexes.size)) {
                        val content = it.substring(startIndexes[i] + startTag.length, endIndexes[i])
                        contents.add(content)
                    }
                }

                callback(contents, null)
            }

            override fun onFailure(call: Call, e: IOException) {
                callback(null, e)
            }
        })
    }
}
