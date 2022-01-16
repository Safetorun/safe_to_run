package com.andro.secure.intents

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.andro.secure.databinding.ActivityWebViewBinding
import io.github.dllewellyn.safetorun.intents.url.params.AllowedType
import io.github.dllewellyn.safetorun.intents.url.urlConfiguration
import io.github.dllewellyn.safetorun.intents.url.urlVerification
import io.github.dllewellyn.safetorun.intents.verify

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
            // Do something
            Log.v("WebViewActivity", "Do something")
        }

        ActivityWebViewBinding.inflate(LayoutInflater.from(this)).apply {
            setContentView(root)

            // Or do it with intent verification

            val result = intent.verify(baseContext) {

                urlConfiguration {
                    "safetorun.com".allowHost()
                    allowParameter {
                        allowedType = AllowedType.String
                        parameterName = "a_token"
                    }
                }

                actionOnSuccess = {
                    errorMessage.visibility = View.GONE
                    intent?.extras?.getString("url")
                        ?.let { "${it}?sensitive_token=pleasekeepmesecret" }
                        ?.let(webView::loadUrl)
                }
            }

            if (result.not()) {
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = "Error with URL"
            }
        }
    }
}
