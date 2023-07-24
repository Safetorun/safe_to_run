package com.andro.secure.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.andro.secure.databinding.WebViewFragmentBinding
import com.andro.secure.util.logVerbose
import com.safetorun.inline.verifyLogger
import com.safetorun.intents.url.verify
import com.safetorun.logger.withLogger

class WebViewSample : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        WebViewFragmentBinding.inflate(inflater, container, false)
            .apply {
                webViewSample.webViewClient = WebViewClientSample()
                webViewSample.settings.javaScriptEnabled = true
                webViewSample.addJavascriptInterface(this@WebViewSample, "app")
            }
            .apply { webViewSample.loadUrl("file:///android_asset/index.html") }
            .root

    @JavascriptInterface
    fun executeFunction() {
        logVerbose("Executed function. Imagine this was sensitive..")
    }

    class WebViewClientSample : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {

            val result = request!!.url?.toString()?.verify {
                withLogger(
                    true,
                    view!!.context.verifyLogger(
                        "WebViewSample"
                    )
                )
                "file:///android_asset/index.html".allowUrl()
            } ?: false


            return if (result) {
                super.shouldOverrideUrlLoading(view, request)
            } else {
                true
            }
        }
    }
}
