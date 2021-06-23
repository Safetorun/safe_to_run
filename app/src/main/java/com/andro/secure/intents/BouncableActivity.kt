package com.andro.secure.intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andro.secure.R

class BouncableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bouncable)

        startActivity(intent.extras?.get("bounce") as Intent)
    }
}
