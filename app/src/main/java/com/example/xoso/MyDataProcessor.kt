import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

class MyDataProcessor {
    fun getEmptyList(tinh: String, ngay: String): MutableList<List<String>> {
        val url = "https://www.xoso.net/getkqxs/$tinh/$ngay.js"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        val response = client.newCall(request).execute()
        val html = response.body?.string()

        val document = Jsoup.parse(html)
        val contentElements = document.getElementsByClass("content")
        val emptyList = mutableListOf<List<String>>()

        if (contentElements.isNotEmpty()) {
            val content = contentElements[0]
            val tdElements = content.getElementsByTag("td")
            var dem = 0
            val myList = mutableListOf<String>()

            for (tdElement in tdElements) {
                val tdContent = tdElement.text()
                myList.add(tdContent)
            }

            var contents = myList.subList(2, myList.size)

            for (i in contents.indices step 2) {
                var emptyList2 = mutableListOf<String>()
                emptyList2.add(contents[i])
                val numbers = contents[i + 1].split(" - ")
                var dem = 0

                for (number in numbers) {
                    emptyList2.add(number)
                }

                emptyList.add(emptyList2)
            }
        }

        return emptyList
    }
}

