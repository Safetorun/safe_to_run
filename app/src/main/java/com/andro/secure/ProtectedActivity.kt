package com.andro.secure

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.andro.secure.databinding.ProtectedActivityBinding

class ProtectedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ProtectedActivityBinding.inflate(LayoutInflater.from(this)).root)
    }
}