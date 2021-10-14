package com.andro.secure.intents

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.andro.secure.R
import com.andro.secure.databinding.ActivityBouncableBinding
import io.github.dllewellyn.safetorun.intents.verify

class BouncableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = ActivityBouncableBinding.inflate(LayoutInflater.from(this)).apply {
            setContentView(root)
        }

        intent.verify(this) {

            actionOnSuccess = {
                startActivity(intent.extras?.get("bounce") as Intent)
            }

            actionOnFailure = {
                layout.oopsNotWorking.text = getString(R.string.oops_couldn_t_bounce)
            }
        }
    }
}
