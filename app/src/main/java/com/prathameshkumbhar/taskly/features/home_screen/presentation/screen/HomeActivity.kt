package com.prathameshkumbhar.taskly.features.home_screen.presentation.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.taskly.R
import com.prathameshkumbhar.taskly.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import splitties.resources.color

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
    }

    private fun setupUi() {
        window.statusBarColor = color(R.color.primary_pink)
    }
}