package com.andro.secure.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import com.andro.secure.R
import com.andro.secure.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityWebViewBinding.inflate(LayoutInflater.from(this)).apply {
            setContentView(root)
            intent?.extras?.getString("url")
                ?.let { "${it}?sensitive_token=pleasekeepmesecret" }
                ?.let(webView::loadUrl)
        }
    }
}
