package com.example.starvingnotebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.setupButton).setOnClickListener {
            val navHostFragment = findViewById<View>(R.id.navHost)
            navHostFragment.findNavController().navigate(R.id.action_notesFragment_to_setupFragment)
        }
    }
}