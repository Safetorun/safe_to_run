package com.andro.secure.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import com.andro.secure.R
import com.andro.secure.databinding.ActivityWebViewBinding
import io.github.dllewellyn.safetorun.intents.verify

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityWebViewBinding.inflate(LayoutInflater.from(this)).apply {
            setContentView(root)

            intent.verify {

                "safetorun.com".allowHost()

                actionOnSuccess = {
                    intent?.extras?.getString("url")
                        ?.let { "${it}?sensitive_token=pleasekeepmesecret" }
                        ?.let(webView::loadUrl)
                }
            }
        }
    }
}
