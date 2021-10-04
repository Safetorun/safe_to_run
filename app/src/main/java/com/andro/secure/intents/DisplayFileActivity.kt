package com.andro.secure.intents

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import com.andro.secure.R
import io.github.dllewellyn.safetorun.intents.file.allowDirectory
import io.github.dllewellyn.safetorun.intents.file.verifyFile
import java.io.File
import java.nio.charset.Charset

class DisplayFileActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.display_file_activity)

        findViewById<TextView>(R.id.testTextView).apply {
            (when {
                intent.data != null -> {
                    intent.data
                }
                intent.action == Intent.ACTION_SEND -> {
                    intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri
                }
                intent.action == Intent.ACTION_SEND_MULTIPLE -> {
                    intent.getParcelableArrayExtra(Intent.EXTRA_STREAM)?.first() as Uri
                }
                else -> {
                    throw IllegalArgumentException()
                }
            })?.let {
                val fileCheckResult = it.verifyFile(this@DisplayFileActivity) {
                    addAllowedParentDirectory(context.filesDir.allowDirectory())
                }

                if (fileCheckResult) {
                    text =
                        contentResolver.openInputStream(it)
                            ?.readBytes()
                            ?.toString(Charset.defaultCharset())
                }
            }
        }
    }
}
