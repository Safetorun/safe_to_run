package com.andro.secure.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import com.andro.secure.R
import com.andro.secure.databinding.ActivityWebViewBinding
import io.github.dllewellyn.safetorun.intents.url.params.AllowedType
import io.github.dllewellyn.safetorun.intents.url.urlVerification
import io.github.dllewellyn.safetorun.intents.verify
import java.lang.RuntimeException

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Can do this specifically with the web verification tool

        val url = intent?.extras?.getString("url")
        val isUrlOk = url?.urlVerification {
            "safetorun.com".allowHost()
            allowParameter {
                allowedType = AllowedType.String
                parameterName = "a_token"
            }
        }

        if (isUrlOk != true) {
            throw RuntimeException("Failed validation!!")
        }

        ActivityWebViewBinding.inflate(LayoutInflater.from(this)).apply {
            setContentView(root)

            // Or do it with intent verification

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
