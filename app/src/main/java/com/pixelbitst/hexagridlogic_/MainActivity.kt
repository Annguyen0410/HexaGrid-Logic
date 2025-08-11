package com.pixelbitst.hexagridlogic_

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    
    private lateinit var hexagonGrid: HexagonGridView
    private lateinit var resetButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // Initialize views
        initializeViews()
        
        // Set up reset button functionality
        setupResetButton()
    }
    
    private fun initializeViews() {
        hexagonGrid = findViewById(R.id.hexagonGrid)
        resetButton = findViewById(R.id.resetButton)
    }
    
    private fun setupResetButton() {
        resetButton.setOnClickListener {
            hexagonGrid.resetAllHexagons()
        }
    }
}