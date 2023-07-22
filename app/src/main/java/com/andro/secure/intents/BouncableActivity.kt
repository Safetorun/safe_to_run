package com.andro.secure.intents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.andro.secure.R
import com.andro.secure.databinding.ActivityBouncableBinding
import com.safetorun.intents.verify

class BouncableActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = ActivityBouncableBinding.inflate(LayoutInflater.from(this)).apply {
            setContentView(root)
        }

        intent.verify(this) {

            andThen { passed, _ ->
                if (passed) {
                    startActivity(intent.extras?.get("bounce") as Intent)
                } else {
                    layout.oopsNotWorking.text = getString(R.string.oops_couldn_t_bounce)

                }
            }
        }
    }
}
