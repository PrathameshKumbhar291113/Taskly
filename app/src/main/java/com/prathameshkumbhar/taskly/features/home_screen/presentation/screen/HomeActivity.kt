package com.prathameshkumbhar.taskly.features.home_screen.presentation.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prathameshkumbhar.taskly.databinding.ActivityHomeBinding
import com.prathameshkumbhar.taskly.utils.statusBarColor
import dagger.hilt.android.AndroidEntryPoint

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
        statusBarColor(this@HomeActivity)
    }
}