package eu.kanade.tachiyomi.animeextension.es.monoschinos.extractors

import eu.kanade.tachiyomi.animesource.model.Video
import eu.kanade.tachiyomi.network.GET
import eu.kanade.tachiyomi.util.asJsoup
import okhttp3.Headers
import okhttp3.OkHttpClient

class UploadExtractor(private val client: OkHttpClient) {
    fun videoFromUrl(url: String, headers: Headers): Video? {
        return try {
            val document = client.newCall(GET(url)).execute().asJsoup()
            val basicUrl = document.selectFirst("script:containsData(var player =)").data().substringAfter("sources: [\"").substringBefore("\"],")
            return Video(basicUrl, "Uqload", basicUrl, headers = headers)
        } catch (e: Exception) {
            null
        }
    }
}
