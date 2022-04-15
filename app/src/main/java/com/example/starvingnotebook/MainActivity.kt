package com.example.starvingnotebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findViewById<View>(R.id.navHost).findNavController()
        findViewById<BottomNavigationView>(R.id.buttonsNav).setupWithNavController(navController)

//        findViewById<Button>(R.id.setupButton).setOnClickListener {
//            val navHostFragment = findViewById<View>(R.id.navHost)
//            navHostFragment.findNavController().navigate(R.id.action_notesFragment_to_setupFragment)
//        }
//        findViewById<Button>(R.id.statButton).setOnClickListener {
//            val navHostFragment = findViewById<View>(R.id.navHost)
//            navHostFragment.findNavController().navigate(R.id.action_setupFragment_to_statisticsFragment2)
//
//        }
//        findViewById<Button>(R.id.notesButton).setOnClickListener {
//            val navHostFragment = findViewById<View>(R.id.navHost)
//            navHostFragment.findNavController().navigate(R.id.action_statisticsFragment2_to_notesFragment)
//
//        }
    }
}