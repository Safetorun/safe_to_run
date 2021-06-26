package com.andro.secure.intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andro.secure.R
import io.github.dllewellyn.safetorun.intents.verify

class BouncableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bouncable)

        intent.verify {
//            allowContainingIntents = true

            actionOnSuccess = {
                startActivity(intent.extras?.get("bounce") as Intent)
            }
        }
    }
}
