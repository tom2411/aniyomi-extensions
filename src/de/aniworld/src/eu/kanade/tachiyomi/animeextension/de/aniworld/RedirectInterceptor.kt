package eu.kanade.tachiyomi.animeextension.de.aniworld

import android.annotation.SuppressLint
import android.app.Application
import android.os.Handler
import android.os.Looper
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import eu.kanade.tachiyomi.network.GET
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class RedirectInterceptor : Interceptor {

    private val context = Injekt.get<Application>()
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = resolveWithWebView(originalRequest) ?: throw Exception("Bitte Captcha in WebView lösen")

        return chain.proceed(newRequest)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun resolveWithWebView(request: Request): Request? {
        // We need to lock this thread until the WebView finds the challenge solution url, because
        // OkHttp doesn't support asynchronous interceptors.
        val latch = CountDownLatch(1)

        var webView: WebView? = null

        val origRequestUrl = request.url.toString()
        val headers = request.headers.toMultimap().mapValues { it.value.getOrNull(0) ?: "" }.toMutableMap()

        var newRequest: Request? = null

        handler.post {
            val webview = WebView(context)
            webView = webview
            with(webview.settings) {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                useWideViewPort = false
                loadWithOverviewMode = false
                userAgentString = // request.header("User-Agent")
                    // ?: "\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.150 Safari/537.36 Edg/88.0.705.63\""
                    "Mozilla/5.0 (Linux; Android 12; Pixel 5 Build/SP2A.220405.004; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/100.0.4896.127 Safari/537.36"
            }

            webview.webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(
                    view: WebView,
                    request: WebResourceRequest,
                ): WebResourceResponse? {
                    if (request.url.toString().contains("token") || request.url.toString().contains("https://dood") || request.url.toString().contains("https://streamtape") ||
                        request.url.toString().contains("https://voe") || request.url.toString().contains("https://realfinanceblogcenter") ||
                        request.url.toString().contains("https://launchreliantcleaverriver") ||
                        request.url.toString().contains("https://fraudclatterflyingcar") ||
                        request.url.toString().contains("https://uptodatefinishconferenceroom")
                    ) {
                        if (!request.url.toString().contains("ad.js")) {
                            newRequest = GET(request.url.toString(), request.requestHeaders.toHeaders())
                            latch.countDown()
                        }
                    }
                    if (request.url.toString().contains("token") || request.url.toString().contains("https://vidoza.net/embed-")) {
                        if (request.url.toString().contains(".html")) {
                            newRequest = GET(request.url.toString(), request.requestHeaders.toHeaders())
                            latch.countDown()
                        }
                    }
                    return super.shouldInterceptRequest(view, request)
                }
            }
            webView?.loadUrl(origRequestUrl, headers)
        }

        // Wait a reasonable amount of time to retrieve the solution. The minimum should be
        // around 4 seconds but it can take more due to slow networks or server issues.
        latch.await(12, TimeUnit.SECONDS)

        handler.post {
            webView?.stopLoading()
            webView?.destroy()
            webView = null
        }

        return newRequest
    }
}
