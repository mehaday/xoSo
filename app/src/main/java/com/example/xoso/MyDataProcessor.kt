package com.example.xoso

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class MyDataProcessor {
    fun processScrapingData(tinh: String, ngay: String): List<String> {
        val url = "https://1688xoso.net/backend/wp-content/themes/168xoso/ma-nhung/ket-qua.php"
        val requestBody = "area=$tinh&date=$ngay".toRequestBody("application/x-www-form-urlencoded".toMediaType())

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val response = client.newCall(request).execute()
        val rs = response.body?.string()

        val startTag = "<tr>"
        val startIndexes = mutableListOf<Int>()
        var startIndex = rs?.indexOf(startTag) ?: -1

        while (startIndex != -1) {
            startIndexes.add(startIndex)
            startIndex = rs?.indexOf(startTag, startIndex + 1) ?: -1
        }

        val endTag = "</tr>"
        val endIndexes = mutableListOf<Int>()

        for (startIndex in startIndexes) {
            val endIndex = rs?.indexOf(endTag, startIndex) ?: -1
            if (endIndex != -1) {
                endIndexes.add(endIndex)
            }
        }

        val contents = mutableListOf<String>()

        for (i in 0 until minOf(startIndexes.size, endIndexes.size)) {
            val content = rs?.substring(startIndexes[i] + startTag.length, endIndexes[i])
            contents.add(content ?: "")
        }

        return contents
    }
}
