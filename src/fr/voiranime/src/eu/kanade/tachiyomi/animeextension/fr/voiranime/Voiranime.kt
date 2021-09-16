package eu.kanade.tachiyomi.animeextension.fr.voiranime

import eu.kanade.tachiyomi.animesource.model.AnimeFilterList
import eu.kanade.tachiyomi.animesource.model.SAnime
import eu.kanade.tachiyomi.animesource.model.SEpisode
import eu.kanade.tachiyomi.animesource.model.Video
import eu.kanade.tachiyomi.animesource.online.ParsedAnimeHttpSource
import okhttp3.Request
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class Voiranime : ParsedAnimeHttpSource() {

    override val name = "VoirAnime"

    override val baseUrl = "https://voiranime.com/"

    override val lang = "fr"

    override val supportsLatest = true

    /**
     * Returns the details of the anime from the given [document].
     *
     * @param document the parsed document.
     */
    override fun animeDetailsParse(document: Document): SAnime {
        TODO("Not yet implemented")
    }

    /**
     * Returns a episode from the given element.
     *
     * @param element an element obtained from [episodeListSelector].
     */
    override fun episodeFromElement(element: Element): SEpisode {
        TODO("Not yet implemented")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each episode.
     */
    override fun episodeListSelector(): String {
        TODO("Not yet implemented")
    }

    /**
     * Returns a anime from the given [element]. Most sites only show the title and the url, it's
     * totally fine to fill only those two values.
     *
     * @param element an element obtained from [latestUpdatesSelector].
     */
    override fun latestUpdatesFromElement(element: Element): SAnime {
        TODO("Not yet implemented")
    }

    /**
     * Returns the Jsoup selector that returns the <a> tag linking to the next page, or null if
     * there's no next page.
     */
    override fun latestUpdatesNextPageSelector(): String? {
        TODO("Not yet implemented")
    }

    /**
     * Returns the request for latest anime given the page.
     *
     * @param page the page number to retrieve.
     */
    override fun latestUpdatesRequest(page: Int): Request {
        TODO("Not yet implemented")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each anime.
     */
    override fun latestUpdatesSelector(): String {
        TODO("Not yet implemented")
    }

    /**
     * Returns a anime from the given [element]. Most sites only show the title and the url, it's
     * totally fine to fill only those two values.
     *
     * @param element an element obtained from [popularAnimeSelector].
     */
    override fun popularAnimeFromElement(element: Element): SAnime {
        TODO("Not yet implemented")
    }

    /**
     * Returns the Jsoup selector that returns the <a> tag linking to the next page, or null if
     * there's no next page.
     */
    override fun popularAnimeNextPageSelector(): String? {
        TODO("Not yet implemented")
    }

    /**
     * Returns the request for the popular anime given the page.
     *
     * @param page the page number to retrieve.
     */
    override fun popularAnimeRequest(page: Int): Request {
        TODO("Not yet implemented")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each anime.
     */
    override fun popularAnimeSelector(): String {
        TODO("Not yet implemented")
    }

    /**
     * Returns a anime from the given [element]. Most sites only show the title and the url, it's
     * totally fine to fill only those two values.
     *
     * @param element an element obtained from [searchAnimeSelector].
     */
    override fun searchAnimeFromElement(element: Element): SAnime {
        TODO("Not yet implemented")
    }

    /**
     * Returns the Jsoup selector that returns the <a> tag linking to the next page, or null if
     * there's no next page.
     */
    override fun searchAnimeNextPageSelector(): String? {
        TODO("Not yet implemented")
    }

    /**
     * Returns the request for the search anime given the page.
     *
     * @param page the page number to retrieve.
     * @param query the search query.
     * @param filters the list of filters to apply.
     */
    override fun searchAnimeRequest(page: Int, query: String, filters: AnimeFilterList): Request {
        TODO("Not yet implemented")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each anime.
     */
    override fun searchAnimeSelector(): String {
        TODO("Not yet implemented")
    }

    /**
     * Returns a episode from the given element.
     *
     * @param element an element obtained from [episodeListSelector].
     */
    override fun videoFromElement(element: Element): Video {
        TODO("Not yet implemented")
    }

    /**
     * Returns the Jsoup selector that returns a list of [Element] corresponding to each episode.
     */
    override fun videoListSelector(): String {
        TODO("Not yet implemented")
    }

    /**
     * Returns the absolute url to the source image from the document.
     *
     * @param document the parsed document.
     */
    override fun videoUrlParse(document: Document): String {
        TODO("Not yet implemented")
    }
}
